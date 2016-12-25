package com.leelab.blogproject.comment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leelab.blogproject.post.vo.SearchVO;
import com.leelab.blogproject.utils.page.PageVo;

@Controller
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@RequestMapping("/comment")
	public void commentList(SearchVO searchVo, PageVo pageVo) {
		
	}
	
}
