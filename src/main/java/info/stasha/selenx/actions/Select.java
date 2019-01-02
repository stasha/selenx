package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Select extends Action {

    @Override
    public void execute(Page page) {
        String value = getValue() == null ? "true" : "false";
        String tagName = $(getSelector(page)).get(0).getTagName().toUpperCase();
        String type = $(getSelector(page)).attr("type").toUpperCase();
        switch (value.toLowerCase()) {
            case "true":
                if (type.equals("CHECKBOX")) {
                    if (!$(getSelector(page)).is(":checked")) {
                        $(getSelector(page)).click();
                    }
                }
                break;
            case "false":

                if (type.equals("CHECKBOX")) {
                    if ($(getSelector(page)).is(":checked")) {
                        $(getSelector(page)).click();
                    }
                }
            default:
            // select other elements
        }
    }

}
