package info.stasha.selenx.actions;

import info.stasha.selenx.tags.CSSSelector;
import info.stasha.selenx.tags.Page;
import info.stasha.selenx.tags.XPathSelector;

/**
 *
 * @author stasha
 */
public abstract class Action implements Executable, CSSSelector<Action>, XPathSelector<Action> {

    private String el;
    private String css;
    private String xp;
    private String template;
    private String returns;

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

    public String getSelector(Page page) {
        if (this.getEl() != null) {
            return page.getSelectors().get(this.getEl());
        } else if (this.getXp() != null){
            return page.getSelectors().get(this.getXp());
        }

        return this.getCss();
    }

    @Override
    public String toString() {
        return "{" + this.getClass().getSimpleName() + ": el=" + el + ", css=" + css + ", template=" + template + ", returns=" + returns + '}';
    }

}
