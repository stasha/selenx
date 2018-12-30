package info.stasha.selenx.junit4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import info.stasha.selenx.TestInstrumentation;
import info.stasha.selenx.XmlParser;
import info.stasha.selenx.actions.Action;
import info.stasha.selenx.actions.Mouse;
import info.stasha.selenx.actions.Expected;
import info.stasha.selenx.actions.ExpectedConverter;
import info.stasha.selenx.actions.Navigate;
import info.stasha.selenx.actions.Select;
import info.stasha.selenx.actions.Type;
import info.stasha.selenx.annotations.Configuration;
import info.stasha.selenx.tags.Id;
import info.stasha.selenx.tags.Import;
import info.stasha.selenx.tags.Site;
import info.stasha.selenx.tags.Test;
import info.stasha.selenx.tags.TestClass;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test runner for running JerseyRequestTests.
 *
 * @author stasha
 */
public class SelenxRunner extends BlockJUnit4ClassRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelenxRunner.class);
    private static final String ROOT = "/home/stasha/projects/stasha/pageobjectfluentapi/src/test/java/info/stasha/pageobject/";

    protected Class<?> testClass;
    protected Class<?> cls;

    public SelenxRunner(Class<?> clazz) throws Throwable {
        super(TestInstrumentation.testClass(clazz, getTests(clazz)));
        this.testClass = clazz;
    }

    private static TestClass getTests(Class<?> clazz) throws IOException {
        Configuration config = clazz.getAnnotation(Configuration.class);

        Site site = Site.init(ROOT);

        XStream xstr = XmlParser.getParser();

        String str = FileUtils.readFileToString(new File(config.xmlTest()), "UTF-8");
        return (TestClass) xstr.fromXML(str);
    }

    @Override
    protected Description describeChild(FrameworkMethod method) {
        return Description.createTestDescription(this.testClass, testName(method), method.getAnnotations());
    }

    @Override
    public void run(RunNotifier notifier) {
        RunListener l = new ExecutionListener();
        notifier.addListener(l);
        notifier.fireTestRunStarted(getDescription());
        super.run(notifier);
        notifier.removeListener(l);
    }

}
