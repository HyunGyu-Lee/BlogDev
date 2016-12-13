package com.leelab.blogproject.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.category.CategoryService;

@Controller
public class BlogController {

	@Autowired
	private CategoryService categoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	@RequestMapping("manage/categoryInfo")
	public ModelAndView manage(@RequestParam("blogId") String blogId) {
		ModelAndView mv = new ModelAndView("blog/manage/manage");
		mv.addObject("blogId", blogId);
		mv.addObject("type", "categoryInfo");
		mv.addObject("category", categoryService.getUserCategory(blogId));
		return mv;
	}
	
	@RequestMapping(value="manage/editCategoryName", method=RequestMethod.POST)
	@ResponseBody
	public void editCategoryName(@RequestParam Map<String, String> requestScope) {
		String name = requestScope.get("name");
		int id = Integer.parseInt(requestScope.get("id"));
		String type = requestScope.get("type");
		
		categoryService.editCategoryName(id, name, type);		
	}
	
}
