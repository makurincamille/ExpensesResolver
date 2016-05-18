package Main;


import Main.Utilities.EkstremumFinder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class EkstremumFinderTest {

    EkstremumFinder ekstremumFinder = new EkstremumFinder();

    @Test
    public void findMaximumValueTest1(){
        Map<String, Double> valuesMap = new HashMap<>();
        Double maximum;
        valuesMap.put("name1",1.0);
        valuesMap.put("name1",2.0);
        valuesMap.put("name1",3.0);
        maximum = ekstremumFinder.findEkstrem(valuesMap);
        assertEquals(3.0, maximum);


    }

    @Test
    public void findMaximumValueTest2(){
        Map<String, Double> valuesMap = new HashMap<>();
        Double maximum;
        valuesMap.put("name1",-1.0);
        valuesMap.put("name1",-2.0);
        valuesMap.put("name1",-3.0);
        maximum = ekstremumFinder.findEkstrem(valuesMap);
        assertEquals(-3.0, maximum);


    }

    @Test
    public void findMaximumValueTest3(){
        Map<String, Double> valuesMap = new HashMap<>();
        Double maximum;
        valuesMap.put("name1",2.0);
        valuesMap.put("name1",2.0);
        maximum = ekstremumFinder.findEkstrem(valuesMap);
        assertEquals(2.0, maximum);


    }
}
