package com.leelab.blogproject.neighbor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.neighbor.dao.NeighborDAO;
import com.leelab.blogproject.neighbor.vo.NeighborVo;

@Service
public class NeighborService {

	@Autowired
	private NeighborDAO neighborDao;
	
	/* 이웃 신청 */
	public void apply(NeighborVo vo) {
		//if(vo.getApply_msg()==null)vo.setApply_msg("우리 서로 이웃해요~");
		neighborDao.insert(vo);
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
