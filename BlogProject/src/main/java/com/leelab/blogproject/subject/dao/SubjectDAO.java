package com.leelab.blogproject.subject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leelab.blogproject.subject.vo.SubjectVo;

@Repository
public interface SubjectDAO {
	
	List<SubjectVo> selectAll();
	
	/* 추후에 관리자 페이지 개발 시작할 때 update, delete, insert 추가할것 */
	
}
