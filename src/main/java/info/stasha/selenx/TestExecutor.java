package info.stasha.selenx;

import info.stasha.selenx.actions.Action;
import info.stasha.selenx.tags.Page;
import info.stasha.selenx.tags.Site;
import info.stasha.selenx.tags.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

/**
 *
 * @author stasha
 */
public class TestExecutor {

    private final Test test;
    private Object to;
    private final String executeBeforeEachMethodName = "executeBeforeEach";
    private final String executeAfterEachMethodName = "executeAfterEach";
    private final String executeMethodName;

    public TestExecutor(Test test) {
        this.test = test;
        executeMethodName = test.getId() + "Execute";
    }

    @RuntimeType
    public void intercept(@This Object to) {
        this.to = to;
        System.out.println("Executing test: " + test.getId());

        try {
            executeMethod(executeBeforeEachMethodName, test.getActions());
            executeInternal(test.getActions(), null, to);

        } finally {
            executeMethod(executeAfterEachMethodName, test.getActions());
        }
    }

    private void executeMethod(String methodName, Set<Action> actions) {
        Method executionMethod;
        try {
            executionMethod = to.getClass().getMethod(methodName, Set.class);
            executionMethod.invoke(to, test.getActions());
        } catch (NoSuchMethodException ex) {
            // do nothing
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Logger.getLogger(TestExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void executeInternal(Set<Action> actions, Page page, final Object to) {

        for (Action action : actions) {
            action.setPage(page);
            System.out.println("Executing: " + action);

            if (action.getTemplate() != null) {
                System.out.println("Using template: " + action.getTemplate());
                executeInternal(Site.getTemplates().getById(action.getTemplate()).getActions(), page, to);
                continue;
            }

            try {

                Method executionMethod = to.getClass().getMethod(executeMethodName, Action.class);
                executionMethod.invoke(to, action);
            } catch (NoSuchMethodException e) {
                action.execute(page);
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(TestExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }

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
