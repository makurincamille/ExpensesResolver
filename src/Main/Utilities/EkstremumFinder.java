package Main.Utilities;

import Main.Domain.Balance;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

/**
 * Created by Camille on 18.05.2016.
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
