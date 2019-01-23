package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Select extends Action {

    @Override
    public void execute() {
        Page page = getPage();
        String value = getValue() == null ? "true" : "false";
        String tagName = $(getWebElement()).get(0).getTagName().toUpperCase();
        String type = $(getWebElement()).attr("type").toUpperCase();
        switch (value.toLowerCase()) {
            case "true":
                if (type.equals("CHECKBOX")) {
                    if (!$(getWebElement()).is(":checked")) {
                        $(getWebElement()).click();
                    }
                }
                break;
            case "false":

                if (type.equals("CHECKBOX")) {
                    if ($(getWebElement()).is(":checked")) {
                        $(getWebElement()).click();
                    }
                }
            default:
            // select other elements
        }
    }

}
