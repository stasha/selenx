package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Expected extends Action {

    private String type = "text";
    private String value;

    public String getType() {
        return type;
    }

    public Expected setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Expected setValue(String value) {
        this.value = value;
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
