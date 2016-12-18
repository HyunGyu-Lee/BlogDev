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
	
	private SqlSessionTemplate session;
	
	PostDAO postDao;
	
	
	@Autowired
	public void setSession(SqlSessionTemplate session) {
		this.session = session;
		postDao = this.session.getMapper(PostDAO.class);
	}

	public void newPost(String user_id, String title, String content, int main_category_id, int sub_category_id) {
		PostDTO post = new PostDTO(0, user_id, title, main_category_id, sub_category_id, 0, content, null);
		logger.info("{}",post);
		postDao.insert(post);
	}

	public ArrayList<PostDTO> getUserPost(String user_id) {
		return postDao.selectByUserId(user_id);
	}
	
	
	
}
