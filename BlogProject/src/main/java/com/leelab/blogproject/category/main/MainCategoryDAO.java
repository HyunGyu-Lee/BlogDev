package com.leelab.blogproject.category.main;

import java.util.ArrayList;

public interface MainCategoryDAO {

	void insert(MainCategoryDTO dto);	
	
	void update(MainCategoryDTO dto);	
	
	void delete(int id);	
	
	void deleteAll();		
	
	MainCategoryDTO selectById(int id);
	
	ArrayList<MainCategoryDTO> selectByUserId(String userId);	
	
	ArrayList<MainCategoryDTO> selectAll();
	
	int getNextOrderByUserId(String userId);
	
}
