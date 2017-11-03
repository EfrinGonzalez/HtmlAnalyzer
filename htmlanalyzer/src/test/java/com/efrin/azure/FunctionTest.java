package com.efrin.azure;

import org.junit.Test;

import com.htmlanalyzer.functions.Function;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Function class.
 */
public class FunctionTest {
    /**
     * Unit test for hello method.
     */
    @Test
    public void testHello() throws Exception {
        final Function function = new Function();

        final String ret = function.hello("function", null);

        assertEquals("Hello, function!", ret);
    }
}
