package com.leelab.blogproject.post;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public ArrayList<PostDTO> getUserPost(String user_id) {
		SearchVO vo = new SearchVO();
		vo.setUser_id(user_id);
		return postDao.selectPosts(vo);
	}

	public PostDTO getPostByPostId(int postId) {
		return postDao.selectById(postId);
	}

	public void getPostByCategoryId(int id, String type) {
		if(type.equals("main"))
		{
			/*
			 * 카테고리 삭제시 포스트 있는지 확인하기 기능 만들기
			 * 타입에 따라 쿼리 분기실행
			 * 카테고리 아이디로 user의 post 선택하기 만들기 
			 * */
			postDao.selectByCategoryId(id, type);	
		}
		else
		{
			postDao.selectByCategoryId(id, type);
		}		
	}

	public ArrayList<PostDTO> getPosts(SearchVO searchVO) {
		return postDao.selectPosts(searchVO);
	}
	
	
	
}
