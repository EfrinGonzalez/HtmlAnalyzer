package com.efrin.azure;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.htmlanalyzer.Utils.HtmlReader;

public class HtmlReaderTest {
	private HtmlReader reader = null;	
	private String resp =null;
	
	@Before
	public void beforeTestMethod() {		
		reader = new HtmlReader();
	}
	
	@Test
	 public void testHtmlReader() throws Exception {
 
		resp = reader.GetHtmlDocumentAsString("https://www.retsinformation.dk/Forms/R0710.aspx?id=192080");   
		 //The request get data
	        assertThat(resp, is(notNullValue()));
	        //In the data the is an </a> tag
	       assertThat(resp, containsString("</a>"));
	        //and a href tag
	        assertThat(resp, containsString("href"));
	        //System.out.println(resp);
	    
	    }
	 
	
	
	 
	 
}
