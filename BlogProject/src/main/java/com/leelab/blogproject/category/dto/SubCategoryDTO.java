package com.leelab.blogproject.category.dto;

public class SubCategoryDTO  {
	
	private int id;
	private String user_id;
	private String name;
	private int category_order;
	private int main_category_id;
	
	public SubCategoryDTO(){}

	public SubCategoryDTO(int id, String user_id, String name, int category_order, int main_category_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.category_order = category_order;
		this.main_category_id = main_category_id;
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

	public int getMain_category_id() {
		return main_category_id;
	}

	public void setMain_category_id(int main_category_id) {
		this.main_category_id = main_category_id;
	}

	@Override
	public String toString() {
		return "SubCategoryDTO [id=" + id + ", user_id=" + user_id + ", name=" + name + ", category_order="
				+ category_order + ", main_category_id=" + main_category_id + "]";
	}

}
