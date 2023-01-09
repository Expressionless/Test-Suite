package au.com.expressionless.suite.test.core;

import au.com.expressionless.suite.test.callbacks.FITestCallback;
import au.com.expressionless.suite.test.callbacks.FITestVerificationCallback;
import au.com.expressionless.utils.CUtils;

/**
 * 
 * @author Bryn Meachem
 */
class TestCase<I, E> {
    public static final String SPACE_BREAK = "       ";
    public static final int EQUALS_LENGTH = 50;

    private final Input<I> input;
    private final Expected<E> expected;

    private final String name;
    private final String description;

    private final FITestCallback<Input<I>, Expected<E>> testCallback;
    private final FITestVerificationCallback<E> verificationCallback;

    public TestCase(String name, Input<I> input, Expected<E> expected, 
            FITestCallback<Input<I>, Expected<E>> testCallback, FITestVerificationCallback<E> verificationCallback,
            String description) {
        this.input = input;
        this.expected = expected;
        this.testCallback = testCallback;
        this.verificationCallback = verificationCallback;

        this.description = description;
        this.name = name;
    }

    public E test() {
        System.out.println(CUtils.equalsBreak(EQUALS_LENGTH) + "\n[!] Running test: " + toString(true));
        E result = testCallback.test(input).expected;
        printData("Input", input.input);
        printData("Expected", expected.expected);
        printData("Result", result);
        return result;
    }
    
    private void printData(String tag, Object object) {
        if(!object.getClass().isArray()) {
            System.out.println(SPACE_BREAK + tag + ": " + object);
        } else {
            Object[] objects = (Object[])object;
            System.out.println("       " + tag + ": " + CUtils.getArrayString(objects));
        }
    }

    public void verify() {
        E result = test();
        try { 
            verificationCallback.assertFunction(result, expected.expected);
        } catch(AssertionError e) {
            System.err.println("TEST FAILED: " + this);
            assert(false);
        }
    }

    public E getExpected() {
        return expected.expected;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Return a brief description of this TestCase
     * @param showDesc - whether or not to include the user supplied description
     * @return
     */
    public String toString(boolean showDesc) {
        StringBuilder sb = new StringBuilder("Test [\n")
                .append("Name: ")
                .append((name != null ? name : "Unnamed Test"));
        if(showDesc) {
            sb.append("\nDescription: ")
                .append(description != null ? description : "none supplied");
        }

        return sb.append("\n]")
        .toString();
    }
}
