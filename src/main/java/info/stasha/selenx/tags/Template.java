package info.stasha.selenx.tags;

import info.stasha.selenx.actions.Action;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author stasha
 */
public class Template extends Id implements Extensible {

    private LinkedHashSet<Action> actions = new LinkedHashSet<>();
    private String extendsTemplate;

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(LinkedHashSet<Action> actions) {
        this.actions = actions;
    }

    @Override
    public void setExtends(Object extendstemplate) {
        this.extendsTemplate = extendsTemplate;
    }

    @Override
    public Object getExtends() {
        return this.extendsTemplate;
    }

}
