package Main;

import Main.Database.Database;
import Main.Domain.Purchase;
import org.junit.Test;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class DatabaseTest {
    Database database = new Database();


    @Test

    public void getPurchasesByFriendNameTest1() {
        Purchase purchase1 = new Purchase("testName1", "Service1", 3.0);
        Purchase purchase2 = new Purchase("testName1", "Service2", 2.0);
        Purchase purchase3 = new Purchase("testName2", "Service4", 4.0);
        database.addPurchase(purchase1);
        database.addPurchase(purchase2);
        database.addPurchase(purchase3);

        List<Purchase> actual = database.getPurchasesByFriendName("testName1");
        assertEquals(2, actual.size());
        assertEquals(actual.get(0), purchase1);
        assertFalse(actual.contains(purchase3));


    }

    @Test
    /** test when the are no purchases */
    public void getPurchasesByFriendNameTest2() {
        List<Purchase> actual = database.getPurchasesByFriendName("testName1");
        assertTrue(actual.isEmpty());
    }

    @Test
    /** test when searching by friend name which has no purchases */
    public void getPurchasesByFriendNameTest3() {

        Purchase purchase1 = new Purchase("testName1", "Service1", 3.0);
        List<Purchase> actual = database.getPurchasesByFriendName("testName2");
        assertTrue(actual.isEmpty());
        assertFalse(actual.contains(purchase1));
    }
}
