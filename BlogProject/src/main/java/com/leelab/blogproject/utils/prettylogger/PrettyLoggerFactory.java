package com.leelab.blogproject.utils.prettylogger;

import java.lang.reflect.Proxy;

/**
 * org.slf4j 에 의존
 * */
public class PrettyLoggerFactory {

	/**
	 * DefaultCustomizing 사용
	 * */
	public static PrettyLogger createInstance(Class<?> clazz) {
		return createInstance(clazz, new DefaultCustomizingHandler());
	}

	/**
	 * 사용자가 colorizing 메소드를 Override해서 객체를 전달하면
	 * 이 메소드에서 생성한 Logger를 주입해준다음 Proxy생성 인자로 전달
	 * */
	public static PrettyLogger createInstance(Class<?> clazz, CustomizingHandler customizingHandler) {
		Class<?> loggerClass = PrettyLoggerImpl.class;
		customizingHandler.setTargetLogger(new PrettyLoggerImpl(clazz));
		return (PrettyLogger)Proxy.newProxyInstance(loggerClass.getClassLoader(),
				 							  		loggerClass.getInterfaces(),
				 							  		customizingHandler);
	}
}
