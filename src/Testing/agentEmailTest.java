package Testing;

import Managers.CacheManager;
import Managers.SceneManager;
import UI.Controllers.aRegister;
import UI.Controllers.mRegister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Clay Oshiro-Leavitt
 * @version It 2
 * Testing for Agent Email verification
 */

@RunWith(Parameterized.class)
public class agentEmailTest {

    private SceneManager dummysceneM;
    private CacheManager dummycacheM;
    private aRegister dummyAgent;

    // Parameters for Phone Number Tests
    @Parameterized.Parameters
    public static Collection<Object[]> emails() {
        return Arrays.asList(new Object[][] {
                {"clay@TTB.gov", true}, {"a@TTB.gov", true}, { "Clay123@TTB.gov", true}, { "CLAY123@TTB.gov", true}, {"clay", false}, {"@TTB.gov", false}, {"clayTTB.gov", false},
                {"", false}
        });
    }
    @Parameterized.Parameter
    public String email;

    @Parameterized.Parameter(1)
    public boolean expected;

    // Testing Phone Number Validation Systems

    // manufacturer phone number
    @Test
    public void agentEmailValidation(){
        dummyAgent = new aRegister(dummysceneM, dummycacheM);

        assertEquals(expected, dummyAgent.validAgentEmail(email));
    }
}
