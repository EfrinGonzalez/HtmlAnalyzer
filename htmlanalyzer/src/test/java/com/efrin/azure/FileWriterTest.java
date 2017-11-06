package com.efrin.azure;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Ignore;
import org.junit.Test;

import com.htmlanalyzer.Utils.MyThread;
import com.htmlanalyzer.Utils.WriteToFile;
import com.htmlanalyzer.functions.Function;

public class FileWriterTest {
	private ConcurrentLinkedQueue<String> queue = null;
	private FileOutputStream out = null;
	private File file = null;
	private String fileName = "c:\\Temp\\test.txt";
	
	
	
	 
		@Test
		public void testFileExistAndNotEmpty() throws Exception {		
			
			queue = new ConcurrentLinkedQueue<String>();
		
			
			// The data was writen into the queue
			//new Thread(new MyThread(queue, '"' + "text" + " : " + "link" + '"' + "\n")).start();
		 
			//Write into the file
			//new Thread(new WriteToFile(out, queue)).start();	
		
			//The file has to exist in order to do this test
			file = new File(fileName);
		 	//System.out.println("File: "+fileName);
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
