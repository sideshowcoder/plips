package com.sideshowcoder.plips;

import com.sideshowcoder.plips.EvaluationException;
import org.jparsec.Parser;
import org.jparsec.Parser.Reference;
import org.jparsec.Terminals;
import org.jparsec.Terminals.DecimalLiteral;
import org.jparsec.Parser.Reference;
import org.jparsec.Scanners;

public class Evaluator {

    final Parser<Double> parser;

    // This is a lisp we numbers should be of arbitrary precision, to make life
    // simple using a double here for now.
    private static final Parser<Double> NUMBER = Scanners.DECIMAL.map(Double::valueOf);

    public Evaluator() {
        parser = makeParser();
    }

    public static Parser<Double> makeParser() {
        Parser.Reference<Double> ref = Parser.newReference();
        ref.set(NUMBER);
        return NUMBER;
    }

    public String evaluate(String expr) {
        try {
            double result = parser.parse(expr);
            return String.valueOf(result);
        } catch (Exception e) {
            throw new EvaluationException(e.getMessage());
        }
    }
}
