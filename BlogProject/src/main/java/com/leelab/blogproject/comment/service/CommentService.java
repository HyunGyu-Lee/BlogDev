package com.leelab.blogproject.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.comment.dao.CommentDAO;
import com.leelab.blogproject.comment.vo.CommentVO;
import com.leelab.blogproject.post.vo.SearchVO;
import com.leelab.blogproject.utils.json.SimpleHashMap;
import com.leelab.blogproject.utils.page.PageUtil;
import com.leelab.blogproject.utils.page.PageVo;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDao;
	
	public PageVo getPageInfo(SearchVO searchVo, PageVo pageVo) {
		int totalRecord = commentDao.getCommentsCount(searchVo);
		if(pageVo.getCurrentPage()==0)pageVo.setCurrentPage(1);
		if(pageVo.getPageSize()==0) pageVo.setPageSize(5);
		if(pageVo.getGroupSize()==0)pageVo.setGroupSize(5);
		
		return PageUtil.getPageInfo(totalRecord, pageVo.getPageSize(), pageVo.getGroupSize(), pageVo.getCurrentPage());
	}

	public List<CommentVO> getComments(SearchVO searchVo, PageVo pageVo) {
		return commentDao.selectAll(SimpleHashMap.newInstance().put("search", searchVo).put("page", pageVo));
	}
	
}
