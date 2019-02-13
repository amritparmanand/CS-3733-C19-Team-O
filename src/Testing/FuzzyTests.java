package Testing;

import Fuzzy.Damerau_Levenshtein;
import Fuzzy.FuzzyContext;
import Fuzzy.Levenshtein;
import Fuzzy.hiddenScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Clay Oshiro-Leavitt
 * @version It 2
 * Testing for Fuzzy Algorithms
 */
@RunWith(Parameterized.class)
public class FuzzyTests {
    private Levenshtein fc;
    private Damerau_Levenshtein fc_Damerau;
    private FuzzyContext fc_hiddenScore;

    @Parameterized.Parameters
    public static Collection<Object[]> fuzzyLevenshtein(){
        return Arrays.asList(new Object[][] {
                {"tomato","potato", 2, 2} , {"ha ha ha ab","ha ha ha ba", 2, 1}, {"Willy Warpus", "Willy Warpus", 0, 0}, {"rock", "gym", 4, 4},
                {"nudweiser", "budweiser", 1, 1}, {"Budweiser", "budweiser", 0, 0}, {"abc", "cba", 2, 2}, {"abc", "bac", 2, 1}
        });
    }
    @Parameterized.Parameter
    public String string1;

    @Parameterized.Parameter (1)
    public String string2;

    @Parameterized.Parameter (2)
    public int expectedDistance;

    @Parameterized.Parameter (3)
    public int expectedSwap;

    @Test
    public void LevenshteinTest(){
        fc = new Levenshtein();
        assertEquals(expectedDistance, fc.Levenshtein(string1, string2));
    }
    @Test
    public void Damerau_LevenshteinTest(){
        fc_Damerau = new Damerau_Levenshtein();
        assertEquals(expectedSwap, fc_Damerau.Damerau_Levenshtein(string1,string2));
    }
  /*  @Test
    public void hiddenScore(){
    fc_hiddenScore = new FuzzyContext();
    fc_hiddenScore.setF(new hiddenScore());
    //assertEquals()
    }*/
}
