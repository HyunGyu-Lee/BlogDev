package com.leelab.blogproject.neighbor.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.leelab.blogproject.neighbor.vo.NeighborVo;

@Repository
public interface NeighborDAO {

	public ArrayList<NeighborVo> selectAll(NeighborVo vo);
	
	public void insert(NeighborVo vo);
	
	public void update(NeighborVo vo);

	public NeighborVo select(NeighborVo vo);

	public List<NeighborVo> selectNeighbor(NeighborVo vo);
}
