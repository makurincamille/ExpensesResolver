package Main;

import Main.UserInterface.UserInterface;
import Main.UserInterface.UserInterfaceImpl;

import java.util.Map;


public class Application {

    UserInterface UI = new UserInterfaceImpl();
    Controller controller = new Controller();


    public void run() {
        UI.openMainWindow();
        String command = UI.getCommand();

        switch (command) {
            case "ADD_PURCHASE":
                controller.addPurchase(UI.getPurchaseData());

            case "SHOW_TOTAL_EXPENSES":
                Double totalExpenses = controller.getTotalExpenses();
            case "RESET":
                Map<String, Double> allfriendsExpenses = controller.getAllFriendExpenses();



        }
    }
}
