package Testing;

import Managers.CacheManager;
import Managers.SceneManager;
import UI.Controllers.aRegister;
import UI.Controllers.mRegister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;



import java.util.Arrays;
import java.util.Collection;

/**
 * @author Clay Oshiro-Leavitt
 * @version It 2
 * Testing for Phone Numbers
 */
@RunWith(Parameterized.class)
public class Tests {

    private SceneManager dummysceneM;
    private CacheManager dummycacheM;
    private mRegister dummyMan;
    private aRegister dummyAgent;

    // Parameters for Phone Number Tests
    @Parameterized.Parameters
    public static Collection<Object[]> phoneNumbers() {
        return Arrays.asList(new Object[][] {
                {"123-456-7890", true}, {"(123) 456-7890", true}, { "123 456 7890", true}, { "1234567890", true}, {"(123)4567890", true}, { "(123)-456-7890", true }, {"1-800-ALPHNUM", true },
                {"1-(123)-123-1234", true}, {"1.123.123.1234", true}, {"1/123/123/1234", true}, {"(123)-1234-123", false}, {"0000000000", false}, {"00000000000", false}
        });
    }
    @Parameterized.Parameter
    public String phoneNumber;

    @Parameterized.Parameter(1)
    public boolean expected;

    // Testing Phone Number Validation Systems

    // manufacturer phone number
    @Test
    public void manufacturerPhoneValidation(){
        dummyMan = new mRegister(dummysceneM, dummycacheM);
      //  System.out.println(phoneNumber);
        assertEquals(expected, dummyMan.validManuPhone(phoneNumber));
    }

    // agent phone number
//    @Test
//    public void agentPhoneValidation(){
//        dummyAgent = new aRegister(dummysceneM, dummycacheM);
//      //  System.out.println(phoneNumber);
//
//        assertEquals(expected, dummyAgent.validAgentPhone(phoneNumber));
//    }
}
