package com.leelab.blogproject.post.vo;

public class SearchVO {
	private int post_id;
	private String user_id;
	private int main_category_id;
	private int sub_category_id;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	@Override
	public String toString() {
		return "SearchVO [post_id=" + post_id + ", user_id=" + user_id + ", main_category_id=" + main_category_id
				+ ", sub_category_id=" + sub_category_id + "]";
	}
	
}
