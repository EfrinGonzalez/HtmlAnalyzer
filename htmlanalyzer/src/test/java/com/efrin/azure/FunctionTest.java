package com.efrin.azure;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Before;
import org.junit.Test;

import com.htmlanalyzer.Utils.HtmlReader;
import com.htmlanalyzer.Utils.WriteToFile;
import com.htmlanalyzer.functions.Function;

/**
 * Unit test for Function class.
 */
public class FunctionTest {
	/**
	 * Unit test for start method.
	 */

	private ConcurrentLinkedQueue<String> queue = null;
	private FileOutputStream out = null;
	
	private Function function = null;
	private HtmlReader htmlReader = null;
	private String response = null;
	private String resp = null;
	private List<String> urlList = null;
	private String fileName = "c:\\temp\\test.txt";
	File file = null;
	private Function mockFunction = mock(Function.class);
	private HtmlReader mockHtmlReader = mock(HtmlReader.class);
	private WriteToFile writer = null;

	@Before
	public void beforeTestMethod() {
		function = new Function();
		htmlReader = new HtmlReader();
	}

	@Test
	public void testStart() throws Exception {
		urlList = new ArrayList<String>();
		urlList.add("https://www.retsinformation.dk/Forms/R0710.aspx?id=192080");
		urlList.add("https://www.retsinformation.dk/Forms/R0710.aspx?id=192286");

		when(mockFunction.start(urlList, null)).thenReturn(urlList.size());
		when(mockHtmlReader.GetHtmlDocumentAsString(urlList.get(0))).thenReturn(response);
		when(mockHtmlReader.GetHtmlDocumentAsString(urlList.get(1))).thenReturn(response);

		// Missing to verify the calling of the asynchronous processes.

		final int listSize = function.start(urlList, null);
		assertThat(listSize, is(urlList.size()));
		response = htmlReader.GetHtmlDocumentAsString(urlList.get(0));
		assertThat(response, is(notNullValue()));
	}

}
