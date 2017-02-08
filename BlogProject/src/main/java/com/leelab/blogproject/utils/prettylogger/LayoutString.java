package com.leelab.blogproject.utils.prettylogger;

public class LayoutString {

	public static String line(int length, String character) {
		return inline(length, character)+"\n";
	}
	
	public static String inline(int length, String character) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length;i++)sb.append(character);
		return sb.toString();
	}
}
