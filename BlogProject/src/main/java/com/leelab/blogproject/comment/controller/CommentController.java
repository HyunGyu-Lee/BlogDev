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
import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.notification.service.NotificationService;
import com.leelab.blogproject.notification.vo.NotificationBuilder;
import com.leelab.blogproject.notification.vo.NotificationVo;
import com.leelab.blogproject.post.dto.PostDTO;
import com.leelab.blogproject.post.service.PostService;
import com.leelab.blogproject.post.vo.SearchVO;
import com.leelab.blogproject.user.service.UserService;
import com.leelab.blogproject.utils.page.PageVo;

@Controller
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping(value="addComment", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addComment(CommentVO commentVo, PageVo pageVo) {
		logger.info("{}", commentVo);
		commentService.addComment(commentVo);
		SearchVO searchVo = new SearchVO();
		searchVo.setPost_id(commentVo.getPost_id());
		pageVo = commentService.getPageInfo(searchVo, pageVo);
		logger.info("{}", searchVo);
		logger.info("{}", pageVo);
		
		/* 알림처리 */
		PostDTO post = postService.getPostById(commentVo.getPost_id());
		if(!post.getUser_id().equals(commentVo.getUser_id()))
		{
			NotificationVo vo = new NotificationBuilder().setLink("/postview/"+post.getUser_id()+"/"+commentVo.getPost_id())
					 									 .setMessage("<strong>"+userService.getUser(commentVo.getUser_id()).getNickname()+"</strong>님이 댓글을 달았습니다.")
					 									 .setNotificator(commentVo.getUser_id())
					 									 .setNotificateTarget(postService.getPostById(commentVo.getPost_id()).getUser_id())
					 									 .build();
			notificationService.notificate(vo);
		}
		
		return SimpleHashMap.newInstance().put("comments", commentService.getComments(searchVo, pageVo)).put("page", pageVo);
	}
	
	@RequestMapping(value="editComment", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editComment(CommentVO commentVo) {
		logger.info("{}", commentVo);
		commentService.editComment(commentVo);
		return SimpleHashMap.newInstance().put("result", 1);
	}
	
	@RequestMapping(value="deleteComment", method=RequestMethod.POST)
	@ResponseBody
	public void deleteComment(CommentVO commentVo) {
		commentService.deleteComment(commentVo);
	}

}
