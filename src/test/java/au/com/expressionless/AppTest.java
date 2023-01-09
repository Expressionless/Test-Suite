package au.com.expressionless;

import org.junit.Test;

import au.com.expressionless.suite.test.core.Expected;
import au.com.expressionless.suite.test.core.JUnitTester;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        new JUnitTester<String, Integer>()
        .setTest((input) -> {
            return new Expected<Integer>(Integer.parseInt(input.input));
        })

        .createTest("Generic Integer test 1")
        .setDescription("This tests the Integer.parseInt function!", "Input: \"5\"", "Output: 5", "Some other lines here")
        .setInput("5")
        .setExpected(5)
        .build()

        .assertAll();
    }
}
