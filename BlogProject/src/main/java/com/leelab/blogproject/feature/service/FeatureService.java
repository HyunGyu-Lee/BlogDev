package com.leelab.blogproject.feature.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.feature.dao.FeatureDAO;
import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.utils.FileUtils;

@Service
public class FeatureService {

	@Autowired
	private FeatureDAO featureDao;
	
	public FeatureVo getBlogFeature(String user_id) {
		return featureDao.select(user_id);
	}
	
	public byte[] getBackgroundImage(String user_id) throws IOException {
		return FileUtils.read(FileUtils.BLOG+getBlogFeature(user_id).getBgimg());
	}

}
