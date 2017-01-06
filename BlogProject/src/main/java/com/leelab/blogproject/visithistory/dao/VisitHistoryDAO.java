package com.leelab.blogproject.visithistory.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leelab.blogproject.visithistory.vo.VisitHistoryVO;
import com.leelab.blogproject.visithistory.vo.VisitorCountVO;

@Repository
public interface VisitHistoryDAO {

	void insert(VisitHistoryVO history);
	
	List<VisitHistoryVO> select(VisitHistoryVO vo);

	VisitorCountVO selectCount(VisitHistoryVO vo);
}
