package info.stasha.selenx;

import static io.github.seleniumquery.SeleniumQuery.$;
import org.openqa.selenium.WebElement;

/**
 *
 * @author stasha
 */
public final class Point {

    private int x;
    private int y;
    WebElement element;

    public Point(String value) {
        if (value.contains(",")) {
            String[] val = value.split(",");
            setX(Integer.parseInt(val[0].trim()));
            setY(Integer.parseInt(val[1].trim()));
        } else {
            setElement($(value).get(0));
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }

    public boolean isPoint() {
        if (this.element != null) {
            return false;
        }

        return true;
    }

}
