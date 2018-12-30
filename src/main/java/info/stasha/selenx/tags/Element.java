package info.stasha.selenx.tags;

/**
 *
 * @author stasha
 */
public class Element extends Id implements CSSSelector<Element>, XPathSelector<Element> {

    private String css;
    private String xp;

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

}
