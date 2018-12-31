package info.stasha.selenx.junit4;

import com.thoughtworks.xstream.XStream;
import info.stasha.selenx.TestInstrumentation;
import info.stasha.selenx.XmlParser;
import info.stasha.selenx.tags.Site;
import info.stasha.selenx.tags.TestClass;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
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

    protected Class<?> testClass;
    protected Class<?> cls;

    public SelenxRunner(Class<?> clazz) throws Throwable {
        super(TestInstrumentation.testClass(clazz, getTests(clazz)));
        this.testClass = clazz;
    }

    private static TestClass getTests(Class<?> clazz) throws IOException {
        Site site = Site.init();

        XStream xstr = XmlParser.getParser();
        Path dir = Paths.get(System.getProperty("user.dir"), "src");
        
        final Path p;
        try (Stream<Path> paths = Files.find(
                dir, Integer.MAX_VALUE,
                (path, attrs) -> attrs.isRegularFile()
                && path.endsWith(clazz.getName().replace(".", File.separator) + ".xml"))) {
            p = paths.findAny().get();
        }
        String str = FileUtils.readFileToString(p.toFile(), "UTF-8");
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
