package com.efrin.azure;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.htmlanalyzer.Utils.HtmlReader;
import com.htmlanalyzer.functions.Function;

/**
 * Unit test for Function class.
 */
public class FunctionTest {
    /**
     * Unit test for start method.
     */
	Function function = null;
	String resp ="";
	List<String> urlList = null;
	String fileName = "c:\\temp\\test.txt";
	File file = null;
	
	 @Before
	    public void beforeTestMethod() {
		 function = new Function();
	    }
	
	 
	 @Test
    public void testStart() throws Exception {
		 urlList = new ArrayList<String>();
		 urlList.add("https://www.retsinformation.dk/Forms/R0710.aspx?id=192080");
		 urlList.add("https://www.retsinformation.dk/Forms/R0710.aspx?id=192286");
		 
        final int ret = function.start(urlList, null);
        assertThat(ret, is(urlList.size()));    
    }
	 
	 

    
	    
}
