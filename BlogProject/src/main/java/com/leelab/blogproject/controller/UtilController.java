package com.leelab.blogproject.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/util")
public class UtilController {

	private static final Logger logger = LoggerFactory.getLogger(UtilController.class);
	
	@RequestMapping("/deleteAllCookie")
	public void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Delete All Cookie");
		for(Cookie c : request.getCookies())
		{
			c.setMaxAge(0);
			response.addCookie(c);
		}
	}
	
	@RequestMapping("test-*")
	public String to(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri.substring(uri.lastIndexOf("-")+1, uri.length());
	}
	
	@RequestMapping(value="ajaxTest", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> ajaxTest(@RequestParam Map<String,String> data) {
		
		logger.info("Ajax요청 들어옴 {}, {}", data.get("name"), data.get("phone"));
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("a", "이건A");
		result.put("b", "이건B");
		
		return result;
	}
}
