package com.leelab.blogproject.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GeneralExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		e.printStackTrace();
		//logger.info("{}", e.getMessage());
		return new ModelAndView("error");
	}
	
}
