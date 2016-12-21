package com.leelab.blogproject.post.controller;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.category.dto.MainCategoryDTO;
import com.leelab.blogproject.category.dto.SubCategoryDTO;
import com.leelab.blogproject.category.service.CategoryService;
import com.leelab.blogproject.common.annotation.NotLoginCheck;
import com.leelab.blogproject.post.dto.PostDTO;
import com.leelab.blogproject.post.service.PostService;
import com.leelab.blogproject.post.vo.SearchVO;
import com.leelab.blogproject.user.dto.UserDTO;
import com.leelab.blogproject.user.service.UserService;
import com.leelab.blogproject.utils.page.PageVo;

@Controller
public class PostController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@RequestMapping("openWritePost")
	public ModelAndView openWritePost(@RequestParam("blogId") String id) {
		ModelAndView mv = new ModelAndView("blog/writePost");
		mv.addObject("category", categoryService.getUserCategory(id));
		mv.addObject("id", id);
		return mv;
	}
	
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
	public ModelAndView viewPosts(SearchVO searchVo, PageVo pageVo, @PathVariable String id) {
		logger.info("Open Blog to {}", id);
		
		UserDTO user = userService.getUser(id);
		Map<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = categoryService.getUserCategory(id);

		searchVo.setUser_id(id);
		logger.info("{}", searchVo);
		ArrayList<PostDTO> posts = postService.getPosts(searchVo, pageVo);
		pageVo = postService.getPageInfo(searchVo, pageVo);

		ModelAndView mv = new ModelAndView("blog/blog");		
		mv.addObject("user", user);
		mv.addObject("category", category);
		mv.addObject("posts", posts);
		mv.addObject("page", pageVo);
		return mv;
	}
	
	@NotLoginCheck
	@RequestMapping("/postview/{id}/{post_id}")
	public ModelAndView viewPost(@PathVariable(name="id") String id, @PathVariable(name="post_id") String postId, SearchVO searchVO) {
		logger.info("Open blog to {} - No.{}'s post",id, postId);
		ModelAndView mv = new ModelAndView("blog/blog");
		
		PostDTO post = postService.getPostDetail(searchVO);
		
		if(post==null)
		{
			mv.setViewName("error");
			return mv;
		}
		
		Map<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = categoryService.getUserCategory(id);
		mv.addObject("user", userService.getUser(id));
		mv.addObject("category", category);
		mv.addObject("post", post);
		return mv;
	}
	
	
}
