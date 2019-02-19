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
import java.util.LinkedHashSet;
import info.stasha.selenx.annotations.ExecuteBeforeEach;
import info.stasha.selenx.annotations.ExecuteAfterEach;
import info.stasha.selenx.annotations.Intercept;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author stasha
 */
public class TestExecutor {

    private final Test test;
    private Object to;
    private final Set<Method> befores = new LinkedHashSet<>();
    private final Set<Method> afters = new LinkedHashSet<>();
    private final Map<String, Method> interceptors = new LinkedHashMap<>();

    public TestExecutor(Test test) {
        this.test = test;
    }

    @RuntimeType
    public void intercept(@This Object to) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
        this.to = to;
        System.out.println("Executing test: " + test.getId());

        

        for (Method m : to.getClass().getMethods()) {
            if (m.isAnnotationPresent(ExecuteBeforeEach.class)) {
                befores.add(m);
            }
            if (m.isAnnotationPresent(ExecuteAfterEach.class)) {
                afters.add(m);
            }
            if (m.isAnnotationPresent(Intercept.class)) {
                Intercept i = m.getAnnotation(Intercept.class);
                if (m.getParameters().length == 1 && m.getParameters()[0].getType().getTypeName().equals(Action.class.getName())) {
                    interceptors.put(i.id(), m);
                }
            }
        }

        try {
            for (Method m : befores) {
                m.invoke(to, test);
            }
            executeInternal(test.getActions(), null, to);

        } finally {
            for (Method m : afters) {
                m.invoke(to, test);
            }
        }
    }

    private void executeInternal(Set<Action> actions, Page page, final Object to) throws Exception {

        for (Action action : actions) {
            action.setPage(page);
            System.out.println("Executing: " + action);

            if (action.getTemplate() != null) {
                System.out.println("Using template: " + action.getTemplate());
                executeInternal(Site.getTemplates().getById(action.getTemplate()).getActions(), page, to);
                continue;
            }

            boolean execute = true;

            try {
                Method executionMethod = interceptors.get(test.getId());
                if (executionMethod != null) {
                    Intercept intercept = executionMethod.getAnnotation(Intercept.class);

                    if (intercept != null && intercept.id().equals(test.getId())) {
                        for (String a : intercept.actions()) {
                            if (a.equals(action.getId())) {
                                execute = (boolean) executionMethod.invoke(to, action);
                                break;
                            }
                        }
                    } else {
                        execute = (boolean) executionMethod.invoke(to, action);
                    }
                }

            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(TestExecutor.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (execute) {
                    action.execute();
                }
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
