package Main;

import Main.Utilities.RoundUtility;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class RoundUtilityTest {

    @Test
    public void test1() {
        Double result1;
        Double result2;
        Double result3;

        Double value1 = 1.005;
        Double value2 = 1.004;
        Double value3 = 1.006;
        result1 = RoundUtility.round(value1);
        result2 = RoundUtility.round(value2);
        result3 = RoundUtility.round(value3);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        assertEquals(1.01, result1);
        assertEquals(1.00, result2);
        assertEquals(1.01, result3);

    }

    @Test
    public void test2() {
        Double result1;
        Double result2;
        Double result3;

        Double value1 = 1.00545434478;
        Double value2 = 1.00465564456;
        Double value3 = 1.00665445655;
        result1 = RoundUtility.round(value1);
        result2 = RoundUtility.round(value2);
        result3 = RoundUtility.round(value3);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        assertEquals(1.01, result1);
        assertEquals(1.00, result2);
        assertEquals(1.01, result3);

    }

    @Test
    public void test3() {
        Double result1;
        Double result2;
        Double result3;

        Double value1 = 1.095;
        Double value2 = 1.995;
        Double value3 = 1.094;
        result1 = RoundUtility.round(value1);
        result2 = RoundUtility.round(value2);
        result3 = RoundUtility.round(value3);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        assertEquals(1.10, result1);
        assertEquals(2.00, result2);
        assertEquals(1.09, result3);

    }
}
