package com.leelab.blogproject.feature.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.leelab.blogproject.feature.vo.FeatureVo;

@Repository
public interface FeatureDAO {
	List<FeatureVo> selectAll(HashMap<String, Object> vos);
	
	FeatureVo select(String user_id);
	
	void insert(FeatureVo featureVo);
	
	void delete(FeatureVo featureVo);
	
	void update(FeatureVo featureVo);

	int getFeatureCount(FeatureVo search);
}
