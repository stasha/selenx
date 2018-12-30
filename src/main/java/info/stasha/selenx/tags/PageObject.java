package info.stasha.selenx.tags;

/**
 *
 * @author stasha
 */
public class PageObject extends Root implements CSSSelector<PageObject>, XPathSelector<PageObject> {

    private String css;
    private String xp;

    @Override
    public String getCss() {
        return this.css;
    }

    @Override
    public PageObject setCss(String css) {
        this.css = css;
        return this;
    }

    @Override
    public String getXp() {
        return this.xp;
    }

    @Override
    public PageObject setXp(String xp) {
        this.xp = xp;
        return this;
    }
}
