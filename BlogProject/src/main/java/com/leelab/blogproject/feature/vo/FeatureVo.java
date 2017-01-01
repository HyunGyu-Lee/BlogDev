package com.leelab.blogproject.feature.vo;

public class FeatureVo {
	private String user_id;
	private String title;
	private String description;
	private String bgimg;
	private String subject_name;
	
	public FeatureVo() {}

	public FeatureVo(String user_id, String title, String description, String bgimg, String subject_name) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.description = description;
		this.bgimg = bgimg;
		this.subject_name = subject_name;
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

	@Override
	public String toString() {
		return "FeatureVo [user_id=" + user_id + ", title=" + title + ", description=" + description + ", bgimg="
				+ bgimg + ", subject_name=" + subject_name + "]";
	}
	
}

