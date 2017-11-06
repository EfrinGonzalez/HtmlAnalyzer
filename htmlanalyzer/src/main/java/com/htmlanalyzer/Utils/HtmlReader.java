package com.htmlanalyzer.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlanalyzer.model.HTMLLinkElement;

public class HtmlReader {

	private static final String USER_AGENT = "Mozilla/5.0";
	private URL obj = null;
	private HttpURLConnection con = null;
	private String inputLine = null;
	private StringBuffer response = null; 
	private BufferedReader in = null;

	public String GetHtmlDocumentAsString(String url) {

		try {
			response = new StringBuffer();
			obj = new URL(url);
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

		return response.toString();

	}
}