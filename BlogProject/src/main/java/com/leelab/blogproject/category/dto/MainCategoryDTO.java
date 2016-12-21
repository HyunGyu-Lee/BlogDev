package com.leelab.blogproject.category.dto;

public class MainCategoryDTO {

	private int id;
	private String user_id;
	private String name;
	private int category_order;
	
	public MainCategoryDTO(){}

	public MainCategoryDTO(int id, String user_id, String name, int category_order) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.category_order = category_order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory_order() {
		return category_order;
	}

	public void setCategory_order(int category_order) {
		this.category_order = category_order;
	}

	@Override
	public String toString() {
		return "MainCategoryDTO [id=" + id + ", user_id=" + user_id + ", name=" + name + ", category_order="
				+ category_order + "]";
	}
	
}
