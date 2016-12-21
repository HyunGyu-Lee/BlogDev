package com.leelab.blogproject.category.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.leelab.blogproject.category.dto.SubCategoryDTO;

@Repository
public interface SubCategoryDAO {

	void insert(SubCategoryDTO dto);	
	
	void update(SubCategoryDTO dto);	
	
	void delete(int id);	
	
	void deleteAll();		
	
	Integer getMaxOrderByMainCategoryId(int main_category_id);
	
	SubCategoryDTO selectById(int id);
	
	ArrayList<SubCategoryDTO> selectByUserId(String userId);	
	
	ArrayList<SubCategoryDTO> selectByMainCategoryId(int id);
	
	ArrayList<SubCategoryDTO> selectAll();
	
	ArrayList<SubCategoryDTO> selectByUserIdOver(String userId, int category_order, int main_category_id);
}
