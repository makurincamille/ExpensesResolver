package Main;


import Main.Domain.Balance;
import Main.Utilities.DeltaSeparator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class DeltaSeparatorTest {

    DeltaSeparator deltaSeparator = new DeltaSeparator();


    @Test
    /** separetate balances with positive and negative amounts  */
    public void separatePositiveAndNegativeValuesTest1() {
        List<Balance> allfriendsDeltaExpenses = new ArrayList<>();
        allfriendsDeltaExpenses.add(new Balance("name1", 2.0));
        allfriendsDeltaExpenses.add(new Balance("name2", 1.0));
        allfriendsDeltaExpenses.add(new Balance("name3", -3.0));
        allfriendsDeltaExpenses.add(new Balance("name4", -7.0));
        allfriendsDeltaExpenses.add(new Balance("name5", -4.0));

        List<List> result = deltaSeparator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses);
        List<Balance> positive = result.get(0);
        List<Balance> negative = result.get(1);
        assertEquals(2.0, positive.get(0).getBalance());
        assertEquals("name1", positive.get(0).getFriendName());
        assertEquals(1.0, positive.get(1).getBalance());
        assertEquals("name2", positive.get(1).getFriendName());
        assertEquals(-4.0, negative.get(2).getBalance());
        assertEquals("name5", negative.get(2).getFriendName());

        assertEquals(2, result.get(0).size());
        assertEquals(3, result.get(1).size());


    }

    @Test
    /** separetate balances with one positive balance */
    public void separatePositiveAndNegativeValuesTest2() {
        List<Balance> allfriendsDeltaExpenses = new ArrayList<>();
        allfriendsDeltaExpenses.add(new Balance("name1", 2.0));
        List<List> result = deltaSeparator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses);
        assertEquals(1, result.get(0).size());
        assertTrue(result.get(1).isEmpty());
    }

    @Test
    /** separetate balances with one negative balance */
    public void separatePositiveAndNegativeValuesTest3() {
        List<Balance> allfriendsDeltaExpenses = new ArrayList<>();
        allfriendsDeltaExpenses.add(new Balance("name1", -2.0));

        List<List> result = deltaSeparator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses);
        assertEquals(1, result.get(1).size());
        assertTrue(result.get(0).isEmpty());
    }

    @Test
    /** separetate empty balances list*/
    public void separatePositiveAndNegativeValuesTest4() {
        List<Balance> allfriendsDeltaExpenses = new ArrayList<>();
        List<List> result = deltaSeparator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses);
        assertTrue(result.get(0).isEmpty());
        assertTrue(result.get(1).isEmpty());
    }
}
