package info.stasha.selenx.tags;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author stasha
 */
public class Page extends Root implements Extensible<String> {

    private String layout;
    private String extendsPage;

    private Map<String, String> selectors = new LinkedHashMap<>();

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    @Override
    public void setExtends(String extendsPage) {
        this.extendsPage = extendsPage;
    }

    @Override
    public String getExtends() {
        return this.extendsPage;
    }

    public Map<String, String> getSelectors() {
        return selectors;
    }

    public void setSelectors(Map<String, String> selectors) {
        this.selectors = selectors;
    }

    @Override
    public String toString() {
        return "Page{id=" + getId() + ", " + ", layout=" + layout + '}';
    }

}
