package com.leelab.blogproject.controller;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.leelab.blogproject.category.CategoryService;
import com.leelab.blogproject.category.main.MainCategoryDTO;
import com.leelab.blogproject.category.sub.SubCategoryDTO;
import com.leelab.blogproject.user.UserDTO;
import com.leelab.blogproject.user.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session) {
		if(session.getAttribute("user")!=null)
		{
			String id = ((UserDTO)session.getAttribute("user")).getId();
			session.setAttribute("user", userService.getUser(id));
		}
		return "home/home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeView(HttpServletRequest request) {
		return "redirect:/";
	}
	
	@RequestMapping("/openRegister")
	public String openRegister() {
		return "register/register";
	}
	
	@RequestMapping("/openLogin")
	public String openLogin(HttpServletRequest request, Model model) {
		String uri = request.getParameter("requestUri");
		if(uri!=null)model.addAttribute("redirectUri", uri);
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
	
	@RequestMapping("/{id}") 
	public String openMyBlog(@PathVariable String id, Model model) {
		logger.info("Open Blog to {}", id);
		
		UserDTO user = userService.getUser(id);
		HashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = categoryService.getUserCategory(id);
		
		model.addAttribute("user", user);
		model.addAttribute("category", category);
		model.addAttribute("category-keySet", category.keySet());
		
		return "blog/blog";
	}
}
