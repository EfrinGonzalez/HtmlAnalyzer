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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlanalyzer.Utils.HtmlReader;
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

	private String url;
	private List<String> urlList = new ArrayList<String>();
	private static final String USER_AGENT = "Mozilla/5.0";

	@FunctionName("start")
	public int start(@HttpTrigger(name = "req", methods = { "get",
			"post" }, authLevel = AuthorizationLevel.ANONYMOUS) List<String> req, ExecutionContext context) {
		urlList = req;

		File file = new File("c:\\temp\\" + File.separator + "test.txt");
		try {
			FileOutputStream out = new FileOutputStream(file, true);

			ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
			HtmlReader htmlReader = null;
			String stringHtml = null;
			for (String url : urlList) {
				htmlReader = new HtmlReader();

				stringHtml = htmlReader.GetHtmlDocumentAsString(url);

				Document doc = Jsoup.parse(stringHtml);

				Elements links = doc.select("a[href]");
				System.out.println("From: " + url);
				System.out.println("Size of list: " + links.size());

				/*
				 * for (Element link : links) {
				 * System.out.println(link.attr("href") + " : " + link.text());
				 * new Thread(new MyThread(queue, '"'+link.attr("href") + " :
				 * " + link.text()+'"'+"\n" )).start();// multi thread into the
				 * queue data }
				 */

				/*
				 * for (int i = 0; i < links.size(); i++) {
				 * System.out.println(links.get(i).attr("href") + " : " +
				 * links.get(i).text()); new Thread(new MyThread(queue,
				 * i+" : From: "+url+'"'+links.get(i).attr("href") + " :
				 * " + links.get(i).text()+'"'+"\n" )).start();// multi thread
				 * into the queue data
				 * 
				 * }
				 */

				for (int i = 1; i < 200; i++) {
					// new Thread(new MyThread(queue,"Thread " +i+" ".start)),
					// ();
					new Thread(new MyThread(queue, url.substring(14, 20)+" : " + i + "\n")).start();// multi
																					// thread
					try {
						Thread.sleep(100);
						if (!Thread.currentThread().isAlive()) {
							System.out.println("The thread has finished");
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}										// into
																					// the
																					// queue
																					// data
				}

				System.out.println("Size of list: " + links.size());
				// new Thread(new MyThread(queue, string)).start();// multi
				// thread into the queue data
				new Thread(new WriteToFile(out, queue)).start();// The thread of
																// monitoring,
																// continuously
																// from the
																// queue read
																// and write
																// data to a
																// file
			}

			try {
				Thread.sleep(300);
				if (!Thread.currentThread().isAlive()) {
					System.out.println("The thread has finished");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return urlList.size();
	}

}
