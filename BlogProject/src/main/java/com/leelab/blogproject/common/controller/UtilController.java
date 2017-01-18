package com.leelab.blogproject.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.user.service.UserService;

@Controller
@RequestMapping("/util")
public class UtilController {

	private static final Logger logger = LoggerFactory.getLogger(UtilController.class);
	
	@Autowired
	UserService service;
	
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
	public String to(HttpServletRequest request, Model model) {
		String uri = request.getRequestURI();
		return uri.substring(uri.lastIndexOf("-")+1, uri.length());
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> login(@RequestParam Map<String, String> map, HttpSession session) {
		boolean result = false;

		result = service.login(map.get("id"), map.get("password"));
		
		if(result)
		{
			session.setAttribute("user", service.getUser(map.get("id")));
		}

		return SimpleHashMap.newInstance().put("result", result);
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:test-p";
	}
	
}
