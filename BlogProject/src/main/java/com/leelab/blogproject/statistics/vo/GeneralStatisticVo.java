package com.leelab.blogproject.statistics.vo;

import java.sql.Timestamp;

public class GeneralStatisticVo {

	private Timestamp day;
	private int count;
	private String user_id;
	
	private Timestamp startDate;
	private Timestamp endDate;
	
	private String gap; // day, week, month
	
	public Timestamp getDay() {
		return day;
	}
	public void setDay(Timestamp day) {
		this.day = day;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getGap() {
		return gap;
	}
	public void setGap(String gap) {
		this.gap = gap;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "GeneralStatisticVo [day=" + day + ", count=" + count + ", user_id=" + user_id + ", startDate="
				+ startDate + ", endDate=" + endDate + ", gap=" + gap + "]";
	}
	
}
