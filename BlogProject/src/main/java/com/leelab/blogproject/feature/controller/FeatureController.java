package com.leelab.blogproject.feature.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leelab.blogproject.common.annotation.NotLoginCheck;
import com.leelab.blogproject.feature.service.FeatureService;

@Controller
public class FeatureController {
	
	@Autowired
	FeatureService featureService;
	
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
	public ModelAndView manage() {
		ModelAndView mv = new ModelAndView("blog/manage/manage");
		return mv;
	}
	
}
