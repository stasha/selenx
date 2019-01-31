package info.stasha.selenx.actions;

import static io.github.seleniumquery.SeleniumQuery.$;
import io.github.seleniumquery.SeleniumQueryFluentFunction;
import org.openqa.selenium.WebElement;

/**
 *
 * @author stasha
 */
public class Wait extends Action<Wait> {

    String timeout;
    String until;
    String attr;
    String value;

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
        Long to = timeout == null || timeout.isEmpty() ? 1500 : Long.parseLong(timeout.trim());
        if (getSelector() == null && to > -1) {
                Thread.sleep(to);
                return;
        }
        
        SeleniumQueryFluentFunction func = $(getSelector()).waitUntil(to);
        
        String action = getAction();
        action = action == null ? "" : action;

        switch (action.toUpperCase()) {
            case "ISEMPTY":
                func.isEmpty();
                break;
            case "ISHIDDEN":
                func.isHidden();
                break;
            case "PRESENT":
                func.isPresent();
                break;
            case "VISIBLE":
                func.isVisible();
                break;
            case "ISNOTEMPTY":
                func.isNotEmpty();
                break;
            case "ISNOTVISIBLE":
                func.isNotVisible();
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
            case "HASATTRIBUTE":
                func.attr(getAttr()).isEqualTo(getValue());
                break;
            case "HASPROPERTY":
                func.prop(getValue());
                break;
            default:
                func.isDisplayed();
        }
    }

}
