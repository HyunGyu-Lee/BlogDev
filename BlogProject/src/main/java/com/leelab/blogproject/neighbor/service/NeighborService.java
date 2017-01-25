package com.leelab.blogproject.neighbor.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.neighbor.dao.NeighborDAO;
import com.leelab.blogproject.neighbor.vo.NeighborVo;

@Service
public class NeighborService {

	private static final Logger logger = LoggerFactory.getLogger(NeighborService.class);

	@Autowired
	private NeighborDAO neighborDao;

	/* 이웃 신청 */
	public void apply(NeighborVo vo) {
		logger.info("신청하는 사람 - {}", vo.getUser_id());
		logger.info("신청받는 사람 - {}", vo.getRel_user_id());
		logger.info("메세지 - {}", vo.getApply_msg());
		
		neighborDao.insert(vo);
	}
	
	public boolean isRelationEstablished(NeighborVo vo) {
		boolean result = false;

		if(neighborDao.select(vo)!=null)result = true;
		
		String temp = vo.getUser_id();
		vo.setUser_id(vo.getRel_user_id());
		vo.setRel_user_id(temp);
		if(neighborDao.select(vo)!=null)result = true;
		
		temp = vo.getUser_id();
		vo.setUser_id(vo.getRel_user_id());
		vo.setRel_user_id(temp);
		return result;
	}
	
	public void action(NeighborVo vo, int action_state) {
		vo.setRel_state(action_state);
		neighborDao.update(vo);
	}
	
	public NeighborVo getRelation(NeighborVo vo) {
		return neighborDao.select(vo);
	}

	public List<NeighborVo> getApplyList(String userId) {
		NeighborVo vo = new NeighborVo();
		vo.setUser_id(userId);
		vo.setRel_state(NeighborVo.STATE_WAIT);
		return neighborDao.selectAll(vo);
	}
	
	public List<NeighborVo> getAcceptableList(String userId) {
		NeighborVo vo = new NeighborVo();
		vo.setRel_user_id(userId);
		vo.setRel_state(NeighborVo.STATE_WAIT);
		return neighborDao.selectAll(vo);
	}

	public List<NeighborVo> getNeighborList(String userId) {
		NeighborVo vo = new NeighborVo();
		vo.setUser_id(userId);
		vo.setRel_state(NeighborVo.STATE_ACCEPT);
		return neighborDao.selectNeighbor(vo);
	}

}
