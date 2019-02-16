package edu.wpi.cs3733c19.teamO.Testing;

import edu.wpi.cs3733c19.teamO.Managers.CacheManager;
import edu.wpi.cs3733c19.teamO.Managers.SceneManager;
import edu.wpi.cs3733c19.teamO.UI.Controllers.aRegister;
import edu.wpi.cs3733c19.teamO.UI.Controllers.mRegister;
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
    private mRegister dummyManu;
    private aRegister dummyAgent;

    // Parameters for Phone Number Tests
    @Parameterized.Parameters
    public static Collection<Object[]> emails() {
        return Arrays.asList(new Object[][] {
                {"clay@TTB.gov", true, true}, {"a@TTB.gov", true, true}, { "Clay123@TTB.gov", true, true}, { "CLAY123@TTB.gov", true, true}, {"clay", false, false}, {"@TTB.gov", false, false}, {"clayTTB.gov", false, false},
                {"", false, false}, {"clay@gmail.com", false, true}, {"clay123@gmail.com", false, true}, {"CLAY123@gmail.com", false, true}, {"john@budweiser.com", false, true}, {"will@reck.io", false, true}, {"clay@a.gov.edu", false, true},
                {"roo.gov@edu", false, false}
        });
    }
    @Parameterized.Parameter
    public String email;

    @Parameterized.Parameter(1)
    public boolean agentExpected;

    @Parameterized.Parameter (2)
    public boolean manuExpected;

    // Testing Email Validation Systems

    // agent email validation
    @Test
    public void agentEmailValidation(){
        dummyAgent = new aRegister(dummysceneM, dummycacheM);
        assertEquals(agentExpected, dummyAgent.validAgentEmail(email));
    }

    @Test
    public void manufacturerEmailVerification(){
        dummyManu = new mRegister(dummysceneM, dummycacheM);
        assertEquals(manuExpected, dummyManu.validManuEmail(email));
    }


}
