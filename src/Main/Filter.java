package Main;

import java.util.HashMap;
import java.util.Map;


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
                responce.put("expenses", controller.getAllFriendExpenses());
                responce.put("transactions",controller.resolveTrasactions());
                return responce;
            case "RESET":
                controller.reset();

        }
    return null;
    }
}
