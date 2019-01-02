package info.stasha.selenx.actions;

import info.stasha.selenx.tags.Page;
import info.stasha.selenx.tags.Site;
import static io.github.seleniumquery.SeleniumQuery.$;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author stasha
 */
public class Browser extends Action {

    private String getUrl() {
        String url = getValue();
        if (url == null) {
            url = "about:blank";
        } else if (!url.startsWith("http")) {
            url = (Site.getBaseUri() + url).replace("//", "/").replace(":/", "://");
        }
        return url;
    }

    private String lastTab() {
        String lastTab = null;

        Set<String> handles = $.driver().get().getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            lastTab = iterator.next();
        }
        return lastTab;
    }

    private void openNewTab() {
        ((JavascriptExecutor) $.driver().get()).executeScript("window.open('" + getUrl() + "','_blank');");

        $.driver().get().switchTo().window(lastTab());
    }

    private String switchToTab() {
        WebDriver driver = $.driver().get();
        
        if(getValue() == null || getValue().isEmpty()){
            return driver.getWindowHandle();
        }

        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
            if (getValue().equalsIgnoreCase(driver.getTitle())) {
                return tab;
            }
        }
        return null;
    }

    private void closeTab() {
        String currentTab = $.driver().get().getWindowHandle();
        String closingTab = switchToTab();

        $.driver().get().close();

        if (currentTab.equals(closingTab)) {
            currentTab = lastTab();
        }

        $.driver().get().switchTo().window(currentTab);
    }

    @Override
    public void execute(Page page) {
        String[] pair;
        switch (getAction().toUpperCase()) {
            case "NAVIGATE":
                $.url(getUrl());
                break;
            case "REFRESH":
                $.driver().get().navigate().refresh();
                break;
            case "BACK":
                $.driver().get().navigate().back();
                break;
            case "FORWARD":
                $.driver().get().navigate().forward();
                break;
            case "MAXIMIZE":
                $.maximizeWindow();
                break;
            case "RESIZE":
                pair = getValue().split(",");
                $.driver().get().manage().window().setSize(new Dimension(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
                break;
            case "MOVE":
                pair = getValue().split(",");
                $.driver().get().manage().window().setPosition(new Point(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
                break;
            case "NEWTAB":
                openNewTab();
                break;
            case "CLOSETAB":
                closeTab();
                break;
            case "SWITCHTOTAB":
                switchToTab();
                break;
            default:
                throw new UnsupportedOperationException("Action: " + getAction() + " is not supported");
        }
    }

}
