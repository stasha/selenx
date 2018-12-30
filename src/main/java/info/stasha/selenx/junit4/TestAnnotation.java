package info.stasha.selenx.junit4;

import java.lang.annotation.Annotation;
import org.junit.Test;

/**
 *
 * @author stasha
 */
public class TestAnnotation implements Test {

    @Override
    public Class<? extends Throwable> expected() {
        return None.class;
    }

    @Override
    public long timeout() {
        return 0L;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Test.class;
    }

}
