package com.htmlanalyzer.model;

import java.io.Serializable;

public class HTMLLinkElement {//implements Serializable{
	
		//private static final long serialVersionUID =1L;
		private String linkElement;	
	    private String linkAddress;
	    
	    public String getLinkAddress() {	
	        return linkAddress;
	
	    }
	
	 
	
	    public void setLinkAddress(String linkElement) {	
	        this.linkAddress = replaceInvalidChar(linkElement);	
	    }	 
	
	    public String getLinkElement() {
	        return linkElement;	
	    }
	
	    public void setLinkElement(String linkAddress) {	
	        this.linkElement = linkAddress;	
	    }
	
	    private String replaceInvalidChar(String linkElement) {	
	        linkElement = linkElement.replaceAll("'", "");	
	        linkElement = linkElement.replaceAll("\"", "");	
	        return linkElement;	
	    }

	    
	    @Override
		public String toString() {
			return "Text=" + getLinkElement() + ", linkAddress=" + getLinkAddress() ;
		}

	    /* @Override	
	    public String toString() {	
	               
	        return new StringBuffer("Link Text : ").append(getLinkElement())
	        				.append("Link Address : ").append(getLinkAddress()).toString();
	    }
*/
}
