package au.com.expressionless.suite.test.callbacks;

public interface FITestVerificationCallback<Expected> {
    public void assertFunction(Expected result, Expected expected);
}
