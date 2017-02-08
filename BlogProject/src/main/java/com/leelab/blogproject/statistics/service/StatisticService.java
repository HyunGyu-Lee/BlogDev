package com.leelab.blogproject.statistics.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.statistics.vo.GeneralStatisticVo;
import com.leelab.blogproject.utils.DateUtils;
import com.leelab.blogproject.visithistory.dao.VisitHistoryDAO;

@Service
public class StatisticService {

	@Autowired
	private VisitHistoryDAO vhDao;

	public List<GeneralStatisticVo> getVisitCount(GeneralStatisticVo vo) {
		Timestamp start = vo.getStartDate();//DateUtils.format(vo.getStartDate(), "yyyy-MM-dd");
		
		List<GeneralStatisticVo> result = vhDao.getVisitStat(vo);
		
		//for(long i=0;) 
		
		
		return result;
	}
	
}
