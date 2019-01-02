package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Keyboard extends Action {

    @Override
    public void execute(Page page) {
        $(getSelector(page)).val(getValue());
    }

}
