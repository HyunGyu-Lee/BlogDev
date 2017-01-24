package com.leelab.blogproject.neighbor.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.neighbor.service.NeighborService;
import com.leelab.blogproject.neighbor.vo.NeighborVo;

@Controller
public class NeighborController {

	private static final Logger logger = LoggerFactory.getLogger(NeighborController.class);

	@Autowired
	private NeighborService nService;
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doApply(NeighborVo vo) {
		logger.info("{}",vo);

		if(nService.isRelationEstablished(vo))
		{
			return SimpleHashMap.newInstance().put("code", -1);
		}
		else
		{
			nService.apply(vo);			
		}
		
		return SimpleHashMap.newInstance().put("code", 1);
	}
	
	@RequestMapping(value="/accept", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doAccept(NeighborVo vo) {
		logger.info("{}",vo);
		nService.action(vo, NeighborVo.STATE_ACCEPT);
		return SimpleHashMap.newInstance().put("code", 1);
	}
	
	@RequestMapping(value="/deny", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doDeny(NeighborVo vo) {
		nService.action(vo, NeighborVo.STATE_DENY);
		return SimpleHashMap.newInstance().put("code", 1);
	}
}
