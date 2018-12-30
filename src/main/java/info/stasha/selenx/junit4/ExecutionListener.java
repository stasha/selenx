package info.stasha.selenx.junit4;

import info.stasha.selenx.tags.Site;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import static io.github.seleniumquery.SeleniumQuery.$;

/**
 * Junit4 Test listener.
 *
 * @author stasha
 */
public class ExecutionListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        $.driver().useFirefox();
    }

    public ExecutionListener() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            $.driver().quit();
        }));
    }

}
