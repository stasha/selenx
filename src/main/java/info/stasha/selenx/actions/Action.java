package info.stasha.selenx.actions;

import info.stasha.selenx.tags.CSSSelector;
import info.stasha.selenx.tags.Page;
import info.stasha.selenx.tags.XPathSelector;

/**
 *
 * @author stasha
 */
public abstract class Action implements Executable, CSSSelector<Action>, XPathSelector<Action> {

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
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEl() {
        return el;
    }

    public Action setEl(String el) {
        this.el = el;
        return this;
    }

    @Override
    public String getCss() {
        return css;
    }

    @Override
    public Action setCss(String css) {
        this.css = css;
        return this;
    }

    @Override
    public String getXp() {
        return this.xp;
    }

    @Override
    public Action setXp(String xp) {
        this.xp = xp;
        return this;
    }

    public String getTemplate() {
        return template;
    }

    public Action setTemplate(String template) {
        this.template = template;
        return this;
    }

    public String getReturns() {
        return returns;
    }

    public Action setReturns(String returns) {
        this.returns = returns;
        return this;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getSelector(Page page) {
        if (this.getEl() != null) {
            return page.getSelectors().get(this.getEl());
        } else if (this.getXp() != null) {
            return page.getSelectors().get(this.getXp());
        }

        return this.getCss();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + this.getClass().getSimpleName() + ": id=" + id + ", el=" + el + ", css=" + css + ", template=" + template + ", returns=" + returns + '}';
    }

}
