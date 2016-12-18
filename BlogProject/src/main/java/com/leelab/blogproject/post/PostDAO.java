package com.leelab.blogproject.post;

import java.util.ArrayList;

public interface PostDAO {
	
	void insert(PostDTO post);
	PostDTO selectById(int id);
	ArrayList<PostDTO> selectByUserId(String user_id);
	
}
