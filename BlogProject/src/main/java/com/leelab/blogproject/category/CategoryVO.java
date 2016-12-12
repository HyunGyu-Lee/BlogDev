package com.leelab.blogproject.category;

import java.util.ArrayList;
import java.util.HashMap;

import com.leelab.blogproject.category.main.MainCategoryDTO;
import com.leelab.blogproject.category.sub.SubCategoryDTO;

public class CategoryVO {

	HashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = new HashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>>();
	
	public CategoryVO(){}
	
	public void addMainCategory(MainCategoryDTO mainCategory) {
		category.put(mainCategory, null);
	}
	
	public void addCategories(MainCategoryDTO mainCategory, ArrayList<SubCategoryDTO> subCategories) {
		category.put(mainCategory, subCategories);
	}
	
	public void addSubCategory(MainCategoryDTO mainCategory, SubCategoryDTO subCategory) {
		ArrayList<SubCategoryDTO> subCategories = category.get(mainCategory);
		
		if(subCategories==null) subCategories = new ArrayList<SubCategoryDTO>();
		
		subCategories.add(subCategory);
	}
	
	public HashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>> getMap() {
		return category;
	}
	
}
