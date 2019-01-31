package info.stasha.selenx;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import info.stasha.selenx.actions.Action;
import info.stasha.selenx.actions.Mouse;
import info.stasha.selenx.actions.Expected;
import info.stasha.selenx.actions.ExpectedConverter;
import info.stasha.selenx.actions.Browser;
import info.stasha.selenx.actions.Keyboard;
import info.stasha.selenx.actions.Wait;
import info.stasha.selenx.actions.WaitConverter;
import info.stasha.selenx.tags.Element;
import info.stasha.selenx.tags.Id;
import info.stasha.selenx.tags.Import;
import info.stasha.selenx.tags.Layout;
import info.stasha.selenx.tags.Page;
import info.stasha.selenx.tags.PageObject;
import info.stasha.selenx.tags.Root;
import info.stasha.selenx.tags.Site;
import info.stasha.selenx.tags.Template;
import info.stasha.selenx.tags.Test;
import info.stasha.selenx.tags.TestClass;

/**
 *
 * @author stasha
 */
public class XmlParser {

    public static XStream getParser() {
        XStream xstr = new XStream(new StaxDriver());

        // site
        xstr.alias("site", Site.class);
        xstr.alias("layouts", Site.class);
        xstr.alias("templates", Site.class);
        xstr.alias("pages", Site.class);

        // tags
        xstr.alias("import", Import.class);
        xstr.alias("layout", Layout.class);
        xstr.alias("template", Template.class);
        xstr.alias("page", Page.class);
        xstr.alias("po", PageObject.class);
        xstr.alias("el", Element.class);
        xstr.alias("mouse", Mouse.class);
        xstr.alias("keyboard", Keyboard.class);
        xstr.alias("expected", Expected.class);
        xstr.alias("tests", TestClass.class);
        xstr.alias("test", Test.class);
        xstr.alias("browser", Browser.class);
        xstr.alias("wait", Wait.class);

        // tag attributes
        xstr.aliasAttribute(Site.class, "baseUri", "baseUri");
        xstr.aliasAttribute(Id.class, "id", "id");
        xstr.aliasAttribute(Import.class, "file", "file");

        // page object tag attributes
        xstr.aliasAttribute(PageObject.class, "css", "css");
        xstr.aliasAttribute(PageObject.class, "xp", "xp");

        // element tag attributes
        xstr.aliasAttribute(Element.class, "css", "css");
        xstr.aliasAttribute(Element.class, "xp", "xp");
        xstr.aliasAttribute(Element.class, "returns", "return");

        // layout tag attributes
        xstr.aliasAttribute(Layout.class, "defaultLayout", "default");
        xstr.aliasAttribute(Layout.class, "extendsLayout", "extends");

        // template tag attributes
        xstr.aliasAttribute(Template.class, "extendsTemplate", "extends");

        // page tag attributes
        xstr.aliasAttribute(Page.class, "extendsPage", "extends");
        xstr.aliasAttribute(Page.class, "layout", "layout");

        // action tag attributes
        xstr.aliasAttribute(Action.class, "id", "id");
        xstr.aliasAttribute(Action.class, "action", "action");
        xstr.aliasAttribute(Action.class, "value", "value");
        xstr.aliasAttribute(Action.class, "css", "css");
        xstr.aliasAttribute(Action.class, "xp", "xp");
        xstr.aliasAttribute(Action.class, "el", "el");
        xstr.aliasAttribute(Action.class, "template", "template");
        xstr.aliasAttribute(Action.class, "returns", "return");

        // expected tag attributes
        xstr.aliasAttribute(Expected.class, "attr", "attr");
        xstr.aliasAttribute(Expected.class, "prop", "prop");

        // action specific attributes
        xstr.aliasAttribute(Test.class, "actions", "action");

        // collections
        xstr.addImplicitCollection(Site.class, "imports", Import.class);
        xstr.addImplicitCollection(Site.class, "layouts", Layout.class);
        xstr.addImplicitCollection(Site.class, "templates", Template.class);
        xstr.addImplicitCollection(Site.class, "pages", Page.class);
        xstr.addImplicitCollection(Root.class, "elements", Element.class);
        xstr.addImplicitCollection(Root.class, "pageObjects", PageObject.class);
        xstr.addImplicitCollection(Template.class, "actions", Action.class);
        xstr.addImplicitCollection(TestClass.class, "tests", Test.class);
        xstr.addImplicitCollection(Test.class, "actions", Action.class);

        xstr.registerConverter(new ExpectedConverter());
        xstr.registerConverter(new WaitConverter());

        return xstr;
    }
}
