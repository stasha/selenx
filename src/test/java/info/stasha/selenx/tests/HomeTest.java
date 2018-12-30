package info.stasha.selenx.tests;

import info.stasha.selenx.annotations.Configuration;
import info.stasha.selenx.junit4.SelenxRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author stasha
 */
@RunWith(SelenxRunner.class)
@Configuration(xmlTest = "/home/stasha/projects/stasha/pageobjectfluentapi/src/test/java/info/stasha/pageobject/tests/HomeTest.xml")
public class HomeTest {
    
    @Test
    public void loginTest(){
        
    }

}
