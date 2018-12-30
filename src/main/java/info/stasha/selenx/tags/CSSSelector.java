package info.stasha.selenx.tags;

/**
 *
 * @author stasha
 */
public interface CSSSelector<T> {

    String getCss();

    T setCss(String css);
}
