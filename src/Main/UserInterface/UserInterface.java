package Main.UserInterface;

import java.util.Map;

/**
 * Created by Camille on 15.05.2016.
 */
public interface UserInterface {

    void openMenu();

    Map getRequest();

    void processResponse(Map<String, Object> response);

}
