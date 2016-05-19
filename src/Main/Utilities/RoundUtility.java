package Main.Utilities;

import Main.Main;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Helper class to round doubles to 2 decimals
 */
public class RoundUtility {

    public Double round(Double value) {
        BigDecimal valueToRound = new BigDecimal(value, MathContext.DECIMAL32);
        valueToRound = valueToRound.setScale(2, BigDecimal.ROUND_HALF_UP);
        return valueToRound.doubleValue();
    }
}
