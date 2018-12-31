/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.stasha.selenx.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.Test;

/**
 *
 * @author stasha
 */
public class SearchXmlTests {

    public SearchXmlTests() {
    }

    @Test
    public void find() throws IOException {
        File f = new File(".").getAbsoluteFile();
        
        Path dir = Paths.get(System.getProperty("user.dir"), "src");


        try (Stream<Path> paths = Files.find(
                dir, Integer.MAX_VALUE,
                (path, attrs) -> attrs.isRegularFile()
                && ((path.toString().startsWith("Test") && path.toString().endsWith(".xml")) || path.toString().endsWith("Test.xml")))) {
            // Consume only the first 5 from the stream:
            paths.forEach(System.out::println);
        }
    }

}
