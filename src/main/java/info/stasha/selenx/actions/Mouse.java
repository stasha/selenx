package info.stasha.selenx.actions;

import info.stasha.selenx.Point;
import static io.github.seleniumquery.SeleniumQuery.$;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.internal.Locatable;

/**
 *
 * @author stasha
 */
public class Mouse extends Action<Mouse> {

    public static final String HOVER = "hover";
    public static final String PRESS = "press";
    public static final String RELEASE = "release";
    public static final String MOVE = "move";
    public static final String CLICK = "click";
    public static final String DOUBLECLICK = "doubleclick";
    public static final String CONTEXTCLICK = "contextclick";
    public static final String DRAGNDROP = "dragndrop";

    @Override
    public void execute() {
        Actions action = new Actions($.driver().get());
        WebElement element = getWebElement();
        Locatable hoverItem = (Locatable) element;
        org.openqa.selenium.interactions.Mouse mouse = ((HasInputDevices) $.driver().get()).getMouse();

        switch (getAction()) {
            case HOVER:
                mouse.mouseMove(hoverItem.getCoordinates(), 1, 1);
                break;
            case PRESS:
                mouse.mouseMove(hoverItem.getCoordinates());
                action.clickAndHold(element).build().perform();
                break;
            case RELEASE:
                mouse.mouseMove(hoverItem.getCoordinates());
                mouse.click(hoverItem.getCoordinates());
                break;
            case MOVE:
                Point p = new Point(getValue());
                mouse.mouseMove(hoverItem.getCoordinates(), p.getX(), p.getY());
                break;
            case CLICK:
                mouse.mouseMove(hoverItem.getCoordinates());
                $(element).click();
                break;
            case DOUBLECLICK:
                mouse.mouseMove(hoverItem.getCoordinates());
                $(element).dblclick();
                break;
            case CONTEXTCLICK:
                mouse.mouseMove(hoverItem.getCoordinates());
                action.contextClick(element).build().perform();
                break;
            case DRAGNDROP:
                mouse.mouseMove(hoverItem.getCoordinates());
                Point point = new Point(getValue());
                if (point.isPoint()) {
                    action.dragAndDropBy(element, point.getX(), point.getY()).build().perform();
                } else {
                    WebElement destination = $(getValue()).get(0);
                    action.dragAndDrop(element, destination).build().perform();
                }
                break;
            default:
                throw new UnsupportedOperationException("Mouse action: " + getAction() + " is not supported!");
        }
    }

}
