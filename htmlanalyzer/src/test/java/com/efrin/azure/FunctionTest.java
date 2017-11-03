package com.efrin.azure;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.htmlanalyzer.functions.Function;

/**
 * Unit test for Function class.
 */
public class FunctionTest {
    /**
     * Unit test for start method.
     */
	 @Test
    public void testStart() throws Exception {
		 List<String> urlList = new ArrayList<String>();
		 urlList.add("https://www.retsinformation.dk/Forms/R0710.aspx?id=192080");
		 urlList.add("https://www.retsinformation.dk/Forms/R0710.aspx?id=192286 ");
		 urlList.add("https://www.google.com");
		 	
		 
		 final Function function = new Function();

        final int ret = function.start(urlList, null);
        assertThat(ret, is(urlList.size()));    
    }
	 
	 @Test
	    public void testhtmlParserIsNotNull() throws Exception {
	        final Function parserFunction = new Function();
	        final String ret = parserFunction.htmlParser("https://www.retsinformation.dk/Forms/R0710.aspx?id=192080", null);
	        //The request get data
	        assertThat(ret, is(notNullValue()));
	        //In the data the is an </a> tag
	        assertThat(ret, containsString("</a>"));
	        //and a href tag
	        assertThat(ret, containsString("href"));
	    
	    }
		 
    
    
  
    
    
    
}
