package info.stasha.selenx;

import info.stasha.selenx.actions.Action;
import info.stasha.selenx.tags.Page;
import info.stasha.selenx.tags.Site;
import info.stasha.selenx.tags.Test;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 *
 * @author stasha
 */
public class TestExecutor {

    public static void execute(Test test) {
        System.out.println("Executing test: " + test.getId());

        executeInternal(test.getActions(), null);
    }

    private static void executeInternal(Set<Action> actions, Page page) {

        for (Action action : actions) {
            System.out.println("Executing: " + action);

            if (action.getTemplate() != null) {
                System.out.println("Using template: " + action.getTemplate());
                executeInternal(Site.getTemplates().getById(action.getTemplate()).getActions(), page);
                continue;
            }

            action.execute(page);

            if (action.getReturns() != null) {
                if (page == null || page.getId().equals(action.getReturns())) {
                    System.out.println("Setting page context to " + action.getReturns());
                }
                try {
                    page = Site.getPages().getById(action.getReturns());
                } catch (NoSuchElementException ex) {
                    throw new NoSuchElementException("Page with id: " + action.getReturns() + " does not exist");
                }

            }

        }
    }
}
