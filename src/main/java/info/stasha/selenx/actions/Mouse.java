package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Mouse extends Action {

    private String type = "click";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void execute(Page page) {
        switch (type.toUpperCase()) {
            case "DOUBLECLICK":
                $(getSelector(page)).dblclick();
                break;
            default:
                $(getSelector(page)).click();
        }
    }

}
