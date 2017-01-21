package com.leelab.blogproject;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.neighbor.dao.NeighborDAO;
import com.leelab.blogproject.neighbor.vo.NeighborVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"classpath:config/spring/context-*.xml"}
)
public class NeighborTestCase {

	@Autowired
	private NeighborDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(NeighborTestCase.class);
	
	@Test
	public void test() {
		NeighborVo vo = new NeighborVo();
		vo.setUser_id("admin");
		vo.setRel_state(NeighborVo.STATE_ACCEPT);
		List<NeighborVo> list = dao.selectNeighbor(vo);
		
		for(NeighborVo item : list)
		{
			logger.info("{}", item);
		}
		
	}
	
}
