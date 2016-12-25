package com.leelab.blogproject.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.leelab.blogproject.comment.vo.CommentVO;
import com.leelab.blogproject.post.vo.SearchVO;

@Repository
public interface CommentDAO {

	List<CommentVO> selectAll(@Param("search")SearchVO svo);
	
	void insert(@Param("comment")CommentVO comment);
	
	void update(@Param("comment")CommentVO comment);
	
	void delete(int id);
}
