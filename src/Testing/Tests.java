package Testing;

import Managers.CacheManager;
import Managers.SceneManager;
import UI.mRegister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;


import java.util.Arrays;
import java.util.Collection;

/**
 * @author Clay Oshiro-Leavitt
 * Testing Class for Phone Numbers
 */
@RunWith(Parameterized.class)
public class Tests {

    private SceneManager dummysceneM;
    private CacheManager dummycacheM;
    private mRegister dummyMan;

    /*Parameters for Phone Number Tests*/
    @Parameterized.Parameters
    public static Collection<Object[]> phoneNumbers() {
        return Arrays.asList(new Object[][] {
                {"123-456-7890"}, {"(123) 456-7890"}, { "123 456 7890"}, { "123.456.7890"}, {"+91 (123) 456-7890"}/*, { 5, true }, { 6, true }*/
        });
    }
    @Parameterized.Parameter
    public String phoneNumber;

    // Testing Phone Number Validation Systems
 /*   @Test
    public void manufacturerPhoneValidation(){
        dummyMan = new mRegister(dummysceneM, dummycacheM);
        assertTrue(dummyMan.validManuPhone(phoneNumber));
    }
    @Test
    public void manufacturerPhoneValidation1(){
        dummyMan = new mRegister(dummysceneM, dummycacheM);
        assertTrue(dummyMan.validManuPhone("123-456-7890"));
    }*/
}
