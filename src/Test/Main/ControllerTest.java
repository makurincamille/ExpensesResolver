package Main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Camille on 16.05.2016.
 */
public class ControllerTest {

    Controller controller = new Controller();

    @Before
    public void setUp() {
        controller.friendNamesList.clear();
    }

    @After
    public void tearDown() {
        controller.friendNamesList.clear();
    }


    @Test
    public void getTotalExpensesTest1() {
        Map<String, Object> purchaseData = new HashMap<>();
        purchaseData.put("friendName", "testName");
        purchaseData.put("purchaseDescription", "HotelPayment");
        purchaseData.put("cost", 1.0);

        controller.addPurchase(purchaseData);
        Double expectetTotalExpenses = 1.0;
        Double actualTotalExpenses = controller.getTotalExpenses();
        assertEquals(expectetTotalExpenses, actualTotalExpenses);

    }

    @Test
    public void getTotalExpensesTest2() {

        controller.addPurchase(createPurchaseData("testName1", "HotelPayment1", 1.3));
        controller.addPurchase(createPurchaseData("testName2", "HotelPayment2", 2.7));
        Double expectetTotalExpenses = 4.0;
        Double actualTotalExpenses = controller.getTotalExpenses();
        assertEquals(expectetTotalExpenses, actualTotalExpenses);

    }

    @Test
    /** Test when there are no purchases*/
    public void getTotalExpensesTest3() {
        Double expectetTotalExpenses = 0.0;
        Double actualTotalExpenses = controller.getTotalExpenses();
        assertEquals(expectetTotalExpenses, actualTotalExpenses);

    }


    @Test
    /** just pdateFriendNameList*/
    public void updateFriendNameListTest1() {

        controller.friendNamesList.add("TestName1");
        controller.friendNamesList.add("TestName2");
        controller.updateFriendNameList("TestName3");
        Integer expectedListSiz = 3;
        Integer actualListSize = controller.friendNamesList.size();
        assertEquals(expectedListSiz, actualListSize);
    }

    @Test
    /** Add friend name which is already in the nameList*/
    public void updateFriendNameListTest2() {
        controller.friendNamesList.add("TestName");
        controller.updateFriendNameList("TestName");
        Integer expectedListSiz = 1;
        Integer actualListSize = controller.friendNamesList.size();
        assertEquals(expectedListSiz, actualListSize);
    }

    @Test
    /** update empty friendNameList*/
    public void updateFriendNameListTest3() {

        controller.updateFriendNameList("TestName");
        Integer expectedListSiz = 1;
        Integer actualListSize = controller.friendNamesList.size();
        assertEquals(expectedListSiz, actualListSize);
    }


    @Test
    /** test when one friend has 2 purchases*/
    public void getFriendExpensesTest1() {
        controller.addPurchase(createPurchaseData("testName", "HotelPayment1", 1.3));
        controller.addPurchase(createPurchaseData("testName", "HotelPayment2", 2.4));

        Double actual = controller.getFriendExpenses("testName");
        Double expected = 3.7;
        assertEquals(expected, actual);
    }

    @Test
    /** test when two friend have one purchases each*/
    public void getFriendExpensesTest2() {
        controller.addPurchase(createPurchaseData("testName1", "HotelPayment1", 1.3));
        controller.addPurchase(createPurchaseData("testName2", "HotelPayment1", 2.4));

        Double actual = controller.getFriendExpenses("testName1");
        Double expected = 1.3;
        assertEquals(expected, actual);
    }

    @Test
    /** test when there are no purchases*/
    public void getFriendExpensesTest3() {
        Double actual = controller.getFriendExpenses("testName1");
        Double expected = 0.0;
        assertEquals(expected, actual);
    }


    @Test
    /** get expenses when there is one purchase for on friend*/
    public void getAllFriendExpensesTest1() {
        controller.addPurchase(createPurchaseData("testName1", "HotelPayment1", 1.3));
        controller.addPurchase(createPurchaseData("testName1", "HotelPayment2", 2.4));
        Map<String, Double> expected = new HashMap<>();
        expected.put("testName1", 3.7);
        Map<String, Double> actual = controller.getAllFriendExpenses();
        assertEquals(expected.get("testName1"), actual.get("testName1"));
        assertEquals(expected.size(), actual.size());

    }

    @Test
    /** get expenses when there are 2 friend with differnt amount of purchases */
    public void getAllFriendExpensesTest2() {
        controller.addPurchase(createPurchaseData("testName1", "HotelPayment1", 1.3));
        controller.addPurchase(createPurchaseData("testName2", "HotelPayment2", 2.4));
        controller.addPurchase(createPurchaseData("testName2", "HotelPayment3", 5.2));
        Map<String, Double> expected = new HashMap<>();
        expected.put("testName1", 1.3);
        expected.put("testName2", 7.6);
        Map<String, Double> actual = controller.getAllFriendExpenses();
        assertEquals(expected.get("testName1"), actual.get("testName1"));
        assertEquals(expected.get("testName2"), actual.get("testName2"));
        assertEquals(expected.size(), actual.size());

    }

   @Test
     /** get expenses when there is no purchases*/
    public void getAllFriendExpensesTest3() {

        Map<String, Double> actual = controller.getAllFriendExpenses();
        assertTrue(actual.isEmpty());

    }


    /**
     * helper method for creating purchase data
     */
    public Map<String, Object> createPurchaseData(String name, String description, Double cost) {
        Map<String, Object> purchaseData = new HashMap<>();
        purchaseData.put("friendName", name);
        purchaseData.put("purchaseDescription", description);
        purchaseData.put("cost", cost);
        return purchaseData;
    }


}