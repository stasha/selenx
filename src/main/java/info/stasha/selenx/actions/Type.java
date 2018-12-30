package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Type extends Action {

    private String value;

    public String getValue() {
        return value;
    }

    public Type setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public void execute(Page page) {
        $(getSelector(page)).val(getValue());
    }

}
