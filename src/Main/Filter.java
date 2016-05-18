package Main;

import java.util.HashMap;
import java.util.Map;


public class Filter {

    Controller controller = new Controller();
    Map<String, Object> response = new HashMap<>();
    public Map processRequest(Map<String, Object> request) {
        String command = (String) request.get("command");
        switch (command) {
            case "ADD_PURCHASE":
                controller.addPurchase((Map) request.get("purchaseData"));
                response.put("command", command);
                response.put("status","Purchase added.");
                response.put("total",controller.getTotalExpenses());
                response.put("average",controller.getAverage());
            return response;
            case "RESOLVE":
                response.put("command", command);
                response.put("expenses", controller.getAllFriendExpenses());
                response.put("transactions",controller.resolveTrasactions());
                return response;
            case "RESET":
                controller.reset();

        }
    return null;
    }
}
