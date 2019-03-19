package info.stasha.selenx.actions;

import static io.github.seleniumquery.SeleniumQuery.$;
import io.github.seleniumquery.SeleniumQueryFluentFunction;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;

/**
 *
 * @author stasha
 */
public class Wait extends Action<Wait> {

    String timeout;
    String until;
    String attr;
    String value;

    private long elapsed = 0;

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    @Override
    public void execute() throws InterruptedException {
        Long to = timeout == null || timeout.isEmpty() ? 10000 : Long.parseLong(timeout.trim());
        if (getSelector() == null && to > -1) {
            Thread.sleep(to);
            return;
        }

        SeleniumQueryFluentFunction func = $(getSelector()).waitUntil(to);

        String action = getAction();
        action = action == null ? "" : action;

        switch (action.toUpperCase()) {
            case "VISIBLE":
                func.isVisible();
                break;
            case "!VISIBLE":
                func.isNotVisible();
                break;
            case "EMPTY":
                while (true) {
                    System.out.println(getWebElement().getAttribute("outerHTML"));
                    if ($(getSelector()).children().size() == 0) {
                        break;
                    }
                    Thread.sleep(100);
                    elapsed += 100;
                    if (elapsed > to) {
                        throw new TimeoutException("Timeout while waiting for " + CURRENT_SELECTOR.get() + " to be empty.");
                    }
                }
                break;
            case "!EMPTY":
                while (true) {
                    if ($(getSelector()).children().size() > 0) {
                        break;
                    }
                    Thread.sleep(100);
                    elapsed += 100;
                    if (elapsed > to) {
                        throw new TimeoutException("Timeout while waiting for " + CURRENT_SELECTOR.get() + " not to be empty.");
                    }
                }
                break;
            case "EQUALS":
                func.html().isEqualTo(getValue());
                break;
            case "CONTAINS":
                func.html().contains(getValue());
                break;
            case "HASCLASS":
                func.attr("class").contains(getValue());
                break;
            case "!HASCLASS":
                func.attr("class").not().contains(getValue());
                break;
            case "HASATTRIBUTE":
                while (true) {
                    JavascriptExecutor js = (JavascriptExecutor) $.driver().get();
                    Map<String, Object> attributes = (Map<String, Object>) js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", getWebElement());

                    if (attributes.containsKey(getValue())) {
                        break;
                    }
                    Thread.sleep(100);
                    elapsed += 100;
                    if (elapsed > to) {
                        throw new TimeoutException("Timeout while waiting for " + CURRENT_SELECTOR.get() + " to have attribute " + getValue());
                    }
                }
                break;
            case "!HASATTRIBUTE":
                while (true) {
                    JavascriptExecutor js = (JavascriptExecutor) $.driver().get();
                    Map<String, Object> attributes = (Map<String, Object>) js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", getWebElement());

                    if (!attributes.containsKey(getValue())) {
                        break;
                    }
                    Thread.sleep(100);
                    elapsed += 100;
                    if (elapsed > to) {
                        throw new TimeoutException("Timeout while waiting for " + CURRENT_SELECTOR.get() + " to doesn't have attribute " + getValue());
                    }
                }
                break;
            case "HASPROPERTY":
                while (true) {
                    JavascriptExecutor js = (JavascriptExecutor) $.driver().get();
                    List attributes = (List<String>) js.executeScript("var items = []; for (var i in arguments[0]) { items.push(i) }; return items;", getWebElement());

                    if (attributes.contains(getValue())) {
                        break;
                    }
                    Thread.sleep(100);
                    elapsed += 100;
                    if (elapsed > to) {
                        throw new TimeoutException("Timeout while waiting for " + CURRENT_SELECTOR.get() + " to doesn't have attribute " + getValue());
                    }
                }
                break;
            case "!HASPROPERTY":
                while (true) {
                    JavascriptExecutor js = (JavascriptExecutor) $.driver().get();
                    List attributes = (List<String>) js.executeScript("var items = []; for (var i in arguments[0]) { items.push(i) }; return items;", getWebElement());

                    if (!attributes.contains(getValue())) {
                        break;
                    }
                    Thread.sleep(100);
                    elapsed += 100;
                    if (elapsed > to) {
                        throw new TimeoutException("Timeout while waiting for " + CURRENT_SELECTOR.get() + " to doesn't have attribute " + getValue());
                    }
                }
                break;
            case "":
                Thread.sleep(to);
                break;
            default:
                throw new UnsupportedOperationException("Wait action: " + getAction() + " is not supported!");
        }
    }

}
