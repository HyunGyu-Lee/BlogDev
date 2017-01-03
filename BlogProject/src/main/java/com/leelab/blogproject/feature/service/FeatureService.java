package com.leelab.blogproject.feature.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leelab.blogproject.feature.dao.FeatureDAO;
import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.utils.FileUtils;
import com.leelab.blogproject.utils.StringUtils;

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

	public void updateCoverImage(String user_id, MultipartFile file) throws IllegalStateException, IOException {
		String extension = FileUtils.getFileExtension(file.getOriginalFilename());
		String saveFileName = StringUtils.getRandomString()+extension;

		FeatureVo feature = getBlogFeature(user_id);
		
		feature.setBgimg(saveFileName);
		featureDao.update(feature);			
		
		FileUtils.save(file, FileUtils.BLOG+saveFileName);
	}

	public void updateBlogFeature(FeatureVo feature) {
		featureDao.update(feature);
	}

}
