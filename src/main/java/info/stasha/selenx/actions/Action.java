package info.stasha.selenx.actions;

import info.stasha.selenx.tags.CSSSelector;
import info.stasha.selenx.tags.Element;
import info.stasha.selenx.tags.Page;
import info.stasha.selenx.tags.XPathSelector;
import static io.github.seleniumquery.SeleniumQuery.$;
import org.openqa.selenium.WebElement;

/**
 *
 * @author stasha
 */
public abstract class Action<T> implements Executable, CSSSelector<T>, XPathSelector<T> {

    public static final ThreadLocal<String> CURRENT_SELECTOR = new ThreadLocal<String>();

    private String action;
    private String value;
    private String el;
    private String css;
    private String xp;
    private String template;
    private String returns;
    private Page page;
    private String id;

    public String getAction() {
        if (action != null) {
            return action.trim().toLowerCase();
        }
        return action;
    }

    public T setAction(String action) {
        this.action = action;
        return (T) this;
    }

    public String getValue() {
        return value;
    }

    public T setValue(String value) {
        this.value = value;
        return (T) this;
    }

    public String getEl() {
        return el;
    }

    public T setEl(String el) {
        this.el = el;
        return (T) this;
    }

    @Override
    public String getCss() {
        return css;
    }

    @Override
    public T setCss(String css) {
        this.css = css;
        return (T) this;
    }

    @Override
    public String getXp() {
        return this.xp;
    }

    @Override
    public T setXp(String xp) {
        this.xp = xp;
        return (T) this;
    }

    public String getTemplate() {
        return template;
    }

    public T setTemplate(String template) {
        this.template = template;
        return (T) this;
    }

    public String getReturns() {
        if (this.returns != null) {
            return this.returns;
        }
        Element el = getElement();
        if (el != null) {
            return getElement().getReturns();
        }
        return null;
    }

    public T setReturns(String returns) {
        this.returns = returns;
        return (T) this;
    }

    public Page getPage() {
        return page;
    }

    public T setPage(Page page) {
        this.page = page;
        return (T) this;
    }

    public Element getElement() {
        if (this.getEl() != null) {
            return page.getElementsMap().get(this.getEl());
        } else if (this.getXp() != null) {
            return page.getElementsMap().get(this.getXp());
        }

        return null;
    }

    public WebElement getWebElement() {
        if (getSelector() != null) {
            return $(getSelector()).get(0);
        }

        Element e = getElement();
        if (e != null) {
            return $(e.getSelector()).get(0);
        }

        WebElement el = $.driver().get().switchTo().activeElement();
        if (el != null) {
            return el;
        }

        return $("body").get(0);
    }

    public String getSelector() {
        String se = null;
        if (this.getCss() != null) {
            se = this.getCss();
        } else if (this.getXp() != null) {
            se = this.getXp();
        }

        if (se == null) {
            se = CURRENT_SELECTOR.get();
        }

        CURRENT_SELECTOR.set(se);

        return se;
    }

    public String getSelector(String selector) {
        if (selector == null) {
            return selector;
        }

        String val = page.getElementsMap().get(selector).getSelector();
        if (val != null) {
            return val;
        }

        return selector;
    }

    public String getId() {
        return id;
    }

    public T setId(String id) {
        this.id = id;
        return (T) this;
    }

    @Override
    public String toString() {
        return "{" + this.getClass().getSimpleName() + ": id=" + id + ", el=" + el + ", css=" + css + ", template=" + template + ", returns=" + returns + '}';
    }

}
