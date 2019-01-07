package info.stasha.selenx.tags;

/**
 *
 * @author stasha
 */
public class Element extends Id implements CSSSelector<Element>, XPathSelector<Element> {

    private String css;
    private String xp;
    private String returns;
    private String selector;

    @Override
    public String getCss() {
        return this.css;
    }

    @Override
    public Element setCss(String css) {
        this.css = css;
        return this;
    }

    @Override
    public String getXp() {
        return this.xp;
    }

    @Override
    public Element setXp(String xp) {
        this.xp = xp;
        return this;
    }

    public String getReturns() {
        return this.returns;
    }

    public void setReturns(String returns) {
        this.returns = returns;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

}
