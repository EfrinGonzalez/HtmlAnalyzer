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
    private String url,  urlTwo;
    private List<String> urlList = new ArrayList<String>();
    
	@FunctionName("start")
    public String start(@HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) String req,
                        ExecutionContext context) {
		
		this.htmlParser(req, context);
        return String.format("Hello, %s!", req);
    }
    
	
	@FunctionName("htmlParser")
	public String htmlParser(@HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) String req,
            ExecutionContext context) {
		//https://www.retsinformation.dk/Forms/R0710.aspx?id=192080
		//https://www.retsinformation.dk/Forms/R0710.aspx?id=192286 
		//url = req;
		url = "https://www.retsinformation.dk/Forms/R0710.aspx?id=192080"; 
		urlTwo = "https://www.retsinformation.dk/Forms/R0710.aspx?id=192080";
		
		
	
		URL obj = null;	
		URL objTwo = null;	
		
		HttpURLConnection con = null;

		HttpURLConnection conTwo = null;
		
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("\nSending 'GET' request to URL : " + urlTwo);
	
		String inputLine;
		StringBuffer response = new StringBuffer();
		StringBuffer responseTwo = new StringBuffer();
		BufferedReader in, inTwo = null;
		try {
			
			obj = new URL(url);
			objTwo = new URL(urlTwo);
			
			con = (HttpURLConnection) obj.openConnection();
			conTwo = (HttpURLConnection) objTwo.openConnection();
			
			con.setRequestMethod("GET");
			conTwo.setRequestMethod("GET");
			
			con.setRequestProperty("User-Agent", USER_AGENT);
			conTwo.setRequestProperty("User-Agent", USER_AGENT);
			//responseCode = con.getResponseCode();
			in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			
			inTwo = new BufferedReader(
			        new InputStreamReader(conTwo.getInputStream()));
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();	
			
			while ((inputLine = inTwo.readLine()) != null) {
				responseTwo.append(inputLine);
			}
			
			inTwo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//url = "https://www.retsinformation.dk/Forms/R0710.aspx?id=192080"; 
		//urlTwo = "https://www.retsinformation.dk/Forms/R0710.aspx?id=192080";
		
		HtmlParsing hmlParser = new HtmlParsing();
		hmlParser.extractHTMLLinks(response.toString(), "file1.txt");
		
		
		//List<HTMLLinkElement> elementsOne = new ArrayList<HTMLLinkElement>(); 
		
		HtmlParsing hmlParserTwo = new HtmlParsing();
		hmlParser.extractHTMLLinks(response.toString(), "file2.txt");
		
		
		return "Done for: url 1:" + url 
					  + " url2: " + urlTwo;
}
	

}
