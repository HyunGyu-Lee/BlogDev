package com.leelab.blogproject.subject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leelab.blogproject.subject.vo.SubjectVo;

@Repository
public interface SubjectDAO {
	
	List<SubjectVo> selectAll();
	
	/* ���Ŀ� ������ ������ ���� ������ �� update, delete, insert �߰��Ұ� */
	
}
