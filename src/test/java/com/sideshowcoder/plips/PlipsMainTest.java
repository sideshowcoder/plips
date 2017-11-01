package com.sideshowcoder.plips;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class PlipsMainTest {

    @Test
    public void testPlipsMainEvaluateNumber() throws UnsupportedEncodingException, IOException {
        InputStream inStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inStream));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(outStream);
        new PlipsMain(inputReader, output, false).repl();
        output.close();
        outStream.close();
        String expected = "Plips Version 0.0.1\n1.0";
        String result = new String(outStream.toByteArray()).trim();
        assertTrue(result.matches(expected));
    }
}
