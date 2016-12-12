package com.leelab.blogproject.post;

public class PostDTO {
	
	private int id;
	private String user_id;
	private String title;
	private String content;
	private int main_category_id;
	private int sub_category_id;
	
	public PostDTO(){}

	public PostDTO(int id, String user_id, String title, String content, int main_category_id, int sub_category_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.main_category_id = main_category_id;
		this.sub_category_id = sub_category_id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMain_category_id() {
		return main_category_id;
	}

	public void setMain_category_id(int main_category_id) {
		this.main_category_id = main_category_id;
	}

	public int getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(int sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", user_id=" + user_id + ", title=" + title + ", content=" + content
				+ ", main_category_id=" + main_category_id + ", sub_category_id=" + sub_category_id + "]";
	}
	
}
