package Main.UserInterface;

import java.util.Map;

public interface UserInterface {

    void openMenu();

    Map getRequest();

    void processResponse(Map<String, Object> response);
    Integer getCommand();

}
