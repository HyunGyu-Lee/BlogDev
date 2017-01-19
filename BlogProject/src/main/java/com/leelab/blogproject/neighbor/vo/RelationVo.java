package com.leelab.blogproject.neighbor.vo;

import java.util.List;

public class RelationVo {

	private String me;
	private List<String> others;
	
	public RelationVo(){}
	
	public RelationVo(String me, List<String> others) {
		super();
		this.me = me;
		this.others = others;
	}
	
	public String getMe() {
		return me;
	}
	public void setMe(String me) {
		this.me = me;
	}
	public List<String> getOthers() {
		return others;
	}
	public void setOthers(List<String> others) {
		this.others = others;
	}
	
}
