package Main.Database;

import Main.Domain.Purchase;

import java.util.ArrayList;
import java.util.List;

/**
 *  Just a data storage layer.
 * */

public class Database {


    public List<Purchase> purchaseList = new ArrayList<>();

    public List<Purchase> getAllPurchaseList() {
        return purchaseList;
    }

    public void addPurchase(Purchase purchase) {
        purchaseList.add(purchase);
    }

    /**
     * returns the list of purchases of specific friend
     */
    public List<Purchase> getPurchasesByFriendName(String friendName) {

        List<Purchase> listOfFriendPurchases = new ArrayList<>();
        for (Purchase purchase : purchaseList) {
            if (purchase.getFriendName().equals(friendName)) {
                listOfFriendPurchases.add(purchase);
            }
        }

        return listOfFriendPurchases;
    }

    public void reset() {
        purchaseList.clear();
    }
}
