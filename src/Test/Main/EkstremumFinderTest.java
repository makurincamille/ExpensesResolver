package Main;


import Main.Domain.Balance;
import Main.Utilities.EkstremumFinder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;


public class EkstremumFinderTest {

    EkstremumFinder ekstremumFinder = new EkstremumFinder();

    @Test
    public void findMaximumValueTest1() {
        List<Balance> valuesMap = new ArrayList<>();
        Balance maximum;
        valuesMap.add(new Balance("name1", 1.0));
        valuesMap.add(new Balance("name2", 2.0));
        valuesMap.add(new Balance("name3", 3.0));

        maximum = ekstremumFinder.findEkstrem(valuesMap);
        assertEquals(3.0, maximum.getBalance());


    }

    @Test
    public void findMaximumValueTest2() {
        List<Balance> valuesMap = new ArrayList<>();
        Balance maximum;
        valuesMap.add(new Balance("name1", -1.0));
        valuesMap.add(new Balance("name2", -2.0));
        valuesMap.add(new Balance("name3", -3.0));

        maximum = ekstremumFinder.findEkstrem(valuesMap);
        assertEquals(-3.0, maximum.getBalance());
    }

    @Test
    public void findMaximumValueTest3() {

        List<Balance> valuesMap = new ArrayList<>();
        Balance maximum;
        valuesMap.add(new Balance("name1", 2.0));
        valuesMap.add(new Balance("name2", 2.0));
        maximum = ekstremumFinder.findEkstrem(valuesMap);
        assertEquals(2.0, maximum.getBalance());

    }
}
