package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;
import io.github.seleniumquery.SeleniumQueryFluentFunction;

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
        Page page = getPage();
        Long to = timeout == null || timeout.isEmpty() ? -1 : Long.parseLong(timeout);
        String selector = getSelector();
        if (selector == null && to > -1) {
                Thread.sleep(to);
                return;
        }
        
        SeleniumQueryFluentFunction func = $(getSelector()).waitUntil(to);

        switch (until.toUpperCase()) {
            case "ISEMPTY":
                func.isEmpty();
                break;
            case "ISHIDDEN":
                func.isHidden();
                break;
            case "ISPRESENT":
                func.isPresent();
                break;
            case "ISVISIBLE":
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
