package com.leelab.blogproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.visithistory.dao.VisitHistoryDAO;
import com.leelab.blogproject.visithistory.vo.VisitHistoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:config/spring/context-*.xml"
})
public class VisitHistoryTest {
	
	@Autowired
	private VisitHistoryDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(VisitHistoryTest.class);
	
	@Test
	public void test() throws ParseException {
		VisitHistoryVO vo = new VisitHistoryVO();
		vo.setBlog_id("admin");
		//vo.setVisitor_id("gusrb0808");
		//vo.setStart(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-01-05 00:00:00"));
		vo.setEnd(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-01-08 15:59:59"));
		
		for(VisitHistoryVO history : dao.select(vo))
		{
			logger.info("{}", history);
		}
		
		logger.info("{}", dao.selectCount(vo));
	}
	
}
