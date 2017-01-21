package com.leelab.blogproject.feature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.feature.service.FeatureService;
import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.neighbor.service.NeighborService;
import com.leelab.blogproject.neighbor.vo.NeighborVo;
import com.leelab.blogproject.subject.service.SubjectService;
import com.leelab.blogproject.user.dto.UserDTO;

@Controller
public class FeatureController {
	
	@Autowired
	private FeatureService featureService;

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private NeighborService nService;
	
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
		else if(type.equals(ManagePageMeshType.NEIGHBOR))
		{
			logger.info("{}",nService.getApplyList(userId));
			String sub_type = requestScope.get("sub_type");
			mv.addObject("type", type);
			mv.addObject("sub_type", sub_type);
			if(sub_type.equals("to"))
			{
				mv.addObject("relations", nService.getApplyList(userId));
			}
			else if(sub_type.equals("from"))
			{
				mv.addObject("relations", nService.getAcceptableList(userId));
			}
			else if(sub_type.equals("we"))
			{
				mv.addObject("relations", nService.getNeighborList(userId));
			}
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
	
	@RequestMapping("/getFeature")
	@ResponseBody
	public HashMap<String, Object> getFeature(@RequestParam String user_id) {
		return SimpleHashMap.newInstance().put("feature", featureService.getBlogFeature(user_id));
	}
	
	@RequestMapping("/getUserDetail")
	@ResponseBody
	@NotLoginCheck
	public HashMap<String, Object> getUserDetail(@RequestParam String rel_user_id, HttpSession session) {
		FeatureVo vo = featureService.getBlogFeature(rel_user_id);
		UserDTO user = (UserDTO)session.getAttribute("user");
		
		SimpleHashMap response = SimpleHashMap.newInstance().put("feature", vo);
		
		if(user!=null)
		{
			NeighborVo nVo = new NeighborVo();
			nVo.setUser_id(user.getId());
			nVo.setRel_user_id(rel_user_id);
			nVo = nService.getRelation(nVo);
			if(nVo==null)response.put("rel_state", 0);
			else response.put("rel_state", nVo.getRel_state());
		}
		else
		{
			response.put("rel_state", 0);
		}
		
		logger.info("{}", response);
		return response;
	}
	
}
