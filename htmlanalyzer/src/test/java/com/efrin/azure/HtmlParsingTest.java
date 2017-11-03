package com.efrin.azure;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.htmlanalyzer.Utils.HtmlParsing;
import com.htmlanalyzer.functions.Function;



public class HtmlParsingTest {

	
	 @Test
	    public void testHtmlExtraction() throws Exception {
		 final HtmlParsing parser = new HtmlParsing();
		 final Function function = new Function();
		
		 String resp  = parser.extractHTMLLinks(function.htmlParser("https://www.retsinformation.dk/Forms/R0710.aspx?id=192080", null), "test.txt");
		 //String resp  = parser.extractHTMLLinks("Blah blah blah <a href='http://www.javacodegeeks.com/'>JavaCodeGeeks</a> blah blah blah blah", "file2.txt");
		 //The response contains data
		 assertThat(resp, is(notNullValue()));
		 //The data was writen in the file  in c:\temp\file2.txt
		
		 String fileContent = readFile("c:\\temp\\test.txt");
		    assertThat(fileContent, is(notNullValue()));
		   
	 }
	 
	 public String readFile(String fileName) throws FileNotFoundException{
		 FileReader in = new FileReader(fileName);
		    BufferedReader br = new BufferedReader(in);

		    try {
				while (br.readLine() != null) {
				    System.out.println(br.readLine());
				}
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		   
		    
		    return br.toString();
	 }
}
