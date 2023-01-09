package au.com.expressionless.suite.test.core;

import java.util.ArrayList;
import java.util.List;

import au.com.expressionless.suite.test.callbacks.FITestCallback;
import au.com.expressionless.suite.test.callbacks.FITestVerificationCallback;

/**
 * Used to create unit tests in a streamlined manner. Example:
 * <pre>
 *  new JUnitTester<Integer, Integer>()
 *  .setTest((input) -> {
 *      return input.input + 3;
 *  })
 *  
 * // Create a single test, changing the test method for this single test case
 * // Make the test method add 5 to the input
 *  .createTest("+5 test")
 *  .setTest((input) -> {
 *      return input.input + 5;
 *  })
 *  .setInput(5)
 *  .setExpected(10)
 *  .build()
 * 
 *  // This test is testing +3, as the above test method was changed only for a single test
 *  .createTest("+3 test 2")
 *  .setInput(11)
 *  .setExpected(14)
 *  .build()
 * 
 *  .assertAll();
 * </pre>
 * 
 * @see {@link FITestCallback} {@link FITestVerificationCallback} {@link TestCase}
 */
public class JUnitTester<I, E> {

    /**
     * Method to test for Unit Tests. Can be overriden on the Unit Test Level
     * 
     */
    public FITestCallback<Input<I>, Expected<E>> testCallback;

    /**
     * Method to assert Unit Tests. Default assertion is assert(Expected.equals(Result))
     * 
     * @see {@link FITestVerificationCallback}
     */
    public FITestVerificationCallback<E> verificationCallback;

    /**
     * List of unit tests so far
     */
    private final List<TestCase<I,E>> tests = new ArrayList<>();

    /**
     * Create a new JUnitTester with the default assertion
     * 
     * @see {@link JUnitTester#defaultAssertion(Object result, Object expected)}
     */
    public JUnitTester() {
        this(JUnitTester::defaultAssertion);
    }

    /**
     * Create a new JUnitTester with a supplied Verification Callback
     * @param verificationCallback base verification callback to assign to the UnitTester
     */
    public JUnitTester(FITestVerificationCallback<E> verificationCallback) {
        setVerification(verificationCallback);
    }

    /**
     * 
     * @param testCallback
     * @return
     */
    public JUnitTester<I, E> setTest(FITestCallback<Input<I>, Expected<E>> testCallback) {
        this.testCallback = testCallback;
        return this;
    }

    public JUnitTester<I, E> setVerification(FITestVerificationCallback<E> verificationCallback) {
        this.verificationCallback = verificationCallback;
        return this;
    }

    public TestBuilder<I, E> createTest(String name) {
        TestBuilder<I, E> jUnitBuilder = new TestBuilder<I,E>(this).setName(name);
        return jUnitBuilder;
    }

    public JUnitTester<I, E> addTest(TestCase<I, E> test) {
        tests.add(test);
        return this;
    }

    public JUnitTester<I, E> assertAll() {
        if(tests.size() == 0)
            System.err.println("No tests to check!");
        for(TestCase<I, E> test : tests) {
            test.verify();
        }
        tests.clear();
        return this;
    }
    
    public static <E> void defaultAssertion(E result, E expected) {
        if(!objectsAreEqual(result, expected))
            throw new AssertionError("Got: " + result + ". Expected: " + expected);
        
    }


    // pulled from junit api
    private static boolean objectsAreEqual(Object obj1, Object obj2) {
		if (obj1 == null) {
			return (obj2 == null);
		}
		return obj1.equals(obj2);
	}
}
