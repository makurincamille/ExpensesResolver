package Main;

import Main.UserInterface.UserInterface;
import Main.UserInterface.UserInterfaceImpl;

import java.util.Map;


public class Application {

    UserInterface UI = new UserInterfaceImpl();
    Filter filter = new Filter();
    public void run() {
        UI.openMainWindow();



    }
}
