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
	
	@RequestMapping(value="manage/editCategoryOrder", method=RequestMethod.POST) 
	@ResponseBody
	public void editCategoryOrder(@RequestParam Map<String, String> requestScope) {
		int targetId = Integer.parseInt(requestScope.get("targetId"));
		int switchedId = Integer.parseInt(requestScope.get("switchedId"));
		String type = requestScope.get("type");
		
		categoryService.editCategoryOrder(targetId, switchedId, type);
	}
	
	@RequestMapping(value="manage/deleteCategory", method=RequestMethod.POST)
	@ResponseBody
	public void deleteCategory(@RequestParam Map<String, String> requestScope) {
		int id = Integer.parseInt(requestScope.get("id"));
		String type = requestScope.get("type");
		categoryService.deleteCategory(id, type);
	}
	
	@RequestMapping(value="manage/addCategory", method=RequestMethod.POST) 
	@ResponseBody
	public void addCategory(@RequestParam Map<String, String> requestScope) {
		int id = Integer.parseInt(requestScope.get("id"));
		String name = requestScope.get("name");
		String level = requestScope.get("level");
		
		if(level.equals("current"))
		{
			int next_category_id = Integer.parseInt(requestScope.get("next_category_id"));
			String type = requestScope.get("type");

			categoryService.addCategoryOnCurrentLevel(id, next_category_id, type, name);
		}
		else
		{
			categoryService.addCategoryInnerLevel(id, name);
		}
	}
	
	@RequestMapping("openWritePost")
	public ModelAndView openWritePost(@RequestParam("blogId") String id) {
		ModelAndView mv = new ModelAndView("blog/writePost");
		mv.addObject("category", categoryService.getUserCategory(id));
		mv.addObject("id", id);
		return mv;
	}
	
	// content 한번더 압축해서 보내보기
	@RequestMapping(value="postPreview", method=RequestMethod.POST)
	public ModelAndView postPreview(@RequestParam Map<String, String> requestScope) {
		
		String ctn = requestScope.get("content");
		
		logger.info("Content-length : {}", ctn.length());
		
		ModelAndView mv = new ModelAndView("blog/preview");
		mv.addObject("title", requestScope.get("title"));
		mv.addObject("categoryName", requestScope.get("categoryName"));
		mv.addObject("content", requestScope.get("content"));
		mv.addObject("category", categoryService.getUserCategory(requestScope.get("blogId")));
		return mv;
	}
}
