package none.cvg.methods;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import none.cvg.DemoClass;
import org.junit.Test;

import static none.cvg.ErrorMessages.REFLECTION_FAILURE;
import static none.cvg.ErrorMessages.TEST_FAILURE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/*
 * TODO:
 *  This test aims at using MethodHandles to invoke a public method on a class.
 *  Each solved test shows how this can be achieved with the traditional reflection calls.
 *  Each unsolved test provides a few hints that will allow the kata-taker to manually solve
 *  the exercise to achieve the same goal with MethodHandles.
 */
public class PublicMethodInvocationTest {

    @Test
    public void reflectionPublicMethod() {

        String expectedOutput = "[DemoClass] - Public method - via reflection";

        try {

            // Find the method on the class via a getMethod.
            Method publicMethod =
                    DemoClass.class.getMethod("publicMethod",
                            String.class);

            DemoClass demoClass = new DemoClass();

            assertEquals("Reflection invocation failed",
                    expectedOutput,
                    publicMethod.invoke(demoClass, "via reflection"));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {

            fail(REFLECTION_FAILURE.getValue() + e.getMessage());
        }
    }

    @Test
    public void methodHandlePublicMethod() {

        String expectedOutput = "[DemoClass] - Public method - via Method Handles";

        /*
         * TODO:
         *  Get a public lookup from java.lang.invoke.MethodHandles
         *  Public parts of a class are looked up via "public lookups"
         *  Check API: java.lang.invoke.MethodHandles.publicLookup()
         */
        MethodHandles.Lookup publicMethodHandlesLookup = null;

        /*
         * TODO:
         *  Replace the "null"s with valid values to extract a method signature for publicMethod.
         *  Create a methodType instance that matches the signature of what we wish to invoke
         *  HINT: The publicMethod() returns a String and accepts a String parameter
         *  Check API: java.lang.invoke.MethodType.methodType(?, ?)
         */
        MethodType methodType = null; // HINT: MethodType.methodType(?, ?);

        try {

            /*
             * TODO:
             *  Replace the "null"s with appropriate values to get a handle to publicMethod.
             *  "Find" a method of the class via the Lookup instance,
             *  based on the methodType described above and the method name.
             *  Public methods can be searched via the findVirtual() method.
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVirtual(?, ?, ?)
             */
            MethodHandle publicMethodHandle =
                    publicMethodHandlesLookup.findVirtual(null,
                            null, null); // Class, Method name, MethodType

            DemoClass demoClass = new DemoClass();

            assertEquals("Method handles invocation failed",
                    expectedOutput,
                    publicMethodHandle.invoke(demoClass,
                            "via Method Handles"));

        } catch (NoSuchMethodException | IllegalAccessException e) {

            fail("Failed to execute a public method invocation via Method Handles: "
                    + e.getMessage());
        } catch (Throwable t) {

            // invoke throws a Throwable (hence catching Throwable separately).
            fail(TEST_FAILURE.getValue() + t.getMessage());
        }
    }
}