package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.internal.Coordinates;

/**
 *
 * @author stasha
 */
public class Mouse extends Action<Mouse> {

    public static final String HOVER = "hover";
    public static final String PRESS = "press";
    public static final String RELEASE = "release";
    public static final String CLICK = "click";
    public static final String DOUBLECLICK = "doubleclick";
    public static final String CONTEXTCLICK = "contextclick";
    public static final String DRAGNDROP = "dragndrop";
    public static final String SCROLL = "scroll";

    @Override
    public void execute(Page page) {
        Actions action = new Actions($.driver().get());
        WebElement element = $(getSelector(page)).get(0);

        switch (getAction().toUpperCase()) {
            case HOVER:
                action.moveToElement(element).build().perform();
                break;
            case PRESS:
                action.clickAndHold(element).build().perform();
                break;
            case RELEASE:
                org.openqa.selenium.interactions.Mouse mouse = ((HasInputDevices) $.driver()).getMouse();
                mouse.mouseUp((Coordinates) element.getLocation());
                break;
            case CLICK:
                $(getSelector(page)).click();
                break;
            case DOUBLECLICK:
                $(getSelector(page)).dblclick();
                break;
            case CONTEXTCLICK:
                action.contextClick(element);
                break;
            case DRAGNDROP:
                String dest = getSelector(page, getValue());
                String[] point = null;

                if (dest.equals(getValue())) {
                    point = getValue().split(",");
                    if (point.length == 2) {
                        try {
                            int x = Integer.parseInt(point[0]);
                            int y = Integer.parseInt(point[1]);
                            new Actions($.driver().get()).dragAndDropBy(element, x, y).build().perform();
                            return;
                        } catch (NumberFormatException ex) {
                            // do nothing
                        }
                    }
                }

                WebElement destination = $(getSelector(page, getValue())).get(0);
                action.dragAndDrop(element, destination).build().perform();

                break;
            case SCROLL:

                break;
            default:
                throw new UnsupportedOperationException("Action: " + getAction() + " is not supported!");
        }
    }

}
