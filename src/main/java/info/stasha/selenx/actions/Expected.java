package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 *
 * @author stasha
 */
public class Expected extends Action {

    private String type = "text";
    private String attr = "html";

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getType() {
        return type;
    }

    public Expected setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public void execute() {
        Page page = getPage();
        switch (type.toLowerCase()) {
            case "text":
                switch (attr.toUpperCase()) {
                    case "HTML":
                        $(getSelector()).assertThat().html().isEqualTo(getValue());
                        break;
                    case "VALUE":
                        $(getSelector()).assertThat().val().isEqualTo(getValue());

                }
                break;
        }
    }

}
