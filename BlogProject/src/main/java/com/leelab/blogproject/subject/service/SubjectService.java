package com.leelab.blogproject.subject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.subject.dao.SubjectDAO;
import com.leelab.blogproject.subject.vo.SubjectVo;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectDAO subjectDao;
	
	public List<SubjectVo> getSubjects() {
		return subjectDao.selectAll();
	}
	
}
