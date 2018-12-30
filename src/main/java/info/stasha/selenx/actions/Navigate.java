package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import info.stasha.selenx.tags.Site;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Navigate extends Action {

    private String url;

    public String getUrl() {
        if(this.url == null){
            throw new IllegalStateException("url must be set!");
        }
        if (this.url.startsWith("http")) {
            return url;
        } else {
            return (Site.getBaseUri() + url).replace("//", "/");
        }
    }

    public Navigate setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public void execute(Page page) {
        $.url(getUrl());
    }

    @Override
    public String toString() {
        return "Navigate{" + "url=" + getUrl() + '}';
    }

}
