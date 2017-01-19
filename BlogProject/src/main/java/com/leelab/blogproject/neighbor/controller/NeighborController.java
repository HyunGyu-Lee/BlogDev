package com.leelab.blogproject.neighbor.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leelab.blogproject.neighbor.service.NeighborService;
import com.leelab.blogproject.neighbor.vo.NeighborVo;
import com.leelab.blogproject.neighbor.vo.RelationVo;

@Controller
public class NeighborController {

	private static final Logger logger = LoggerFactory.getLogger(NeighborController.class);

	@Autowired
	private NeighborService nService;
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	@ResponseBody
	public void doApply(NeighborVo vo) {
		logger.info("{}",vo);
		nService.apply(vo);
	}
	
	
	
}
