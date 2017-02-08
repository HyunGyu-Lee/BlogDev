package com.leelab.blogproject.utils.prettylogger;

import java.util.List;

public class ObjectPrinter {

	private static final int LAYOUT_WIDTH = 200;
	
	public static final String RESET = "\u001b[0m";
	public static final String BOLD = "\u001b[4m";
	
	/**
	 * ANSI CODE로 표현된 글자 색
	 * */
	public static final String BLACK = "\u001b[30m";
	public static final String RED = "\u001b[91m";
	public static final String GREEN = "\u001b[92m";
	public static final String YELLOW = "\u001b[93m";
	public static final String BLUE = "\u001b[94m";
	public static final String PURPLE = "\u001b[95m";
	public static final String CYAN = "\u001b[96m";
	public static final String WHITE = "\u001b[97m";

	/**
	 * ANSI CODE로 표현된 배경 색
	 * */
	public static final String BG_BLACK = "\u001b[40m";
	public static final String BG_RED = "\u001b[101m";
	public static final String BG_GREEN = "\u001b[102m";
	public static final String BG_YELLOW = "\u001b[103m";
	public static final String BG_BLUE = "\u001b[104m";
	public static final String BG_PURPLE = "\u001b[105m";
	public static final String BG_CYAN = "\u001b[106m";
	public static final String BG_WHITE = "\u001b[107m";
	
	public static <T> String list(String name, List<T> list, String...ansiCodes) {
		int header_split = (LAYOUT_WIDTH-(name.length()+4))/2;
		StringBuilder sb = new StringBuilder();
		
		/* ANSI_CODE 적용 */
		for(String ansiCode : ansiCodes)sb.append(ansiCode);
		
		sb.append("\n").append(LayoutString.inline(header_split,"━")).append("<<"+name+">>").append(LayoutString.line(header_split,"━"));
		
		for (int i = 0; i < list.size(); i++) {
			String val = i+" : "+list.get(i);
			int con_sp = (LAYOUT_WIDTH-val.length())/2;
			sb.append(LayoutString.inline(con_sp, " "));
			sb.append(String.format("%s", val));
			sb.append(LayoutString.line(con_sp, "  "));
		}
		
		sb.append(LayoutString.line(LAYOUT_WIDTH,"━"));
		sb.append(RESET);
		return sb.toString();
	}
	
}
