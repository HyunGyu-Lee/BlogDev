package com.leelab.blogproject.common.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.feature.service.FeatureService;
import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.subject.service.SubjectService;
import com.leelab.blogproject.user.dto.UserDTO;
import com.leelab.blogproject.user.service.UserService;
import com.leelab.blogproject.utils.StringUtils;
import com.leelab.blogproject.utils.page.PageVo;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private FeatureService featureService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session, @RequestParam Map<String, String> request, PageVo page) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if(session.getAttribute("user")!=null)
		{
			String id = ((UserDTO)session.getAttribute("user")).getId();
			session.setAttribute("user", userService.getUser(id));
		}
		
		ModelAndView mv = new ModelAndView("home/home");
		mv.addObject("subjects", subjectService.getSubjects());
		
		String type = null;
		String requestType = request.get("type");
		FeatureVo search = new FeatureVo();

		if(request.isEmpty() || requestType == null)
		{
			type = "home";
			int subject_id = 0;
			if(request.get("subject_id")!=null) subject_id = Integer.parseInt(request.get("subject_id"));
			search.setSubject_id(subject_id);
			page = featureService.getPageInfo(search, page);
			logger.info("{}",page);
			mv.addObject("features", featureService.getBlogFeatures(search, page));
		}
		else if(requestType.equals("search"))
		{
			type = "search";
			search.setSearch_by(request.get("search_by"));
			search.setKeyword(request.get("keyword"));
			logger.info("{}", search);
			page = featureService.getPageInfo(search, page);
			logger.info("{}",page);
			mv.addObject("features", featureService.getBlogFeatures(search, page));
			mv.addObject("page", page);
			mv.addObject("search_by", request.get("search_by"));
		}
		mv.addObject("page", page);
		mv.addObject("qryString", StringUtils.toQueryString(search));
		mv.addObject("type", type);
		return mv;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeView(HttpServletRequest request) {
		return "redirect:/";
	}
	
	@RequestMapping("/openRegister")
	public String openRegister() {
		logger.info("ȸ������ ����");
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
