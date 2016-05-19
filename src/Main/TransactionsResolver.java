package Main;


import Main.Domain.Balance;
import Main.Domain.Transaction;
import Main.Utilities.DeltaSeparator;
import Main.Utilities.EkstremumFinder;
import Main.Utilities.RoundUtility;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class for returning list of transactions to be made among the friends to them to have even amounts paid.
 * */

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
     * Returns the list of balances. In the balance objects the name of a friend is stored
     * and the amount that friend has overpayed or underpayed.
     * Balance amount (delta value (should be refactored)) is calculated by formula:
     * Balance amount (delta)=  sum to be paid by each friend (average)- sum that specific friend has actually paid (expense)
     *
     * */
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
    * In the method list of balances is separeted in two lists:
    * Positive - with only positive balance  amounts (amounts that are underpaid)
    * Negative - with only negative  amounts (amounts overpaid).
    * Max and min balance amounts are found in positive and negative accordingly.
    * Max and min are compared by summing both values (result is delta value)
    *
    * if max > min (delta>0), transaction is being created containing names of who pays to whom, and the smalest value
    * from this max-min pair. Min value in this case.
    * Balance with min balance amount is removed from negative list,
    * but amount of max is updated with delta values (the leftover basicaly)
    *
    * if max < min (delta <0) same kind of transaction is created only now the sources of names  swapped.
    * Negative and positive list update now happens vice versa - balance from positive is removed and
    * in negative - updated.
    *
    * if max = min (delta = 0) transaction is created with necessary names, amount - max or min (as they are equal)
    * Both max and min balances are removed from positive and negative lists accordingly.
    *
    * Transactions are being added to list.
    * Process continues until list of positive balances is empty, after than list with transactions is returned.
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


