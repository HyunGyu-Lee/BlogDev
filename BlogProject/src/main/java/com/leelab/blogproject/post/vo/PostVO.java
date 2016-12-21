package com.leelab.blogproject.post.vo;

import com.leelab.blogproject.post.dto.PostDTO;

public class PostVO extends PostDTO {

	private String main_category_name;
	private String sub_category_name;
	
	public String getMain_category_name() {
		return main_category_name;
	}
	public void setMain_category_name(String main_category_name) {
		this.main_category_name = main_category_name;
	}
	public String getSub_category_name() {
		return sub_category_name;
	}
	public void setSub_category_name(String sub_category_name) {
		this.sub_category_name = sub_category_name;
	}
}
