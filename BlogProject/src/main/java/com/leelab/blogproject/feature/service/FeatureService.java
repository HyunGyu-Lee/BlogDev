package com.leelab.blogproject.feature.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leelab.blogproject.feature.dao.FeatureDAO;
import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.utils.FileUtils;
import com.leelab.blogproject.utils.StringUtils;
import com.leelab.blogproject.utils.json.SimpleHashMap;
import com.leelab.blogproject.utils.page.PageUtil;
import com.leelab.blogproject.utils.page.PageVo;

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

	public List<FeatureVo> getBlogFeatures(FeatureVo search, PageVo page) {
		return featureDao.selectAll(SimpleHashMap.newInstance().put("search", search).put("page", page));
	}

	public PageVo getPageInfo(FeatureVo search, PageVo page) {
		int totalRecord = featureDao.getFeatureCount(search);
		if(page.getCurrentPage()==0)page.setCurrentPage(1);
		if(page.getPageSize()==0) page.setPageSize(6);
		if(page.getGroupSize()==0)page.setGroupSize(5);
		
		return PageUtil.getPageInfo(totalRecord, page.getPageSize(), page.getGroupSize(), page.getCurrentPage());
	}
	
}
