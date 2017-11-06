package com.htmlanalyzer.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import com.htmlanalyzer.model.HTMLLinkElement;
import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.serverless.functions.annotation.FunctionName;
import com.microsoft.azure.serverless.functions.annotation.HttpTrigger;

/**
 * Hello function with HTTP Trigger.
 */
public class Function {

	private List<String> urlList = new ArrayList<String>();
	private ConcurrentLinkedQueue<String> queue = null;
	private HtmlReader htmlReader = null;
	private String stringHtml = null;
	private FileOutputStream out = null;
	private File file = null;
	private HTMLLinkElement htmlPojo = null;

	@FunctionName("start")
	public int start(@HttpTrigger(name = "req", methods = { "get",
			"post" }, authLevel = AuthorizationLevel.ANONYMOUS) List<String> req, ExecutionContext context) {

		try {
			urlList = req;
			File file = new File("c:\\temp\\" + File.separator + "test.txt");
			out = new FileOutputStream(file, true);
			queue = new ConcurrentLinkedQueue<String>();

			for (String url : urlList) {
				htmlReader = new HtmlReader();
				stringHtml = htmlReader.GetHtmlDocumentAsString(url);
				Document doc = Jsoup.parse(stringHtml);

				Elements links = doc.select("a[href]");
				System.out.println("From: " + url);
				System.out.println("Size of list: " + links.size());

				for (Element link : links) {
					// multi thread into the queue data
					htmlPojo = new HTMLLinkElement(link.text(), link.attr("href"));
					new Thread(new MyThread(queue,
							'"' + htmlPojo.getLinkText() + " : " + htmlPojo.getLinkAddress() + '"' + "\n")).start();
				}

				// The thread of monitoring, continuously from the queue read
				// and write data to a file
				new Thread(new WriteToFile(out, queue)).start();
				try {
					// According to the list size the sleeping time would be
					// subject to increase
					// in order for the file to be full written.
					Thread.sleep(30000);
					if (!Thread.currentThread().isAlive()) {
						System.out.println("The thread has finished");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // outer for

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return urlList.size();
	}

}
