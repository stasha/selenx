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
public class WaitConverter implements Converter {

    @Override
    public void marshal(Object expected, HierarchicalStreamWriter writer, MarshallingContext mc) {

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext uc) {
        Wait e = new Wait();
        e.setCss(reader.getAttribute("css"));
        e.setXp(reader.getAttribute("xp"));
        e.setEl(reader.getAttribute("el"));
        e.setReturns(reader.getAttribute("return"));
        e.setTemplate(reader.getAttribute("template"));
        
        e.setAttr(reader.getAttribute("attr"));
        e.setTimeout(reader.getAttribute("timeout"));
        e.setUntil(reader.getAttribute("until"));

        // getValue must be last otherwise strange exception will be thrown
        e.setValue(reader.getValue().trim());
        return e;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.getTypeName().equals(Wait.class.getTypeName());
    }

}
