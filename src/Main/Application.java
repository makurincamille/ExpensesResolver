package Main;

import Main.UserInterface.UserInterface;
import Main.UserInterface.UserInterfaceImpl;

import java.util.HashMap;
import java.util.Map;


public class Application {

    UserInterface userInterface = new UserInterfaceImpl();
    Filter filter = new Filter();

    public void run() {
        userInterface.openMenu();
        Map<String, Object> request = userInterface.getRequest();
        Map<String, Object> response = filter.processRequest(request);
        userInterface.processResponse(response);

    }
}
