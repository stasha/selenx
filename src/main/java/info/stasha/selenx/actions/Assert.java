package info.stasha.selenx.actions;

import static io.github.seleniumquery.SeleniumQuery.$;
import io.github.seleniumquery.SeleniumQueryFluentFunctionEvaluateIf;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;

/**
 *
 * @author stasha
 */
public class Assert extends Action {

    private String attr;
    private String prop;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    @Override
    public String getAction() {
        if (super.getAction() == null) {
            return "EQUALS";
        }
        return super.getAction().trim().toUpperCase();
    }

    private SeleniumQueryFluentFunctionEvaluateIf<String> getAssert() {
        if (getAttr() != null) {
            return $(getWebElement()).assertThat().attr(getAttr());
        } else if (getProp() != null) {
            return $(getWebElement()).assertThat().prop(getProp());
        }

        return $(getWebElement()).assertThat().html();
    }

    private String getVal() {
        if (getAttr() != null) {
            return $(getWebElement()).attr(getAttr());
        } else if (getProp() != null) {
            return $(getWebElement()).prop(getProp());
        }

        return $(getWebElement()).html();
    }

    private String getElementToHtml() {
        return getWebElement().getAttribute("outerHTML");
    }

    @Override
    public void execute() {

        switch (getAction()) {
            case "EQUALS":
                getAssert().isEqualTo(getValue());
                break;
            case "!EQUALS":
                getAssert().not().isEqualTo(getValue());
                break;
            case "CONTAINS":
                getAssert().contains(getValue());
                break;
            case "!CONTAINS":
                getAssert().not().contains(getValue());
                break;
            case "GREATER":
                getAssert().isGreaterThan(Double.parseDouble(getValue()));
                break;
            case "!GREATER":
                getAssert().not().isGreaterThan(Double.parseDouble(getValue()));
                break;
            case "SMALLER":
                getAssert().isLessThan(Double.parseDouble(getValue()));
                break;
            case "!SMALLER":
                getAssert().not().isLessThan(Double.parseDouble(getValue()));
                break;
            case "VISIBLE":
                $(getWebElement()).assertThat().isVisible();
                break;
            case "!VISIBLE":
                $(getWebElement()).assertThat().isNotVisible();
                break;
            case "HIDDEN":
                $(getWebElement()).assertThat().isHidden();
                break;
            case "EMPTY":
                $(getWebElement()).assertThat().isEmpty();
                break;
            case "!EMPTY":
                $(getWebElement()).assertThat().isNotEmpty();
                break;
            case "ENABLED":
                if (!getWebElement().isEnabled()) {
                    throw new AssertionError("Element is not enabled" + ": " + getElementToHtml());
                }
                break;
            case "!ENABLED":
                if (getWebElement().isEnabled()) {
                    throw new AssertionError("Element is enabled" + ": " + getElementToHtml());
                }
                break;
            case "SELECTED":
                if (!getWebElement().isSelected()) {
                    throw new AssertionError("Element is not selected: " + getElementToHtml());
                }
                break;
            case "!SELECTED":
                if (getWebElement().isSelected()) {
                    throw new AssertionError("Element is selected: " + getElementToHtml());
                }
                break;
            case "STARTSWITH":
                if (!getVal().startsWith(getValue())) {
                    throw new AssertionError("Expected " + getVal() + " to start with " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "!STARTSWITH":
                if (getVal().startsWith(getValue())) {
                    throw new AssertionError("Expected " + getVal() + " to start with " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "ENDSWITH":
                if (!getVal().endsWith(getValue())) {
                    throw new AssertionError("Expected " + getVal() + " to end with " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "!ENDSWITH":
                if (getVal().endsWith(getValue())) {
                    throw new AssertionError("Expected " + getVal() + " to end with " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "HASCLASS":
                if (!$(getWebElement()).hasClass(getValue())) {
                    throw new AssertionError("Element doesn't contain class " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "!HASCLASS":
                if ($(getWebElement()).hasClass(getValue())) {
                    throw new AssertionError("Element contains class " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "HASATTRIBUTE":
                JavascriptExecutor js = (JavascriptExecutor) $.driver().get();
                Map<String, Object> attributes = (Map<String, Object>) js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", getWebElement());
                if (!attributes.containsKey(getValue())) {
                    throw new AssertionError("Element doesn't contain attribute " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "!HASATTRIBUTE":
                js = (JavascriptExecutor) $.driver().get();
                attributes = (Map<String, Object>) js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", getWebElement());
                if (attributes.containsKey(getValue())) {
                    throw new AssertionError("Element contains attribute " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "HASPROPERTY":
                js = (JavascriptExecutor) $.driver().get();
                List props = (List<String>) js.executeScript("var items = []; for (var i in arguments[0]) { items.push(i) }; return items;", getWebElement());

                if (!props.contains(getValue())) {
                    throw new AssertionError("Element does not contains property " + getValue() + ": " + getElementToHtml());
                }

                break;
            case "!HASPROPERTY":
                js = (JavascriptExecutor) $.driver().get();
                props = (List<String>) js.executeScript("var items = []; for (var i in arguments[0]) { items.push(i) }; return items;", getWebElement());

                if (props.contains(getValue())) {
                    throw new AssertionError("Element contains property " + getValue() + ": " + getElementToHtml());
                }

                break;
            case "HASELEMENT":
                if ($(getWebElement()).filter(getValue()).size() == 0) {
                    throw new AssertionError("Element does not contain " + getValue() + " element: " + getElementToHtml());
                }
                break;
            case "!HASELEMENT":
                if ($(getWebElement()).filter(getValue()).size() > 0) {
                    throw new AssertionError("Element contains " + getValue() + " element: " + getElementToHtml());
                }
                break;
            case "COUNT":
                int size = $(getSelector()).size();
                if (size != Integer.parseInt(getValue().trim())) {
                    throw new AssertionError("Element count does not equal. Expected " + getValue() + " but was " + size + ": " + getElementToHtml());
                }
                break;
            default:
                throw new UnsupportedOperationException("Action " + getAction() + " is not supported.");
        }
    }

}
