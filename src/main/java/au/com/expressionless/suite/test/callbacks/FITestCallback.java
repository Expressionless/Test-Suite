package au.com.expressionless.suite.test.callbacks;

public interface FITestCallback<Input, Expected> {

    public Expected test(Input input);

}
