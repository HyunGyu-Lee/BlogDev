package com.leelab.blogproject.utils.page;

public class PageUtil {
	
	public static PageVo getPageInfo(int totalRecord, int pageSize, int groupSize, int currentPage) {
		
		int lastRecord = currentPage * pageSize;		
		if(lastRecord > totalRecord) lastRecord = totalRecord;
		
		int firstRecord = lastRecord - (pageSize - 1);
		if(firstRecord < 0) firstRecord = 1;
		
		int totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
		if(currentPage > totalPage) currentPage = totalPage;
		
		int groupNo = currentPage / groupSize + (currentPage % groupSize == 0 ? 0 : 1);
		
		int lastPage = groupNo * groupSize;
		if(lastPage > totalPage) lastPage = totalPage;
		
		int firstPage = lastPage - (groupSize - 1);
		
		int prevPage = firstPage - groupSize;
		int nextPage = firstPage + groupSize;
		
		return new PageVo(totalRecord, pageSize, groupSize, currentPage, firstRecord, lastRecord, totalPage, groupNo, firstPage, lastPage, prevPage, nextPage);
	}
	
}
