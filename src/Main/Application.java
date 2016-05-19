package Main;

import Main.UserInterface.UserInterface;
import Main.UserInterface.UserInterfaceImpl;

import java.util.Map;

/**
 * The class, instance of which is created in main method and which runs or stops the application execution.
 * User interface (ui)is being created (the "Welcome" sentences displayed)
 * In run() flow is as follows:
 *  - request object with request data (command and purchase data) is send from ui
 *  - request is parsed to filter
 *  - filter returns response with data
 *  - ui processes (displays) data in response
 *  Application stops if command is 4 - exit
 */

public class Application {

    UserInterface userInterface = new UserInterfaceImpl();
    Filter filter = new Filter();

    public void run() {
        while (userInterface.getCommand() != 4) {
            userInterface.openMenu();
            if (userInterface.getCommand() != 4) {
                Map<String, Object> request = userInterface.getRequest();
                Map<String, Object> response = filter.processRequest(request);
                userInterface.processResponse(response);
            }
        }

    }
}
