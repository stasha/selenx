package info.stasha.selenx.tags;

/**
 *
 * @author stasha
 */
public interface Extensible<T> {

    void setExtends(T extendsLayout);

    T getExtends();
}
