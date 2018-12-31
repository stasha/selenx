package info.stasha.selenx;

import info.stasha.selenx.actions.Action;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author stasha
 */
public interface ExecutingTest {

    public void setId(String id);

    public String getId();

    public Set<Action> getActions();

    public void setActions(LinkedHashSet<Action> actions);
}
