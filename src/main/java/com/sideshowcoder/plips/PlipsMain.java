package com.sideshowcoder.plips;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.lang3.ArrayUtils;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import com.sideshowcoder.plips.Evaluator;
import java.io.PrintStream;

/**
 * PlipsMain
 *
 * Lisp Interpreter built following the "Build Your Own Lisp" Book, but using
 * Java instead of C
 */
public class PlipsMain {

    private static final String QUIT = "/q";
    private static final String PROMPT = "plips> ";
    private static final String VERSION_MESSAGE = "Plips Version 0.0.1";
    private static final String USAGE = "Enter " + QUIT + " to exit";
    private static final String UTF_8 = "UTF-8";

    private final BufferedReader reader;
    private final PrintStream output;
    private final boolean interactive;
    private final Evaluator evaluator;

    public PlipsMain(BufferedReader r, PrintStream o, boolean i) {
        reader = r;
        output = o;
        interactive = i;
        evaluator = new Evaluator();
    }

    public static void main(String[] args) {
        final InputStream input;
        final boolean interactive;
        if (!ArrayUtils.isEmpty(args)) {
            interactive = false;
            StringBuilder sb = new StringBuilder();
            for(String arg : args) {
                sb.append(arg);
            }
            byte[] inputBytes;
            try {
                inputBytes = sb.toString().getBytes(UTF_8);
            } catch (UnsupportedEncodingException e) {
                System.exit(1);
                return;
            }
            input = new ByteArrayInputStream(inputBytes);
        } else {
            interactive = true;
            input = System.in;
        }
        final BufferedReader r = new BufferedReader(new InputStreamReader(input));
        new PlipsMain(r, System.out, interactive).repl();
    }

    public void repl() {
        output.println(VERSION_MESSAGE);
        if (interactive) {
            output.println(USAGE);
        }

        String input;
        String result;

        while(true) {
            if (interactive) {
                output.println(USAGE);
            }

            input = read();
            if (input == null || input.equals(QUIT)) {
                return;
            }

            result = eval(input);
            print(result);
        }
    }

    private String read() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            line = "Error " + e.getMessage();
        }
        return line;
    }

    private String eval(String line) {
        try {
            return evaluator.evaluate(line);
        } catch (EvaluationException e) {
            return "Error: Can't evaluate " + line;
        }
    }

    private void print(String result) {
        output.println(result.trim());
    }
}
