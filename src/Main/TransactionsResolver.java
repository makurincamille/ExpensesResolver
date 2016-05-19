package Main;


import Main.Domain.Balance;
import Main.Domain.Transaction;
import Main.Utilities.DeltaSeparator;
import Main.Utilities.EkstremumFinder;
import Main.Utilities.RoundUtility;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

public class TransactionsResolver {

    public Map<String, Double> allfriendsExpenses;
    public List<String> friendNamesList;
    public Double totalExpense;
    RoundUtility roundUtility = new RoundUtility();
    DeltaSeparator separator = new DeltaSeparator();
    EkstremumFinder ekstremumFinder = new EkstremumFinder();

    public TransactionsResolver(Map allfriendsExpenses, List<String> friendNamesList, Double totalExpense) {
        this.totalExpense = totalExpense;
        this.allfriendsExpenses = allfriendsExpenses;
        this.friendNamesList = friendNamesList;
    }

    /**
     * Method creates map for <friend name, delta>
     * Delta = average (sum which everyone has to pay) - friendExpense(sum which specific friend actualy paid)
     * if delta is negative - friend is creditor, he overpayed
     * if delta is positive - friend is debitor, he has underpayed and has to make transactions
     */
    public List<Balance> getAllfriendsDeltaExpenses() {
        Double average = 1.0;
        if (friendNamesList.size()!=0) {
            average = roundUtility.round(totalExpense / friendNamesList.size());
        }
        List<Balance> allfriendsDeltaExpenses = new ArrayList<>();
        for (Map.Entry<String, Double> entry : allfriendsExpenses.entrySet()) {
            Double expense = entry.getValue();
            Double delta = average - expense;
            if (delta != 0) {
                allfriendsDeltaExpenses.add(new Balance(entry.getKey(),delta)); //entry.getKey(), delta
            }

        }
        return allfriendsDeltaExpenses;
    }



    /**
     * In method friend expenses are compared.
     * if currentExpense value is bigger than absolute values of
     * comperableExpense, transaction object is being created, where debitor, creditor names and amount are being specified.
     * Tranactions put in the list.
     * Overal debt and debt of current friend is reduced by amout specified in transaction.
     * Iterations over maps continues until debt equals 0
     * *
     */
    public List<Transaction> resolveTransactions() {
        List<Transaction> transactionsList =new ArrayList<>();
        List<Balance> allfriendsDeltaExpenses =getAllfriendsDeltaExpenses();

        List<Balance> positive = separator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses).get(0);
        List<Balance> negative = separator.separatePositiveAndNegativeValues(allfriendsDeltaExpenses).get(1);


        while (!positive.isEmpty()){
            Balance maxPositive = ekstremumFinder.findEkstrem(positive);
            Balance maxNegative = ekstremumFinder.findEkstrem(negative);
            Double delta = maxPositive.getBalance()+maxNegative.getBalance();

            if (delta<0){
               transactionsList.add(new Transaction(maxPositive.getFriendName(),maxNegative.getFriendName(),maxPositive.getBalance()));
                positive.remove(maxPositive);
                negative.remove(maxNegative);
                negative.add(new Balance(maxNegative.getFriendName(),delta));
            }else if (delta>0){
                transactionsList.add(new Transaction(maxPositive.getFriendName(),maxNegative.getFriendName(),maxNegative.getBalance()));
                positive.remove(maxPositive);
                negative.remove(maxNegative);
                positive.add(new Balance(maxPositive.getFriendName(),delta));

            }else{
            transactionsList.add(new Transaction(maxPositive.getFriendName(), maxNegative.getFriendName(),maxPositive.getBalance()));
                positive.remove(maxPositive);
                negative.remove(maxNegative);
            }
        }

        return transactionsList;
    }
}


