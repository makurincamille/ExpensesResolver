package Main;


import Main.Domain.Transaction;
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
    public Map<String, Double> getAllfriendsDeltaExpenses() {
        Double average = RoundUtility.round(totalExpense / friendNamesList.size());

        Map<String, Double> allfriendsDeltaExpenses = new HashMap<>();
        for (Map.Entry<String, Double> entry : allfriendsExpenses.entrySet()) {
            Double expense = entry.getValue();
            Double delta = average - expense;
            if (delta != 0) {
                allfriendsDeltaExpenses.put(entry.getKey(), delta);
            }

        }
        return allfriendsDeltaExpenses;
    }

    /**
     * Calculates total amount owed/overpayed
     */
    public Double calculateDebt() {
        Double debt = 0.0;

        for (Map.Entry<String, Double> entry : getAllfriendsDeltaExpenses().entrySet()) {
            if (entry.getValue() >= 0) {
                debt += entry.getValue();
            }
        }
        return debt;
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
        List<Transaction> transactionsList = new ArrayList<>();
        Map<String, Double> allfriendsDeltaExpenses = getAllfriendsDeltaExpenses();

        Double debt = calculateDebt();

        while (debt > 0) {
            for (Map.Entry<String, Double> outerEntry : allfriendsDeltaExpenses.entrySet()) {
                Double currentExpense = outerEntry.getValue();
                    for (Map.Entry<String, Double> innerEntry : allfriendsDeltaExpenses.entrySet()) {
                    Double comperableExpense = innerEntry.getValue();
                    if (comperableExpense < 0 && currentExpense >= abs(comperableExpense)) {
                        Double delta = currentExpense + comperableExpense;
                        transactionsList.add(new Transaction(outerEntry.getKey(), innerEntry.getKey(), comperableExpense));
                        if (delta == 0) {
                            allfriendsDeltaExpenses.remove(outerEntry.getKey());

                        } else {

                            allfriendsDeltaExpenses.replace(outerEntry.getKey(), currentExpense, currentExpense - delta);
                        }
                        allfriendsDeltaExpenses.remove(innerEntry.getKey());

                        debt -= innerEntry.getValue();

                    }
                }

            }
        }


        return transactionsList;
    }
}


