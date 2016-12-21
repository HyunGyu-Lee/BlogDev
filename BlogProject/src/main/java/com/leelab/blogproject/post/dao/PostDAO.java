package com.leelab.blogproject.post.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.leelab.blogproject.post.dto.PostDTO;
import com.leelab.blogproject.post.vo.SearchVO;

@Repository
public interface PostDAO {
	
	void insert(PostDTO post);
	
	PostDTO selectById(@Param("search")SearchVO searchVo);
	
	ArrayList<PostDTO> selectPosts(@Param("vos") HashMap<String, Object> vos);
	
	void selectByCategoryId(int id, @Param("type") String type);
	
	int getPostsCount(@Param("search") SearchVO searchVo);

	
	
}
