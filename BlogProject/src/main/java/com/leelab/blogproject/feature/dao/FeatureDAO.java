package com.leelab.blogproject.feature.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.post.vo.SearchVO;

@Repository
public interface FeatureDAO {
	List<FeatureVo> selectAll(SearchVO search);
	
	FeatureVo select(String user_id);
	
	void insert(FeatureVo featureVo);
	
	void delete(FeatureVo featureVo);
	
	void update(FeatureVo featureVo);
}
