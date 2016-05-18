package Main.Utilities;

import java.util.Iterator;
import java.util.Map;

import static java.lang.Math.abs;

/**
 * Created by Camille on 18.05.2016.
 */
public class EkstremumFinder {
    public Double findEkstrem(Map<String, Double> valuesMap) {
        Double maximumValue = 0.0;
        for (Map.Entry<String, Double> entry : valuesMap.entrySet()) {
            Double currentValue;
            currentValue = entry.getValue();
            if (abs(currentValue) > abs(maximumValue)) {
                maximumValue = currentValue;
            }
        }
        return maximumValue;
    }
}
