package com.leelab.blogproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.notification.dao.NotificationDAO;
import com.leelab.blogproject.notification.vo.NotificationVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"classpath:config/spring/context-*.xml"}
)
public class TestContext {

	@Autowired
	private NotificationDAO dao;
	
	@Before
	public void setUp() {

	}
	
	@Test
	public void test() {
		NotificationVo nVo = new NotificationVo();
		nVo.setNotificator("admin");
		nVo.setMessage("테스투");
		nVo.setLink("/admin");
		dao.insertAll(nVo, new String[]{"gusrb0808","chijatree62"});
	}
	
}
