import model.MemberModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TemplateMapperTest {
    @Test
    public void mapperObjectWithStringTemplateTest() {
        MemberModel member = new MemberModel();
        member.setName("Jennifer");
        member.setLastName("Lowlance");
        member.setExpireDate("30-APR-22");
        String templateString = "${name} ${lastName} membership expiry date is ${expireDate}.";
        String completeString = TemplateMapper.mapperObjectWithStringTemplate(member,templateString,"\\$\\{","\\}");
        assertEquals("Jennifer Lowlance membership expiry date is 30-APR-22.", completeString);

    }

}
