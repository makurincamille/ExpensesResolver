package Main;

import Main.Domain.Balance;
import Main.Domain.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class TransactionsResolverTest {

    public Map<String, Double> allfriendsExpenses = new HashMap<>();
    public List<String> friendNamesList = new ArrayList<>();
    public Double totalExpense;

    @Before
    public void setUp() {
        allfriendsExpenses.clear();
        friendNamesList.clear();
        totalExpense = 0.0;
    }

    @After
    public void tearDown() {
        allfriendsExpenses.clear();
        friendNamesList.clear();
        totalExpense = 0.0;
    }


    @Test
    /** test for one friend with one purchase*/
    public void getAllfriendsDeltaExpensesTest1() {
        allfriendsExpenses.put("name1", 3.0);
        friendNamesList.add("name1");
        totalExpense = 3.0;
        TransactionsResolver transactionsResolver = new TransactionsResolver(allfriendsExpenses, friendNamesList, totalExpense);
        List<Balance> actual = transactionsResolver.getAllfriendsDeltaExpenses();
        assertEquals(0, actual.size());


    }

    @Test
    /** test for two friends with one purchase*/
    public void getAllfriendsDeltaExpensesTest2() {
        allfriendsExpenses.put("name1", 4.0);
        allfriendsExpenses.put("name2", 9.0);
        friendNamesList.add("name1");
        friendNamesList.add("name2");
        totalExpense = 13.0;
        TransactionsResolver transactionsResolver = new TransactionsResolver(allfriendsExpenses, friendNamesList, totalExpense);
        List<Balance> actual = transactionsResolver.getAllfriendsDeltaExpenses();

        //for debug
        for (Balance balance : actual) {
            System.out.println(balance.getFriendName() +" "+ balance.getBalance());
        }

        assertEquals(2, actual.size());
        /*assertEquals(2.5, actual.get(0).getBalance());
        assertEquals(-2.5, actual.get(1).getBalance());*/


    }

    @Test
    public void resolveTransactionsTest1() {

        allfriendsExpenses.put("name1", 4.0);
        allfriendsExpenses.put("name2", 3.0);
        allfriendsExpenses.put("name3", 8.0);
        friendNamesList.add("name1");
        friendNamesList.add("name2");
        friendNamesList.add("name3");
        totalExpense = 15.0;
        TransactionsResolver transactionsResolver = new TransactionsResolver(allfriendsExpenses, friendNamesList, totalExpense);
        List<Transaction> actual = transactionsResolver.resolveTransactions();
        assertEquals(2, actual.size());

    }



}
