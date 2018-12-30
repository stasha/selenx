package info.stasha.selenx.tags;

/**
 *
 * @author stasha
 */
public class Layout extends Root implements Extensible<String> {

    private String extendsLayout;
    private boolean defaultLayout;

    @Override
    public void setExtends(String extendsLayout) {
        this.extendsLayout = extendsLayout;
    }

    @Override
    public String getExtends() {
        return this.extendsLayout;
    }

    public boolean isDefaultLayout() {
        return defaultLayout;
    }

    public void setDefaultLayout(boolean defaultLayout) {
        this.defaultLayout = defaultLayout;
    }

    @Override
    public String toString() {
        return "Layout{id=" + getId() + ", " + "extendsLayout=" + extendsLayout + ", defaultLayout=" + defaultLayout + '}';
    }

}
