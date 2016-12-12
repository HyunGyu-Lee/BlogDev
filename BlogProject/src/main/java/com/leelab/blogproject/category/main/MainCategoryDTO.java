package com.leelab.blogproject.category.main;

public class MainCategoryDTO {

	private int id;
	private String user_id;
	private String name;
	
	public MainCategoryDTO(){}

	public MainCategoryDTO(int id, String user_id, String name) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.name = name;
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

	@Override
	public String toString() {
		return "MainCategoryDTO [id=" + id + ", user_id=" + user_id + ", name=" + name + "]";
	}
	
}
