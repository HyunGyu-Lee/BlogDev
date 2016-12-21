package com.leelab.blogproject.controller;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.annotation.NotLoginCheck;
import com.leelab.blogproject.category.CategoryService;
import com.leelab.blogproject.category.main.MainCategoryDTO;
import com.leelab.blogproject.category.sub.SubCategoryDTO;
import com.leelab.blogproject.post.PostDTO;
import com.leelab.blogproject.post.PostService;
import com.leelab.blogproject.post.SearchVO;
import com.leelab.blogproject.user.UserDTO;
import com.leelab.blogproject.user.UserService;

@Controller
public class BlogController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
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
		
		postService.getPostByCategoryId(id, type);
		
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
	
	@RequestMapping(value="writePost", method=RequestMethod.POST)
	@ResponseBody
	public void writePost(@RequestParam Map<String, String> requestScope) {
		String user_id = requestScope.get("user_id");
		String title = requestScope.get("title");
		String content = requestScope.get("content");
		int main_category_id = Integer.parseInt(requestScope.get("main_category_id"));
		int sub_category_id = Integer.parseInt(requestScope.get("sub_category_id"));

		postService.newPost(user_id, title, content, main_category_id, sub_category_id);
	}
	
	@NotLoginCheck
	@RequestMapping("/{id}") 
	public ModelAndView openMyBlog(SearchVO searchVO, @PathVariable String id) {
		logger.info("Open Blog to {}", id);
		
		UserDTO user = userService.getUser(id);
		Map<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = categoryService.getUserCategory(id);

		searchVO.setUser_id(id);

		ArrayList<PostDTO> posts = postService.getPosts(searchVO);
		
		ModelAndView mv = new ModelAndView("blog/blog");
		
		mv.addObject("user", user);
		mv.addObject("category", category);
		mv.addObject("posts", posts);

		return mv;
	}
	
	@NotLoginCheck
	@RequestMapping("/postview/{id}/{post_id}")
	public String viewPost(@PathVariable(name="id") String id, @PathVariable(name="post_id") String postId, Model model) {
		logger.info("Open blog to {} - No.{}'s post",id, postId);
		PostDTO post = postService.getPostByPostId(Integer.parseInt(postId));
		
		if(post==null)
		{
			return "error";
		}
		
		Map<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = categoryService.getUserCategory(id);
		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("category", category);
		model.addAttribute("post", post);
		return "blog/blog";
	}
	
	
}
