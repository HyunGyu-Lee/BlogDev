package com.leelab.blogproject.neighbor.service;

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

}
