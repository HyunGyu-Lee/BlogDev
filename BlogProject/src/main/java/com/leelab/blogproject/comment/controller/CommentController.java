package com.leelab.blogproject.comment.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leelab.blogproject.comment.service.CommentService;
import com.leelab.blogproject.comment.vo.CommentVO;
import com.leelab.blogproject.common.annotation.NotLoginCheck;
import com.leelab.blogproject.post.vo.SearchVO;
import com.leelab.blogproject.utils.json.SimpleHashMap;
import com.leelab.blogproject.utils.page.PageVo;

@Controller
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="postComment", method=RequestMethod.POST)
	@NotLoginCheck
	@ResponseBody
	public Map<String, Object> commentList(SearchVO searchVo, PageVo pageVo) {
		logger.info("Comments In {}'s post", searchVo.getPost_id());
		pageVo = commentService.getPageInfo(searchVo, pageVo);
		logger.info("{}",pageVo);
		List<CommentVO> comments = commentService.getComments(searchVo, pageVo);
		return SimpleHashMap.newInstance().put("comments", comments).put("page", pageVo);
	}
	

}
