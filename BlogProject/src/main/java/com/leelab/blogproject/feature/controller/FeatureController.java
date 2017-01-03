package com.leelab.blogproject.feature.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.common.annotation.NotLoginCheck;
import com.leelab.blogproject.common.exception.GeneralBlogException;
import com.leelab.blogproject.common.vo.ManagePageMeshType;
import com.leelab.blogproject.feature.service.FeatureService;
import com.leelab.blogproject.subject.service.SubjectService;
import com.leelab.blogproject.user.dto.UserDTO;

@Controller
public class FeatureController {
	
	@Autowired
	FeatureService featureService;

	@Autowired
	SubjectService subjectService;
	
	private static final Logger logger = LoggerFactory.getLogger(FeatureController.class);
	
	@RequestMapping("blogBgImage/{id}")
	@NotLoginCheck
	@ResponseBody
	public ResponseEntity<byte[]> blogBgImage(@PathVariable String id) throws IOException {
		logger.info("{}", id);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(featureService.getBackgroundImage(id), header, HttpStatus.CREATED);
	}
	
	@RequestMapping("manage")
	public ModelAndView manage(@RequestParam Map<String, String> requestScope, @SessionAttribute("user") UserDTO user) throws GeneralBlogException {
		
		String userId = requestScope.get("user_id");
		
		if(!user.getId().equals(userId))throw new GeneralBlogException("페이지에 접근할 권한이 없습니다.");
		
		ModelAndView mv = new ModelAndView("blog/manage/manage");
		
		String type = requestScope.get("type");
		
		if(type.equals(ManagePageMeshType.TYPOGRAPHY))
		{
			logger.info("{}", featureService.getBlogFeature(userId));
			mv.addObject("type", type);
			mv.addObject("feature",featureService.getBlogFeature(userId));
			mv.addObject("subject", subjectService.getSubjects());
		}
		
		return mv;
	}
	
}
