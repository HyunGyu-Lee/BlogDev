package com.leelab.blogproject.post;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {
	
	void insert(PostDTO post);
	PostDTO selectById(int id);
	ArrayList<PostDTO> selectPosts(@Param("search") SearchVO searchVO);
	void selectByCategoryId(int id, @Param("type") String type);
	
}
