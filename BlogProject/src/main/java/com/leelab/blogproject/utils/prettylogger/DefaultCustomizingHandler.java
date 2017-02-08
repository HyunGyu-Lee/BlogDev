package com.leelab.blogproject.utils.prettylogger;

public class DefaultCustomizingHandler extends CustomizingHandler {
	
	@Override
	public void colorize(String logLevel, Object[] args) {

		if(logLevel.equals(INFO))
		{
			args[0] = BG_YELLOW + RED + args[0] + RESET;
		}
		/* 아래 항목 아직 미구현 */
		else if(logLevel.equals(DEBUG)){}
		else if(logLevel.equals(ERROR)){}
		else if(logLevel.equals(WARN)){}
		else if(logLevel.equals(TRACE)){}

	}
	
}
