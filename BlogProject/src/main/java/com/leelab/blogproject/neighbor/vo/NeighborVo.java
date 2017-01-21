package com.leelab.blogproject.neighbor.vo;

import java.sql.Timestamp;

public class NeighborVo {
	/**
	 * rel_state 1 => user가 rel_user한테 신청상태
	 * rel_state 2 => rel_user가 user의 이웃요청을 거절한 상태
	 * rel_state 3 => rel_user가 user의 이웃요청을 수락한 상태
	 * */
	public static final int STATE_WAIT = 1;
	public static final int STATE_DENY = 2;	
	public static final int STATE_ACCEPT = 3;
	
	private String user_id;
	private String rel_user_id;
	private Timestamp rel_create_at;
	private int rel_state;
	private String apply_msg;
	private String rel_user_nickname;
	private String tag;
	
	public NeighborVo() {}
	
	public NeighborVo(String user_id, String rel_user_id, Timestamp rel_create_at, int rel_state, String apply_msg) {
		super();
		this.user_id = user_id;
		this.rel_user_id = rel_user_id;
		this.rel_create_at = rel_create_at;
		this.rel_state = rel_state;
		this.apply_msg = apply_msg;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRel_user_id() {
		return rel_user_id;
	}

	public void setRel_user_id(String rel_user_id) {
		this.rel_user_id = rel_user_id;
	}

	public Timestamp getRel_create_at() {
		return rel_create_at;
	}

	public void setRel_create_at(Timestamp rel_create_at) {
		this.rel_create_at = rel_create_at;
	}

	public int getRel_state() {
		return rel_state;
	}

	public void setRel_state(int rel_state) {
		this.rel_state = rel_state;
	}

	public String getApply_msg() {
		return apply_msg;
	}

	public void setApply_msg(String apply_msg) {
		this.apply_msg = apply_msg;
	}

	public String getRel_user_nickname() {
		return rel_user_nickname;
	}

	public void setRel_user_nickname(String rel_user_nickname) {
		this.rel_user_nickname = rel_user_nickname;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "NeighborVo [user_id=" + user_id + ", rel_user_id=" + rel_user_id + ", rel_create_at=" + rel_create_at
				+ ", rel_state=" + rel_state + ", apply_msg=" + apply_msg + ", rel_user_nickname=" + rel_user_nickname
				+ ", tag=" + tag + "]";
	}


}

