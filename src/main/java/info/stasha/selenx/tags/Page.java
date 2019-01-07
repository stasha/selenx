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

    private Map<String, Element> elementsMap = new LinkedHashMap<>();

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

    public Map<String, Element> getElementsMap() {
        return elementsMap;
    }

    public void setElementsMap(Map<String, Element> elementsMap) {
        this.elementsMap = elementsMap;
    }

    @Override
    public String toString() {
        return "Page{id=" + getId() + ", " + ", layout=" + layout + '}';
    }

}
