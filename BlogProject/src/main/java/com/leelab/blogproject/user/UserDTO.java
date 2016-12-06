package com.leelab.blogproject.user;

import java.util.Date;

public class UserDTO {

	private String id;
	private String password;
	private String phone;
	private String email;
	private String auth;
	private String auth_key;
	private String nickname;
	private String profile_url;
	private Date create_at;
	
	public UserDTO(){}
	
	public UserDTO(String id, String password, String phone, String email, String auth, String auth_key, String nickname, String profile_url, Date create_at) {
		super();
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.auth = auth;
		this.auth_key = auth_key;
		this.nickname = nickname;
		this.profile_url = profile_url;
		this.create_at = create_at;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getAuth_key() {
		return auth_key;
	}
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile_url() {
		return profile_url;
	}
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", password=" + password + ", phone=" + phone + ", email=" + email + ", auth="
				+ auth + ", auth_key=" + auth_key + ", nickname=" + nickname + ", profile_url=" + profile_url
				+ ", create_at=" + create_at + "]";
	}
}
