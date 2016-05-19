package Main;


import Main.Database.Database;
import Main.Domain.Purchase;
import Main.Domain.Transaction;
import Main.Utilities.RoundUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller processes the data which was received with request and returns data for response.
 */
public class Controller {

    Database database = new Database();
    RoundUtility roundUtility = new RoundUtility();

    public List<String> friendNamesList = new ArrayList<>();

    /**
     * Adds purchase to storage and updates friend name list
     */
    public void addPurchase(Map purchaseData) {

        updateFriendNameList((String) purchaseData.get("friendName"));
        Purchase purchase = new Purchase((String) purchaseData.get("friendName"),
                (String) purchaseData.get("purchaseDescription"), roundUtility.round((Double) purchaseData.get("cost")));
        database.addPurchase(purchase);

    }

    /**
     * Returns the sum of all purchases.
     */
    public Double getTotalExpenses() {
        Double totalExpense = 0.0;
        List<Purchase> purchaseList = database.getAllPurchaseList();
        for (Purchase purchase : purchaseList) {
            Double purchseCost = purchase.getCost();
            totalExpense += purchseCost;
        }
        return roundUtility.round(totalExpense);
    }

    /**
     * Returns the amount that every friend has to have paid.
     */
    public Double getAverage() {
        return roundUtility.round(getTotalExpenses() / friendNamesList.size());
    }

    /**
     * Returns the sum of expenses for a specific friend.
     */
    public Double getFriendExpenses(String friendName) {
        Double friendsTotalExpens = 0.0;
        List<Purchase> friendsPurchaseList = database.getPurchasesByFriendName(friendName);
        for (Purchase purchase : friendsPurchaseList) {
            Double purchseCost = purchase.getCost();
            friendsTotalExpens += purchseCost;
        }
        return friendsTotalExpens;
    }

    /**
     * returns map <friendName, sum of expenses> for all friend names
     */
    public Map getAllFriendExpenses() {
        Map<String, Double> allfriendsExpenses = new HashMap<>();
        for (String friendName : friendNamesList) {
            allfriendsExpenses.put(friendName, getFriendExpenses(friendName));
        }
        return allfriendsExpenses;
    }

    /**
     * Returns list of transactions to be made among the friends to them to have even amounts paid.
     */
    public List<Transaction> resolveTrasactions() {

        TransactionsResolver transactionsResolver = new TransactionsResolver(getAllFriendExpenses(),
                friendNamesList, getTotalExpenses());

        List<Transaction> transactionsList = transactionsResolver.resolveTransactions();
        return transactionsList;
    }

    /**
     * Adds friends name to the friend name list? if the list does't contain that name
     */
    public void updateFriendNameList(String friendName) {

        if (!friendNamesList.contains(friendName)) {
            friendNamesList.add(friendName);
        }
    }

    public void reset() {
        database.reset();
        friendNamesList.clear();
    }
}
