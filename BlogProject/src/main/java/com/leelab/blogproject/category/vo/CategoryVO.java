package com.leelab.blogproject.category.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.leelab.blogproject.category.dto.MainCategoryDTO;
import com.leelab.blogproject.category.dto.SubCategoryDTO;

public class CategoryVO {
	/*
	 * 이미 Database에서 꺼내올 때 메인 카테고리 Order에 대해 오름차순 정렬을 수행,
	 * 일반 HashMap에 저장하면 정렬이 유지되지 않기 때문에 정렬순서를 유지하기 위한 LinkedHashMap 사용
	 * */
	LinkedHashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = new LinkedHashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>>();
	
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
