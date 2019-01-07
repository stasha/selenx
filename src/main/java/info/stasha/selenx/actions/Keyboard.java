package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author stasha
 */
public class Keyboard extends Action<Keyboard> {

    public static final String KEYDOWN = "keydown";
    public static final String KEYUP = "keyup";
    public static final String TYPE = "type";

    private Keys getKeys() {
        switch (getValue().toUpperCase()) {
            case "SHIFT":
                return Keys.SHIFT;
            case "ALT":
                return Keys.ALT;
            case "CTRL":
                return Keys.CONTROL;
            default:
                throw new UnsupportedOperationException("Action for key " + getValue() + " is not supported.");
        }
    }

    @Override
    public void execute() {
        Page page = getPage();
        Actions action = new Actions($.driver().get());
        WebElement element = $(getSelector()).get(0);

        switch (getAction()) {
            case KEYDOWN:
                if (element == null) {
                    action.keyDown(getKeys()).build().perform();
                } else {
                    action.keyDown(element, getKeys()).build().perform();
                }
                break;
            case KEYUP:
                if (element == null) {
                    action.keyUp(getKeys()).build().perform();
                } else {
                    action.keyUp(element, getKeys()).build().perform();
                }
                break;
            case TYPE:
                $(element).val(getValue());
                break;

            default:
                throw new UnsupportedOperationException("Action: " + getAction() + " is not supported!");
        }

    }

}
