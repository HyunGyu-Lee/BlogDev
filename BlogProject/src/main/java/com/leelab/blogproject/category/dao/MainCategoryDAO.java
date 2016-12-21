package com.leelab.blogproject.category.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.leelab.blogproject.category.dto.MainCategoryDTO;

@Repository
public interface MainCategoryDAO {

	void insert(MainCategoryDTO dto);	
	
	void update(MainCategoryDTO dto);	
	
	void delete(int id);	
	
	void deleteAll();		
	
	MainCategoryDTO selectById(int id);
	
	ArrayList<MainCategoryDTO> selectByUserId(String userId);
	
	ArrayList<MainCategoryDTO> selectByUserIdOver(String userId, int category_order);
	
	ArrayList<MainCategoryDTO> selectAll();
	
	int getNextOrderByUserId(String userId);
	
}
