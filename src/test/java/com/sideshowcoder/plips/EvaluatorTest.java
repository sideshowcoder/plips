package com.sideshowcoder.plips;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.sideshowcoder.plips.EvaluationException;

public class EvaluatorTest {

    @Test(expected = EvaluationException.class)
    public void evalNoExpressionFails() {
        String invalidExpression = "((";
        new Evaluator().evaluate(invalidExpression);
    }

    @Test
    public void evalNumberToItself() {
        String numberExpression = "1";
        String result = new Evaluator().evaluate(numberExpression);
        assertEquals(Double.valueOf(numberExpression), Double.valueOf(result));
    }
}
