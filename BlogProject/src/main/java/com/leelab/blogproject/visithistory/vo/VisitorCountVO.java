package com.leelab.blogproject.visithistory.vo;

public class VisitorCountVO {

	private int unique_today_visitor;
	private int unique_total_visitor;	
	private int today_visitor;
	private int total_visitor;
	
	public VisitorCountVO(){}
	
	public int getUnique_today_visitor() {
		return unique_today_visitor;
	}
	public void setUnique_today_visitor(int unique_today_visitor) {
		this.unique_today_visitor = unique_today_visitor;
	}
	public int getUnique_total_visitor() {
		return unique_total_visitor;
	}
	public void setUnique_total_visitor(int unique_total_visitor) {
		this.unique_total_visitor = unique_total_visitor;
	}
	public int getToday_visitor() {
		return today_visitor;
	}
	public void setToday_visitor(int today_visitor) {
		this.today_visitor = today_visitor;
	}
	public int getTotal_visitor() {
		return total_visitor;
	}
	public void setTotal_visitor(int total_visitor) {
		this.total_visitor = total_visitor;
	}

	@Override
	public String toString() {
		return "VisitorCountVO [unique_today_visitor=" + unique_today_visitor + ", unique_total_visitor="
				+ unique_total_visitor + ", today_visitor=" + today_visitor + ", total_visitor=" + total_visitor + "]";
	}
}
