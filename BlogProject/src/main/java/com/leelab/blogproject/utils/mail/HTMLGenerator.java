package com.leelab.blogproject.utils.mail;

public class HTMLGenerator {

	private StringBuilder html = new StringBuilder();
	
	public HTMLGenerator h1(String value) {
		appendCenter("h1", value);
		return this;
	}
	
	public HTMLGenerator br() {
		html.append("<br/>");
		return this;
	}
	
	public HTMLGenerator br(int cnt) {
		for(int i=0;i<cnt;i++)br();
		return this;
	}
	
	public HTMLGenerator b(String value) {
		appendCenter("b", value);
		return this;
	}
	
	public HTMLGenerator plain(String text) {
		html.append(text);
		return this;
	}
	
	
	private void appendCenter(String tag, String value) {
		html.append("<").append(tag).append(">").append(value).append("</").append(tag).append(">");
	}

	public String generateHTML() {
		return html.toString();
	}
}
