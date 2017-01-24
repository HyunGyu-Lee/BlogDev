package com.leelab.blogproject.simplemock;

import org.springframework.test.context.web.GenericXmlWebContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class Application {

	public static void main(String[] args) {
		
		//GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:/config/spring/context-*.xml");
		
		GenericXmlWebContextLoader ctxLoader = new GenericXmlWebContextLoader();
		try
		{
			WebApplicationContext ctx = (WebApplicationContext) ctxLoader.loadContext("");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
}
