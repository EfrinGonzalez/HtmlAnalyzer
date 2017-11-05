package com.htmlanalyzer.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.htmlanalyzer.Utils.HtmlParsing;
import com.htmlanalyzer.Utils.MyThread;
import com.htmlanalyzer.Utils.WriteToFile;
import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.serverless.functions.annotation.FunctionName;
import com.microsoft.azure.serverless.functions.annotation.HttpTrigger;


/**
 * Hello function with HTTP Trigger.
 */
public class Function {
	private static final String USER_AGENT = "Mozilla/5.0";
	private String url, urlTwo;
	private List<String> urlList = new ArrayList<String>();

	@FunctionName("start")
	public int start(@HttpTrigger(name = "req", methods = { "get",
			"post" }, authLevel = AuthorizationLevel.ANONYMOUS) List<String> req, ExecutionContext context) {
		urlList = req;

		this.htmlParser(urlList.get(0), context);
		this.htmlParser(urlList.get(1), context);
		
		File file=new File("c:\\temp\\"+"test.txt");
		
		//
		try {
			FileOutputStream out=new FileOutputStream(file, true);
			ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<String>();
			for(int i=1;i<21;i++){
				//new Thread(new MyThread(queue,"Thread " +i+" ".start)), (); 
				new Thread(new MyThread(queue,"Thread " +i+" ")).start();// multi thread into the queue data
			}
			new Thread(new WriteToFile(out,queue)).start();//The thread of monitoring, continuously from the queue read and write data to a file
			
			try {
				Thread.sleep(1000);
				if(!Thread.currentThread().isAlive()){
					System.out.println("The thread has finished");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		//
		return urlList.size();
	}

	@FunctionName("htmlParser")
	public String htmlParser(@HttpTrigger(name = "req", methods = { "get",
			"post" }, authLevel = AuthorizationLevel.ANONYMOUS) String req, ExecutionContext context) {
		URL obj = null;
		HttpURLConnection con = null;
		//System.out.println("\nSending 'GET' request to URL : " + urlTwo);

		String inputLine;
		StringBuffer response = new StringBuffer();
		BufferedReader in = null;
		try {
			obj = new URL(req);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);

			in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HtmlParsing hmlParser = new HtmlParsing();
		hmlParser.extractHTMLLinksJSoup(response.toString(), "file.txt");
		return response.toString();

	}

}
