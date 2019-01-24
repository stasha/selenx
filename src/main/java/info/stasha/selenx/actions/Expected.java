package info.stasha.selenx.actions;

import static io.github.seleniumquery.SeleniumQuery.$;
import io.github.seleniumquery.SeleniumQueryFluentFunctionEvaluateIf;

/**
 *
 * @author stasha
 */
public class Expected extends Action {

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
    
    private String getElementToHtml(){
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
            case "STARTS_WITH":
                if (!getVal().startsWith(getValue())) {
                    throw new AssertionError("Expected " + getVal() + " to start with " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "!STARTS_WITH":
                if (getVal().startsWith(getValue())) {
                    throw new AssertionError("Expected " + getVal() + " to start with " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "ENDS_WITH":
                if (!getVal().endsWith(getValue())) {
                    throw new AssertionError("Expected " + getVal() + " to end with " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "!ENDS_WITH":
                if (getVal().endsWith(getValue())) {
                    throw new AssertionError("Expected " + getVal() + " to end with " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "HAS_CLASS":
                if (!$(getWebElement()).hasClass(getValue())) {
                    throw new AssertionError("Element doesn't contain class " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "!HAS_CLASS":
                if ($(getWebElement()).hasClass(getValue())) {
                    throw new AssertionError("Element contains class " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "HAS_ATTR":
                if (getWebElement().getAttribute(getVal()) == null) {
                    throw new AssertionError("Element doesn't has attribute " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "!HAS_ATTR":
                if (getWebElement().getAttribute(getVal()) != null) {
                    throw new AssertionError("Element has attribute " + getValue() + ": " + getElementToHtml());
                }
                break;
            case "HAS_ELEMENT":
                if ($(getWebElement()).filter(getValue()).size() == 0) {
                    throw new AssertionError("Element does not contain " + getValue() + " element: " + getElementToHtml());
                }
                break;
            case "!HAS_ELEMENT":
                if ($(getWebElement()).filter(getValue()).size() > 0) {
                    throw new AssertionError("Element contains " + getValue() + " element: " + getElementToHtml());
                }
                break;
            default:
                throw new UnsupportedOperationException("Action " + getAction() + " is not supported.");
        }
    }

}
