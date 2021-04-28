import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class CustomObjectMapper {
    public static Map<String,String> getMapObjectWithValueInRegexFormat(Object object) {
        Map<String,String> mapForTemplate = new HashMap<String, String>();
        Class<?> objClass = null;
        try {
            objClass = Class.forName(object.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setMapWithValueInRegexFormat(mapForTemplate,objClass,object);
        return mapForTemplate;

    }
    public static Field[]  getFieldNamesInClass(Class<?> modelClass) {
        Field[] fields = modelClass.getDeclaredFields();
        return fields;
    }
    public static void setMapWithValueInRegexFormat(Map<String,String> mapForTemplate,Class<?> objClass,Object object) {
        Field[] fields = getFieldNamesInClass(objClass);
        StringBuilder keyBuilder = null;
        String key = "";
        String value = "";
        for (Field field : fields) {
            keyBuilder = new StringBuilder();
            keyBuilder.append("${");
            keyBuilder.append(field.getName());
            keyBuilder.append("}");

            key = keyBuilder.toString();
            value = invokeGetterMethod(object,field.getName());
            mapForTemplate.put(key,value);
        }

    }
    public static String invokeGetterMethod(Object object, String variableName)
    {
        Object f = null;
        try {
            PropertyDescriptor pd = new PropertyDescriptor(variableName, object.getClass());
            Method getter = pd.getReadMethod();
            f = getter.invoke(object);
        } catch (IllegalAccessException | IllegalArgumentException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return f!=null?f.toString():"";
    }

}
