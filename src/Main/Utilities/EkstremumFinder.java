package Main.Utilities;

import Main.Domain.Balance;


import java.util.List;


import static java.lang.Math.abs;

/**
 * Method findEkstrem receives list of Balance objects which contains only positive balance amounts or only negative,
 * finds max balance amount (Ekstremum) in list of positive balance amounts
 * or min balance amount in list of negative balance amounts.
 *Returns Balance with max or min amount.
 *
 */
public class EkstremumFinder {
    public Balance findEkstrem(List<Balance> balanceValues) {
        Balance maximumBalance = balanceValues.get(0);
        for (Balance balance: balanceValues) {

            Balance currentBalance = balance;

            if (abs(currentBalance.getBalance()) > abs(maximumBalance.getBalance())) {
                maximumBalance = currentBalance;
            }
        }
        return maximumBalance;
    }
}
