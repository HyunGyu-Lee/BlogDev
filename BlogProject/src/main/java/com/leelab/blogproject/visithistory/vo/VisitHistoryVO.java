package com.leelab.blogproject.visithistory.vo;

import java.util.Date;

public class VisitHistoryVO {

	private int id;
	private String blog_id;
	private String visitor_id;
	private Date visited_at;
	
	private Date start;
	private Date end;

	private int unique_visitor_count;
	
	public VisitHistoryVO(){}
	
	public VisitHistoryVO(int id, String blog_id, String visitor_id, Date visited_at) {
		super();
		this.id = id;
		this.blog_id = blog_id;
		this.visitor_id = visitor_id;
		this.visited_at = visited_at;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public String getVisitor_id() {
		return visitor_id;
	}
	public void setVisitor_id(String visitor_id) {
		this.visitor_id = visitor_id;
	}
	public Date getVisited_at() {
		return visited_at;
	}
	public void setVisited_at(Date visited_at) {
		this.visited_at = visited_at;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getUnique_visitor_count() {
		return unique_visitor_count;
	}

	public void setUnique_visitor_count(int unique_visitor_count) {
		this.unique_visitor_count = unique_visitor_count;
	}

	@Override
	public String toString() {
		return "VisitHistoryVO [id=" + id + ", blog_id=" + blog_id + ", visitor_id=" + visitor_id + ", visited_at="
				+ visited_at + ", start=" + start + ", end=" + end + ", unique_visitor_count=" + unique_visitor_count
				+ "]";
	}
}
