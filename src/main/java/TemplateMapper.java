
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.ObjectMapper;
public class TemplateMapper {
    public static String replaceStringTemplateWithValuesMap(Map<String, Object> valuesMap,String templateString,String fieldStart,String fieldEnd) {
        String regex = fieldStart + "([^}]+)" + fieldEnd;
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(templateString);
        String result = templateString;
        String newVal = null;
        while (m.find()) {
            String[] found = m.group(1).split("\\.");
            Object o = valuesMap.get(found[0]);
            if(o!=null) {
                newVal = o.toString();
                result = result.replaceFirst(regex, newVal);
            }
        }
        return result;

    }
    public static String mapperObjectWithStringTemplate(Object object,String templateString,String fieldStart,String fieldEnd) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(object, Map.class);
        return  replaceStringTemplateWithValuesMap(map,templateString,fieldStart,fieldEnd);
    }

}
