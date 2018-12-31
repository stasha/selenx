package info.stasha.selenx.tests;

import info.stasha.selenx.actions.Action;
import info.stasha.selenx.junit4.SelenxRunner;
import java.util.Set;
import org.junit.runner.RunWith;

/**
 *
 * @author stasha
 */
@RunWith(SelenxRunner.class)
public class HomeTest {

    public void executeBeforeEach(Set<Action> actions) {
        System.out.println(actions);
    }

    public void executeAfterEach(Set<Action> actions) {
        System.out.println(actions);
    }

//    public void loginTestExecute(Action action) {
//        System.out.println("action");
//    }

}
