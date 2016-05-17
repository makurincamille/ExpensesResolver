package Main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Camille on 17.05.2016.
 */
public class Filter {

    Controller controller = new Controller();
    Map<String, Object> responce = new HashMap<>();
    public Map processRequest(Map<String, Object> request) {
        String command = (String) request.get("command");
        switch (command) {
            case "ADD_PURCHASE":
                controller.addPurchase((Map) request.get("purchaseData"));
                responce.put("total",controller.getTotalExpenses());
                responce.put("average",controller.getAverage());
            return responce;
            case "RESOLVE":

            case "RESET":
                controller.reset();

        }
    return null;
    }
}
