package com.efrin.azure;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.htmlanalyzer.Utils.HtmlReader;
import com.htmlanalyzer.functions.Function;

public class HtmlReaderTest {

	Function function = null;
	HtmlReader reader = null;
	
	String resp ="";
	
	
	@Test
	// public void testHtmlReaderIsNotNull() throws Exception {
	 public void testHtmlReader() throws Exception {
	    reader = new HtmlReader(); 
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
