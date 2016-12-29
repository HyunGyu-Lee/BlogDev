package com.leelab.blogproject.comment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.leelab.blogproject.comment.vo.CommentVO;
import com.leelab.blogproject.post.vo.SearchVO;

@Repository
public interface CommentDAO {

	List<CommentVO> selectAll(@Param("vos")Map<String, Object> map);
	
	void insert(@Param("comment")CommentVO comment);
	
	void update(@Param("comment")CommentVO comment);
	
	void delete(int id);

	int getCommentsCount(@Param("search") SearchVO searchVo);

	CommentVO select(int id);

}
