package com.leelab.blogproject.category.sub;

import java.util.ArrayList;


public interface SubCategoryDAO {

	void insert(SubCategoryDTO dto);	
	
	void update(SubCategoryDTO dto);	
	
	void delete(int id);	
	
	void deleteAll();		
	
	SubCategoryDTO selectById(int id);
	
	ArrayList<SubCategoryDTO> selectByUserId(String userId);	
	
	ArrayList<SubCategoryDTO> selectByMainCategoryId(int id);
	
	ArrayList<SubCategoryDTO> selectAll();
	
}
