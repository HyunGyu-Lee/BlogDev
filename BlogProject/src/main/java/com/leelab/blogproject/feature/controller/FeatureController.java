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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.common.annotation.NotLoginCheck;
import com.leelab.blogproject.common.annotation.RequireAuthCheck;
import com.leelab.blogproject.common.resolver.MultipartRequest;
import com.leelab.blogproject.common.vo.ManagePageMeshType;
import com.leelab.blogproject.feature.service.FeatureService;
import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.subject.service.SubjectService;

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
	@RequireAuthCheck(checkFor="user_id")
	public ModelAndView manage(@RequestParam Map<String, String> requestScope) {
		
		String userId = requestScope.get("user_id");

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
	
	@RequestMapping(value="updateCoverImage", method=RequestMethod.POST) 
	@ResponseBody
	public void updateCoverImage(MultipartRequest request) throws IllegalStateException, IOException {
		logger.info("{}",request.getFile(0));
		logger.info("{}",request.get("user_id"));
		featureService.updateCoverImage(request.get("user_id"), request.getFile(0));
	}
	
	@RequestMapping(value="updateBlogFeature", method=RequestMethod.POST)
	@ResponseBody
	public void updateBlogFeature(FeatureVo feature) {
		featureService.updateBlogFeature(feature);
	}
}
