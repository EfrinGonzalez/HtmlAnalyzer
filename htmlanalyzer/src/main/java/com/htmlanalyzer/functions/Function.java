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
import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.serverless.functions.annotation.FunctionName;
import com.microsoft.azure.serverless.functions.annotation.HttpTrigger;
import java.util.ArrayList;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

/**
 * Hello function with HTTP Trigger.
 */
public class Function {
    private static final String USER_AGENT = "Mozilla/5.0";
    private String url;
    
	@FunctionName("hello")
    public String hello(@HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) String req,
                        ExecutionContext context) {
        return String.format("Hello, %s!", req);
    }
    
	
	@FunctionName("html")
	public String html(@HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) String req,
            ExecutionContext context) {
		//String url = "https://www.retsinformation.dk/Forms/R0710.aspx?id=192080";
		url = req;
		URL obj = null;	
		HttpURLConnection con = null;			
		System.out.println("\nSending 'GET' request to URL : " + url);
	
		String inputLine;
		StringBuffer response = new StringBuffer();
		BufferedReader in = null;
		try {
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			//responseCode = con.getResponseCode();
			in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		HtmlParsing hmlParser = new HtmlParsing();
		hmlParser.extractHTMLLinks(response.toString());
		
		return String.format("Done, %s!", req);
}
	
	
	
    @FunctionName("github")
    public String github(@HttpTrigger(name = "name", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) String req,
                        ExecutionContext context) {
    	
    	
    	final String FILENAME = "C:\\Users\\EFRINGONZALEZ\\Documents\\AzureJava\\test\\com.efrin\\src\\files\\file.txt";
    	    	
    	String url = "https://www.retsinformation.dk/Forms/R0710.aspx?id=192080"; 
		URL obj = null;	
		HttpURLConnection con = null;			
		System.out.println("\nSending 'GET' request to URL : " + url);
	
		String inputLine;
		StringBuffer response = new StringBuffer();
		BufferedReader in = null;
		try {
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			//responseCode = con.getResponseCode();
			in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(response.toString());

    	
		//writing the file
		/*
			BufferedWriter bw = null;
			FileWriter fw = null;

			try {

				String content = response.toString();// "This is the content to write into file\n";
				//String content = "test";
				
				fw = new FileWriter(FILENAME);
				bw = new BufferedWriter(fw);
				bw.write(content);

				System.out.println("Done");

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}
		 	*/
		
		
		
	//String htmlString = response.toString();
	
	/*try {
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href]");

		for(Element link : links){
			
			System.out.println("link: "+ link.attr("href"));
			System.out.println("Text: "+ link.attr("Text"));
			}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	
		///
		
		
    	return ("done");
    	//return String.format("Github, %s!", req);
    }
}
