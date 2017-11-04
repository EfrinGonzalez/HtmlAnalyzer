package com.htmlanalyzer.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlanalyzer.Utils.HtmlParsing;
import com.htmlanalyzer.Utils.WriteToFile;
import com.htmlanalyzer.model.HTMLLinkElement;
import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.serverless.functions.annotation.FunctionName;
import com.microsoft.azure.serverless.functions.annotation.HttpTrigger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

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
		this.htmlParser(urlList.get(0), context);
		return urlList.size();
	}

	@FunctionName("htmlParser")
	public String htmlParser(@HttpTrigger(name = "req", methods = { "get",
			"post" }, authLevel = AuthorizationLevel.ANONYMOUS) String req, ExecutionContext context) {
		URL obj = null;
		HttpURLConnection con = null;
		System.out.println("\nSending 'GET' request to URL : " + urlTwo);

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
