package com.leelab.blogproject.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.subject.service.SubjectService;
import com.leelab.blogproject.user.dto.UserDTO;
import com.leelab.blogproject.user.service.UserService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		if(session.getAttribute("user")!=null)
		{
			String id = ((UserDTO)session.getAttribute("user")).getId();
			session.setAttribute("user", userService.getUser(id));
		}
		
		ModelAndView mv = new ModelAndView("home/home");
		mv.addObject("subjects", subjectService.getSubjects());
		return mv;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeView(HttpServletRequest request) {
		return "redirect:/";
	}
	
	@RequestMapping("/openRegister")
	public String openRegister() {
		logger.info("회원가입 오픈");
		return "register/register";
	}
	
	@RequestMapping("/openLogin")
	public String openLogin(HttpServletRequest request, Model model) {
		String uri = request.getParameter("requestUri");
		if(uri!=null)model.addAttribute("redirectUri", uri.replaceAll("AND", "&"));
		return "login/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/userInfo")
	public String userInfo(@SessionAttribute UserDTO user, Model model) {
		model.addAttribute("user", userService.getUserInfo(user.getId()));
		return "home/userInfo";
	}
	
}
