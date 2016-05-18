package Main;


import Main.Utilities.DeltaSeparator;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class DeltaSeparatorTest {

    DeltaSeparator deltaSeparator = new DeltaSeparator();


    @Test
    public void separatePositiveAndNegativeValuesTest1(){
        Map<String, Double> allfriendsDeltaExpenses = new HashMap<>();
        allfriendsDeltaExpenses.put("name1",2.0);
        allfriendsDeltaExpenses.put("name2",1.0);
        allfriendsDeltaExpenses.put("name3",-3.0);
        allfriendsDeltaExpenses.put("name4",-7.0);
        allfriendsDeltaExpenses.put("name5",-4.0);
        List<Map> result = deltaSeparator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses);

        assertEquals(2.0,result.get(0).get("name1"));
        assertEquals(-4.0,result.get(1).get("name5"));

        assertEquals(2,result.get(0).size());
        assertEquals(3,result.get(1).size());
        assertTrue(result.get(0).containsKey("name1"));
        assertTrue(result.get(0).containsKey("name2"));
        assertTrue(result.get(0).containsValue(2.0));
        assertTrue(result.get(0).containsValue(1.0));
        assertTrue(result.get(1).containsKey("name3"));
        assertTrue(result.get(1).containsValue(-3.0));


    }
    @Test
    public void separatePositiveAndNegativeValuesTest2(){
        Map<String, Double> allfriendsDeltaExpenses = new HashMap<>();
        allfriendsDeltaExpenses.put("name1",2.0);
        List<Map> result = deltaSeparator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses);
        assertEquals(1,result.get(0).size());
        assertTrue(result.get(1).isEmpty());
    }

    @Test
    public void separatePositiveAndNegativeValuesTest3(){
        Map<String, Double> allfriendsDeltaExpenses = new HashMap<>();
        allfriendsDeltaExpenses.put("name1",-2.0);
        List<Map> result = deltaSeparator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses);
        assertEquals(1,result.get(1).size());
        assertTrue(result.get(0).isEmpty());
    }

    @Test
    public void separatePositiveAndNegativeValuesTest4(){
        Map<String, Double> allfriendsDeltaExpenses = new HashMap<>();
        List<Map> result = deltaSeparator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses);
        assertTrue(result.get(0).isEmpty());
        assertTrue(result.get(1).isEmpty());
    }
}
