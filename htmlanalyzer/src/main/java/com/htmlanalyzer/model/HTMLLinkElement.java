package com.htmlanalyzer.model;

import java.io.Serializable;

public class HTMLLinkElement {

	private String linkText;
	private String linkAddress;

	public HTMLLinkElement(String linkText, String linkAddress) {
		super();
		this.linkText = linkText;
		this.linkAddress = linkAddress;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	@Override
	public String toString() {
		return "HTMLLinkElement [linkText=" + linkText + ", linkAddress=" + linkAddress + "]";
	}

}
