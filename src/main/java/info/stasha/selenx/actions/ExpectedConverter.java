package info.stasha.selenx.actions;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 *
 * @author stasha
 */
public class ExpectedConverter implements Converter {

    @Override
    public void marshal(Object expected, HierarchicalStreamWriter writer, MarshallingContext mc) {

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext uc) {
        Expected e = new Expected();
        e.setCss(reader.getAttribute("css"));
        e.setXp(reader.getAttribute("xp"));
        e.setEl(reader.getAttribute("el"));
        e.setReturns(reader.getAttribute("return"));
        String prop = reader.getAttribute("prop");
        e.setAttr(reader.getAttribute("attr"));
        e.setProp(reader.getAttribute("prop"));
        e.setTemplate(reader.getAttribute("template"));
        e.setAction(reader.getAttribute("action"));

        // getValue must be last otherwise strange exception will be thrown
        String attrValue = reader.getAttribute("value");
        String value = reader.getValue();
        if (value != null) {
            value = value.trim();
        }
        attrValue = attrValue == null ? value : attrValue;
        e.setValue(attrValue);
        return e;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.getTypeName().equals(Expected.class.getTypeName());
    }

}
