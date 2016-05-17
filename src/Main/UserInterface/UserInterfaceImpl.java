package Main.UserInterface;


import java.util.HashMap;
import java.util.Map;

public class UserInterfaceImpl implements UserInterface {

    @Override
    public void openMainWindow() {
        Window mainWindow = new MainWindow();
        mainWindow.showWindow();
    }

    @Override
    public void showResults() {

    }

    @Override
    public Map getPurchaseData() {

        Map<String, Object> purchaseData = new HashMap<>();
        return purchaseData;
    }

    ;

    @Override
    public String getCommand() {
        String command = null;
        return command;
    }


}
