package com.leelab.blogproject.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.leelab.blogproject.user.UserDTO;
import com.leelab.blogproject.user.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userSerivce;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		return "home/home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeView(HttpServletRequest request) {
		return "home/home";
	}
	
	@RequestMapping("/openRegister")
	public String openRegister() {
		return "register/register";
	}
	
	@RequestMapping("/openLogin")
	public String openLogin() {
		return "login/login";
	}

	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/userInfo")
	public String userInfo() {
		return "home/userInfo";
	}
}
