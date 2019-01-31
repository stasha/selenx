package info.stasha.selenx.actions;

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

    /**
     * Presses and then releases functional keys like up, down, f1, tab...
     */
    public static final String PRESS = "press";
    /**
     * Press and hold keys: shift, ctrl, alt
     */
    public static final String HOLD = "hold";
    /**
     * Release hold keys: shift, ctrl, alt
     */
    public static final String RELEASE = "release";
    /**
     * Type normal text
     */
    public static final String TYPE = "type";

    public void holdOrRelease(String a) throws InterruptedException {

        WebElement el = $(getWebElement()).get(0);
        Keys key = null;
        String values[] = getValue().split(",");

        for (String val : values) {
            switch (a) {
                case "hold":
                    action = action.keyDown(el, getKey(val));
                    break;
                case "press":
                    action = action.sendKeys(el, getKey(val));
                    break;
                default:
                    action = action.keyUp(el, getKey(val));
            }
        }
    }

    public Keys getKey(String key) {
        return Keys.valueOf(key.trim().toUpperCase());
    }

    @Override
    public void execute() throws InterruptedException {
        if (action == null) {
            action = new Actions($.driver().get());
        }
        
        if(getSelector() == null || "body".equals(getSelector())){
            $("body").click();
        }

        switch (getAction()) {
            case PRESS:
                holdOrRelease("press");
                break;
            case TYPE:
                WebElement element = getWebElement();
                $(element).val("");
                action = action.sendKeys(element, getValue());
                break;
            case HOLD:
                holdOrRelease("hold");
                break;
            case RELEASE:
                holdOrRelease("release");
                break;

            default:
                throw new UnsupportedOperationException("Action: " + getAction() + " is not supported!");
        }

        action.build().perform();
//        Thread.sleep(500);

    }

}
