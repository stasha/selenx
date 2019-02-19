package info.stasha.selenx.junit4;

import static io.github.seleniumquery.SeleniumQuery.$;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.WebDriver;

/**
 * Junit4 Test listener.
 *
 * @author stasha
 */
public class ExecutionListener extends RunListener {
    
    public static WebDriver driver = null;

    @Override
    public void testRunStarted(Description description) throws Exception {
        if(driver == null){
            $.driver().useFirefox();
            driver = $.driver().get();
        }
    }

    public ExecutionListener() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            $.driver().quit();
        }));
    }

}
