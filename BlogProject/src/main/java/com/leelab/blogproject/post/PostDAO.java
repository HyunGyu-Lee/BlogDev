package com.leelab.blogproject.post;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {
	
	void insert(PostDTO post);
	PostDTO selectById(int id);
	ArrayList<PostDTO> selectByUserId(String user_id);
	
}
