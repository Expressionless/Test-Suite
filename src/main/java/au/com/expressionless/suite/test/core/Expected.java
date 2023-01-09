package au.com.expressionless.suite.test.core;

public class Expected<T> {
    public T expected;

    public Expected(T expected) {
        this.expected = expected;
    }

    @Override
    public boolean equals(Object other) {
        if((other instanceof Expected<?>)) {
            return ((Expected<?>) other).expected.equals(this.expected);
        }

        return false;
    }
}