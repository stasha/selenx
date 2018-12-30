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
        e.setType(reader.getAttribute("type"));
        e.setTemplate(reader.getAttribute("template"));

        // getValue must be last otherwise strange exception will be thrown
        e.setValue(reader.getValue().trim());
        return e;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.getTypeName().equals(Expected.class.getTypeName());
    }

}
