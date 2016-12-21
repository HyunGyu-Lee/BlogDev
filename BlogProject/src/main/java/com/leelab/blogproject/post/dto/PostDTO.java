package com.leelab.blogproject.post.dto;

import java.sql.Timestamp;

public class PostDTO {
	private int id;
	private String user_id;
	private String title;
	private int main_category_id;
	private int sub_category_id;
	private int hit;
	private String content;
	private Timestamp create_at; 
	
	public PostDTO(){}
	
	public PostDTO(int id, String user_id, String title, int main_category_id, int sub_category_id, int hit,
		String content, Timestamp create_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.main_category_id = main_category_id;
		this.sub_category_id = sub_category_id;
		this.hit = hit;
		this.content = content;
		this.create_at = create_at;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", user_id=" + user_id + ", title=" + title + ", main_category_id="
				+ main_category_id + ", sub_category_id=" + sub_category_id + ", hit=" + hit + ", content=" + content
				+ ", create_at=" + create_at + "]";
	}
	
}
