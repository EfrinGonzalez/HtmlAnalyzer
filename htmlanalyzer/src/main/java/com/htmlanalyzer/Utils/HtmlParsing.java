package com.htmlanalyzer.Utils;

import java.util.ArrayList;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import com.htmlanalyzer.model.HTMLLinkElement;

 

public class HtmlParsing {

    private Matcher mTag, mLink;
    private Pattern pTag, pLink;
 
    //Regular expressions to perform the parsing
    private static final String HTML_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
    private static final String HTML_HREF_TAG_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

    public HtmlParsing() {
        pTag = Pattern.compile(HTML_TAG_PATTERN);
        pLink = Pattern.compile(HTML_HREF_TAG_PATTERN);
    }


    public String extractHTMLLinks(final String sourceHtml, String fileName) {
    	ArrayList<HTMLLinkElement> elements = new ArrayList<HTMLLinkElement>();

        mTag = pTag.matcher(sourceHtml);

        while (mTag.find()) {

            String href = mTag.group(1);     // get the values of href
            String linkElem = mTag.group(2); // get the text of link Html Element
            mLink = pLink.matcher(href);
            
            while (mLink.find()) {
                String link = mLink.group(1);
                HTMLLinkElement htmlLinkElement = new HTMLLinkElement();
                htmlLinkElement.setLinkAddress(link);
                htmlLinkElement.setLinkElement(linkElem);
                elements.add(htmlLinkElement);

                WriteToFile writer = new WriteToFile();        		
                writer.writeToLocalFile(htmlLinkElement.getLinkElement(),htmlLinkElement.getLinkAddress(), fileName);               
          }

        }
        return elements.toString();
        
     
    }



	

}