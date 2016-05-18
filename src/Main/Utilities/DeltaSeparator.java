package Main.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Devides map of all friend deltas into 2 maps. One with positive deltas, second with negative*/
public class DeltaSeparator {

    public List<Map> separatePositiveAndNegativeValues (Map <String, Double> allfriendsDeltaExpenses){

        List<Map> result = new ArrayList<>();
        Map <String, Double> positive = new HashMap<>();
        Map <String, Double> negative = new HashMap<>();
        for (Map.Entry<String, Double> entry : allfriendsDeltaExpenses.entrySet()){
            if (entry.getValue()>0){
                positive.put(entry.getKey(),entry.getValue());
            } else {
                negative.put(entry.getKey(),entry.getValue());
            }

        }
        result.add(positive);
        result.add(negative);
        return result;
    }


}
