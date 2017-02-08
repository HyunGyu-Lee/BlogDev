package com.leelab.blogproject.utils.prettylogger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class CustomizingHandler implements InvocationHandler {
	
	/**
	 * org.slf4j.Logger에 정의된 로그 레벨
	 * */
	public static final String INFO = "info";
	public static final String DEBUG = "debug";
	public static final String ERROR = "error";
	public static final String WARN = "warn";
	public static final String TRACE = "trace";

	/**
	 * PrettyLoggerImpl에 정의된 커스텀 로깅 메소드 레벨
	 * */
	public static final String LIST = "list";
	
	/**
	 * 글자 스타일
	 * */
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
	
	/**
	 * org.slf4j.Logger 객체
	 * */
	private Object targetLogger;
	
	public CustomizingHandler(){}
	
	public CustomizingHandler(Object targetLogger) {
		this.targetLogger = targetLogger;
	}
	
	/**
	 * logLevel에 따라 출력될 색을 지정, Override 하는 곳에서 args에 색깔상수를 더해줘야함
	 * */
	public abstract void colorize(String logLevel, Object[] args);

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;

		colorize(method.getName(), args);
		result = method.invoke(targetLogger, args);
		
		return result;
	}
	
	public Object getTargetLogger() {
		return targetLogger;
	}

	public void setTargetLogger(Object targetLogger) {
		this.targetLogger = targetLogger;
	}
	
}
