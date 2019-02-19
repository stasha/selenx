package info.stasha.selenx.tags;

import info.stasha.selenx.actions.Action;
import java.util.LinkedHashSet;
import java.util.Set;
import info.stasha.selenx.ExecutingTest;

/**
 *
 * @author stasha
 */
public class Test extends Id implements ExecutingTest {

    private LinkedHashSet<Action> actions = new LinkedHashSet<>();
    private String ignore;

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(LinkedHashSet<Action> actions) {
        this.actions = actions;
    }

    public String getIgnore() {
        return ignore;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }
    
    

}
