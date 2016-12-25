package com.leelab.blogproject.comment.vo;

import java.sql.Timestamp;

public class CommentVO {
	private int id;
	private int post_id;
	private Timestamp create_at;
	private String user_id;
	private String nickname;
	private String content;
	
	public CommentVO(){}

	public CommentVO(int id, int post_id, Timestamp create_at, String user_id, String nickname, String content) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.create_at = create_at;
		this.user_id = user_id;
		this.nickname = nickname;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentVO [id=" + id + ", post_id=" + post_id + ", create_at=" + create_at + ", user_id=" + user_id
				+ ", nickname=" + nickname + ", content=" + content + "]";
	}

}
