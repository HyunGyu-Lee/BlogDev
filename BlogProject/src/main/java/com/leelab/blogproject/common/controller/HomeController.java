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
	
	/**
	 * Home 뷰 요청
	 * @param HttpSession
	 * @return String - "home/home"
	 * */
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
	
	/**
	 * Home 뷰 요청, "/"로 리다이렉트
	 * @see HomeController#home(HttpSession)
	 * @param -
	 * @return String - "redirect:/"
	 * */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeView(HttpServletRequest request) {
		return "redirect:/";
	}
	
	/**
	 * Register 뷰 요청
	 * @param -
	 * @return String - "register/register"
	 * */
	@RequestMapping("/openRegister")
	public String openRegister() {
		logger.info("회원가입창 오픈");
		return "register/register";
	}
	
	/**
	 * 로그인 화면으로 이동 요청
	 * @param HttpServletRequest, Model
	 * @return String - "home/home"
	 * */
	@RequestMapping("/openLogin")
	public String openLogin(HttpServletRequest request, Model model) {
		String uri = request.getParameter("requestUri");
		if(uri!=null)model.addAttribute("redirectUri", uri);
		return "login/login";
	}
	
	/**
	 * 로그아웃 요청<br/>
	 * HttpSession객체의 invalidate메소드 호출 후 Home으로 리다이렉트
	 * @param HttpSession
	 * @return String - "redirect:/"
	 * */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	/**
	 * 사용자 정보 보기 화면으로 이동 요청
	 * @param UserDTO, Model
	 * @return String - "home/userInfo"
	 * */
	@RequestMapping("/userInfo")
	public String userInfo(@SessionAttribute UserDTO user, Model model) {
		model.addAttribute("user", userService.getUserInfo(user.getId()));
		return "home/userInfo";
	}
	
}
