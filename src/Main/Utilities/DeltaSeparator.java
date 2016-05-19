package Main.Utilities;

import Main.Domain.Balance;

import java.util.ArrayList;
import java.util.List;


/**
 * Devides map of all friend deltas into 2 maps. One with positive deltas, second with negative
 */
public class DeltaSeparator {

    public List<List> separatePositiveAndNegativeValues(List<Balance> allfriendsDeltaExpenses) {

        List<List> result = new ArrayList<>();
        List<Balance> positive = new ArrayList<>();
        List<Balance> negative = new ArrayList<>();
        for (Balance balance : allfriendsDeltaExpenses) {
            if (balance.getBalance() > 0) {
                positive.add(balance);
            } else {
                negative.add(balance);
            }

        }
        result.add(positive);
        result.add(negative);
        return result;
    }


}
