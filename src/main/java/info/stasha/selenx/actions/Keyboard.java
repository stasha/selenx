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

    private static Actions action;

    public static final String PRESS = "press";
    public static final String RELEASE = "release";
    public static final String TYPE = "type";
    public static final String APPEND = "append";
    public static final String PREPEND = "prepend";

    private String press;
    private String release;

    private void pressOrRelease(WebElement element, String actionType) {
        String[] keys = press.split(" ");
        for (String key : keys) {
            if (!key.trim().isEmpty()) {
                Keys k = null;
                switch (key.toUpperCase()) {
                    case "SHIFT":
                        k = Keys.SHIFT;
                        break;
                    case "CTRL":
                        k = Keys.CONTROL;
                        break;
                    case "ALT":
                        k = Keys.ALT;
                        break;
                    default:
                        throw new UnsupportedOperationException("Action: " + getAction() + " is not supported!");
                }
                if (k != null) {
                    if ("press".equals(actionType)) {
                        action = action.keyDown(element, k);
                    } else if ("release".equals(actionType)) {
                        action = action.keyUp(element, k);
                    }
                }
            }
        }
    }

    @Override
    public void execute() {
        if (action == null) {
            action = new Actions($.driver().get());
        }

        WebElement element = null;
        String selector = getSelector();
        if (selector != null) {
            element = $(getSelector()).get(0);
            $(element).val("");
        }

        if (press != null) {
            pressOrRelease(element, "press");
        }
        switch (getAction()) {
            case TYPE:
                action = action.sendKeys(getValue());
                break;
            case APPEND:

                break;
            case PREPEND:

                break;

            default:
                throw new UnsupportedOperationException("Action: " + getAction() + " is not supported!");
        }
        if (release != null) {
            pressOrRelease(element, "release");
        }

        action.build().perform();

    }

}
