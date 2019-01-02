package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Expected extends Action {

    private String type = "text";

    public String getType() {
        return type;
    }

    public Expected setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public void execute(Page page) {
        switch (type.toLowerCase()) {
            case "text":
                $(getSelector(page)).assertThat().html().isEqualTo(getValue());
                break;
        }
    }

}
