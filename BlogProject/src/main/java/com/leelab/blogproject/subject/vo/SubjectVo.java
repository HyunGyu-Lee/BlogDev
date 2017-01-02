package com.leelab.blogproject.subject.vo;

public class SubjectVo {
	
	private int id;
	private String name;
	
	public SubjectVo() {}

	public SubjectVo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
