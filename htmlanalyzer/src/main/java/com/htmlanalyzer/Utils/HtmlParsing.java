package com.htmlanalyzer.Utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.htmlanalyzer.model.HTMLLinkElement;

public class HtmlParsing {

	public String extractHTMLLinksJSoup(final String sourceHtml, String fileName) {

		String html = sourceHtml;
		Document doc = Jsoup.parse(html);

		Elements links = doc.select("a[href]");

		for (Element link : links) {
			System.out.println(link.attr("href") + " : " + link.text());
			WriteToFile writer = new WriteToFile();
			writer.writeToLocalFile(link.text(), link.attr("href"), fileName);
		}

		return links.toString();

	}

}