package com.leelab.blogproject.utils.page;

public class PageVo {

	/*
	 * 총 레코드 갯수
	 * 페이지 당 레코드 갯수
	 * 페이지 그룹 크기
	 * 현재 페이지
	 * 현재 페이지에 시작 레코드 번호
	 * 현재 페이지의 마지막 레코드 번호
	 * 총 페이지 갯수
	 * 현재 페이지의 소속 그룹번호
	 * 현재 그룹의 시작 페이지 번호
	 * 현재 그룹의 마지막 페이지 번호
	 * 이전 페이지 번호
	 * 다음 페이지 번호
	 * */
	
	private int totalRecord;
	private int pageSize;
	private int groupSize;
	
	private int currentPage;
	private int firstRecord;
	private int lastRecord;
	private int totalPage;
	
	private int groupNo;
	private int firstPage;
	private int lastPage;
	
	private int prevPage;
	private int nextPage;

	public PageVo(){}

	public PageVo(int totalRecord, int pageSize, int groupSize, int currentPage, int firstRecord, int lastRecord,
			int totalPage, int groupNo, int firstPage, int lastPage, int prevPage, int nextPage) {
		super();
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.groupSize = groupSize;
		this.currentPage = currentPage;
		this.firstRecord = firstRecord;
		this.lastRecord = lastRecord;
		this.totalPage = totalPage;
		this.groupNo = groupNo;
		this.firstPage = firstPage;
		this.lastPage = lastPage;
		this.prevPage = prevPage;
		this.nextPage = nextPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public int getFirstRecord() {
		return firstRecord;
	}

	public void setFirstRecord(int firstRecord) {
		this.firstRecord = firstRecord;
	}

	public int getLastRecord() {
		return lastRecord;
	}

	public void setLastRecord(int lastRecord) {
		this.lastRecord = lastRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
}
