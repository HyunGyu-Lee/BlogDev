package com.leelab.blogproject.category.controller;

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

import com.leelab.blogproject.category.service.CategoryService;
import com.leelab.blogproject.post.service.PostService;

@Controller
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
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
		logger.info("{} - {} 카테고리 삭제",type,id);
		categoryService.deleteCategory(id, type);
	}
	
	@RequestMapping(value="manage/addCategory", method=RequestMethod.POST) 
	@ResponseBody
	public void addCategory(@RequestParam Map<String, String> requestScope) {
		String id = requestScope.get("id");
		String name = requestScope.get("name");
		String level = requestScope.get("level");
		String blog_id = requestScope.get("blogId");
		
		if(level.equals("current"))
		{
			int next_category_id = Integer.parseInt(requestScope.get("next_category_id"));
			String type = requestScope.get("type");

			categoryService.addCategoryOnCurrentLevel(Integer.parseInt(id), next_category_id, type, name);
		}
		else if(level.equals("first"))
		{
			categoryService.addFirstCategory(name, blog_id);
		}
		else
		{
			categoryService.addCategoryInnerLevel(Integer.parseInt(id), name);
		}
	}
	
}
