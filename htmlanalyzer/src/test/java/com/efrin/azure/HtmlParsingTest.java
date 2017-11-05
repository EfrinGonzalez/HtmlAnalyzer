package com.efrin.azure;

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

public class HtmlParsingTest {

	Function function = null;
	HtmlReader parser = null;
	File file = null;
	String resp ="";
	String fileName = "c:\\temp\\test.txt";
	
	@Ignore
	@Test
	public void testHtmlExtractionJSoup() throws Exception {
		parser = new HtmlReader();
		function = new Function();

		/*resp = parser.extractHTMLLinksJSoup(
				function.htmlParser("https://www.retsinformation.dk/Forms/R0710.aspx?id=192080", null));
		*/
		assertThat(resp, is(notNullValue()));

		// The data was writen in the file in c:\temp\file2.txt
		file = new File(fileName);
		assertTrue(file.exists());

		String fileContent = readFile(fileName);
		assertThat(fileContent, is(notNullValue()));
	}

	
	public String readFile(String fileName) throws FileNotFoundException {
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
