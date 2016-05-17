package Main;




import Main.Database.Database;
import Main.Domain.Purchase;
import Main.Domain.Transaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Controller {

    Database database = new Database();
    public Double totalExpense = 0.0;

    public List<String> friendNamesList = new ArrayList<>();


    public void addPurchase(Map purchaseData) {

        updateFriendNameList((String) purchaseData.get("friendName"));
        Purchase purchase = new Purchase((String) purchaseData.get("friendName"),
                (String) purchaseData.get("purchaseDescription"), (Double) purchaseData.get("cost"));
        database.addPurchase(purchase);

    }


    public Double getTotalExpenses() {

        List<Purchase> purchaseList = database.getAllPurchaseList();
        for (Purchase purchase : purchaseList) {
            Double purchseCost = purchase.getCost();
            totalExpense += purchseCost;
        }
        return totalExpense;
    }
    public Double getAverage(){
        Double average =  getTotalExpenses() / friendNamesList.size();
        return average;
    }

    /**
     * Returns total expenses of specific friend
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
     * returns map <friendName, totalExpense> for all friend names
     */
    public Map getAllFriendExpenses() {
        Map<String, Double> allfriendsExpenses = new HashMap<>();
        for (String friendName : friendNamesList) {
            allfriendsExpenses.put(friendName, getFriendExpenses(friendName));
        }
        return allfriendsExpenses;
    }


    public List<Transaction> resolveTrasactions() {

        TransactionsResolver transactionsResolver = new TransactionsResolver(getAllFriendExpenses(),
                friendNamesList, getTotalExpenses());


        List<Transaction> transactionsList = transactionsResolver.resolveTransactions();
        return transactionsList;
    }


    public void updateFriendNameList(String friendName) {

        if (!friendNamesList.contains(friendName)) {
            friendNamesList.add(friendName);
        }
    }

    public void reset(){
        database.reset();
        totalExpense = 0.0;
        friendNamesList.clear();
    }
}
