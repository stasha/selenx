package info.stasha.selenx.tests;

import info.stasha.selenx.actions.Action;
import info.stasha.selenx.junit4.SelenxRunner;
import org.junit.runner.RunWith;
import info.stasha.selenx.ExecutingTest;
import info.stasha.selenx.annotations.ExecuteBeforeEach;
import info.stasha.selenx.annotations.ExecuteAfterEach;
import info.stasha.selenx.annotations.Intercept;

/**
 *
 * @author stasha
 */
@RunWith(SelenxRunner.class)
public class HomeTest {

    @ExecuteBeforeEach
    public void executeBeforeEach(ExecutingTest test) {
        System.out.println(test);
    }

    @ExecuteAfterEach
    public void executeAfterEach(ExecutingTest test) {
        System.out.println(test);
    }

    @Intercept(id = "loginTest", actions ={"actionId", "navigateId"})
    public boolean loginTest(Action action) {
        System.out.println("action");
        return true;
    }

}
