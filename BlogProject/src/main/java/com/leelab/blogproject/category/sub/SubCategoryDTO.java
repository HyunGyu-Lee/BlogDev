package com.leelab.blogproject.category.sub;

public class SubCategoryDTO {
	
	private int id;
	private String user_id;
	private String name;
	private int main_category_id;
	
	public SubCategoryDTO(){}

	public SubCategoryDTO(int id, String user_id, String name, int main_category_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.name = name;
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

	public int getMain_category_id() {
		return main_category_id;
	}

	public void setMain_category_id(int main_category_id) {
		this.main_category_id = main_category_id;
	}

	@Override
	public String toString() {
		return "SubCategoryDTO [id=" + id + ", user_id=" + user_id + ", name=" + name + ", main_category_id="
				+ main_category_id + "]";
	}
	
}
