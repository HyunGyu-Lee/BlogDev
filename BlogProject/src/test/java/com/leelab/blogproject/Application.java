package com.leelab.blogproject;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.utils.prettylogger.PrettyLogger;
import com.leelab.blogproject.utils.prettylogger.PrettyLoggerFactory;
import com.leelab.blogproject.visithistory.dao.VisitHistoryDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:/config/spring/*.xml"
})
public class Application {

	@Autowired
	private VisitHistoryDAO vhDao;

	PrettyLogger logger = PrettyLoggerFactory.createInstance(Application.class);
	
	List<String> list;
	
	@Before
	public void setUp() {
		list = new ArrayList<String>();
		list.add("김뚝aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa딱");
		list.add("이뚝딱");
		list.add("현뚝딱");
		list.add("규뚝딱");
		list.add("카뚝딱");
	}
	
	@Test
	public void a() {
		logger.info("깔삼한 로그 테스트입니다!");
		
		String listable = logger.list("사용aaaaaaaaaaaaaaaaaasdasd자목록",list);
		/* 
		 * 원래 목적은 logger.list하면 색 입혀진 로그 출력되는게 목적이었는데
		 * 자바 프록시가 내부 호출에 대해서는 메소드 인터셉팅이 안벌어진다.
		 * */
		String form = String.format("%-20s값", "\t");
		logger.info(listable);
	}
	
}
