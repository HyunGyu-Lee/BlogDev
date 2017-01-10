package com.leelab.blogproject.feature.vo;

import java.sql.Timestamp;

public class FeatureVo {
	private String user_id;
	private String title;
	private String description;
	private String bgimg;
	private String subject_name;
	private String nickname;
	private String post;
	private int subject_id;
	private String search_by;
	private String keyword;
	private String content;
	private String post_title;
	private int post_id;
	private Timestamp create_at;
	
	public FeatureVo() {}

	public FeatureVo(String user_id, String title, String description, String bgimg, String subject_name, int subject_id) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.description = description;
		this.bgimg = bgimg;
		this.subject_name = subject_name;
		this.subject_id = subject_id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBgimg() {
		return bgimg;
	}

	public void setBgimg(String bgimg) {
		this.bgimg = bgimg;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSearch_by() {
		return search_by;
	}

	public void setSearch_by(String search_by) {
		this.search_by = search_by;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public Timestamp getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}

	@Override
	public String toString() {
		return "FeatureVo [user_id=" + user_id + ", title=" + title + ", description=" + description + ", bgimg="
				+ bgimg + ", subject_name=" + subject_name + ", nickname=" + nickname + ", post=" + post
				+ ", subject_id=" + subject_id + ", search_by=" + search_by + ", keyword=" + keyword + "]";
	}


	
}

