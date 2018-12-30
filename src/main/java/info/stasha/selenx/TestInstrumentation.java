package info.stasha.selenx;

import info.stasha.selenx.junit4.TestAnnotation;
import info.stasha.selenx.tags.Test;
import info.stasha.selenx.tags.TestClass;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodCall;

/**
 * Instrumentation.
 *
 * @author stasha
 */
public class TestInstrumentation {

    private TestInstrumentation() {
    }

    private static final Map<Class<?>, Class<?>> CLASSES = new HashMap<>();

    /**
     * Returns already instrumented class.
     *
     * @param clazz
     * @return
     */
    public static Class<?> getInstrumentedClass(Class<?> clazz) {
        return CLASSES.get(clazz);
    }

    /**
     * Returns new instrumented test class.
     *
     * @param clazz
     * @param tests
     * @return
     * @throws java.lang.NoSuchMethodException
     */
    public static Class<?> testClass(Class<?> clazz, TestClass tests) throws NoSuchMethodException {

        if (!CLASSES.containsKey(clazz)) {
            DynamicType.Builder<?> b = new ByteBuddy()
                    .subclass(clazz)
                    .name(clazz.getName() + "_");

            for (Test test : tests.getTests()) {
                b = b.defineMethod(test.getId(), void.class, Visibility.PUBLIC)
                        .intercept(MethodCall.invoke(
                                TestExecutor.class.getMethod("execute", Test.class))
                                .with(test)
                        )
                        .annotateMethod(new TestAnnotation());
            }

            Class<?> cls = b.make()
                    .load(clazz.getClassLoader())
                    .getLoaded();

            CLASSES.put(clazz, cls);
        }

        return CLASSES.get(clazz);
    }
}
