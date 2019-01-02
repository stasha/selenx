package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Mouse extends Action {

    @Override
    public void execute(Page page) {
        switch (getAction().toUpperCase()) {
            case "CLICK":
                $(getSelector(page)).click();
                break;
            case "DOUBLECLICK":
                $(getSelector(page)).dblclick();
                break;
            default:
                throw new UnsupportedOperationException("Action: " + getAction() + " is not supported!");
        }
    }

}
