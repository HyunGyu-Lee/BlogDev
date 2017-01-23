package com.leelab.blogproject.post.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.common.annotation.Notificational;
import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.post.dao.PostDAO;
import com.leelab.blogproject.post.dto.PostDTO;
import com.leelab.blogproject.post.vo.PostVO;
import com.leelab.blogproject.post.vo.SearchVO;
import com.leelab.blogproject.utils.page.PageUtil;
import com.leelab.blogproject.utils.page.PageVo;

@Service
public class PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	
	@Autowired
	PostDAO postDao;

	public void newPost(String user_id, String title, String content, int main_category_id, int sub_category_id) {
		PostDTO post = new PostDTO(0, user_id, title, main_category_id, sub_category_id, 0, content, null);
		logger.info("{}",post);
		postDao.insert(post);
	}

	@Deprecated
	public ArrayList<PostDTO> getUserPost(String user_id) {
		SearchVO vo = new SearchVO();
		vo.setUser_id(user_id);
		return postDao.selectPosts(null);
	}

	public PostDTO getPostDetail(SearchVO searchVO, String viewUserId) {
		increaseHit(searchVO, viewUserId);
		return postDao.selectById(searchVO);
	}

	private void increaseHit(SearchVO searchVO, String viewUserId) {
		String blogUserId = searchVO.getUser_id();
		
		if(!blogUserId.equals(viewUserId))
		{
			postDao.updateHit(searchVO.getPost_id());
		}
		
	}

	public void getPostByCategoryId(int id, String type) {
		if(type.equals("main"))
		{
			/*
			 * ī�װ� ������ ����Ʈ �ִ��� Ȯ���ϱ� ��� �����
			 * Ÿ�Կ� ���� ���� �б����
			 * ī�װ� ���̵�� user�� post �����ϱ� ����� 
			 * */
			postDao.selectByCategoryId(id, type);	
		}
		else
		{
			postDao.selectByCategoryId(id, type);
		}		
	}
	
	@Notificational
	public ArrayList<PostDTO> getPosts(SearchVO searchVo, PageVo pageVo) {
		logger.debug("{}", this.getClass());
		return postDao.selectPosts(SimpleHashMap.newInstance().put("search", searchVo).put("page", pageVo));
	}

	public ArrayList<PostVO> getPostsInPage(SearchVO searchVo, PageVo pageVo) {
		return postDao.selectPostsInPage(searchVo);
	};
	
	public PageVo getPageInfo(SearchVO searchVo, PageVo pageVo) {
		int totalRecord = postDao.getPostsCount(searchVo);
		if(pageVo.getCurrentPage()==0)pageVo.setCurrentPage(1);
		if(pageVo.getPageSize()==0) pageVo.setPageSize(5);
		if(pageVo.getGroupSize()==0)pageVo.setGroupSize(5);
		
		return PageUtil.getPageInfo(totalRecord, pageVo.getPageSize(), pageVo.getGroupSize(), pageVo.getCurrentPage());
	}

	public void deletePost(int post_id) {
		postDao.delete(post_id);
	}

	public void updatePost(PostDTO post) {
		postDao.update(post);
	}

}
