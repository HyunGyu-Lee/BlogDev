package com.leelab.blogproject;

import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.category.CategoryService;
import com.leelab.blogproject.category.main.MainCategoryDAO;
import com.leelab.blogproject.category.main.MainCategoryDTO;
import com.leelab.blogproject.category.sub.SubCategoryDAO;
import com.leelab.blogproject.category.sub.SubCategoryDTO;
import com.leelab.blogproject.mail.MailTemplate;
import com.leelab.blogproject.user.UserDAO;
import com.leelab.blogproject.user.UserDTO;
import com.leelab.blogproject.utils.CollectionUtils;
import com.leelab.blogproject.utils.json.SimpleHashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:config/spring/context-*.xml"
})
public class TestApplication {

	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private MailTemplate mail;

	CategoryService service;
	
	private UserDAO userDao;
	
	private MainCategoryDAO mCateDao;
	
	private SubCategoryDAO sCateDao;
	
	@Before
	public void applicationSetup() {
		userDao = session.getMapper(UserDAO.class);
		mCateDao = session.getMapper(MainCategoryDAO.class);
		sCateDao = session.getMapper(SubCategoryDAO.class);
	}
	
	@Test
	public void test() throws MessagingException {
		service = new CategoryService();
		service.setSession(session);
//		mCateDao.selectByUserId("admin");
//		
//		mCateDao.insert(new MainCategoryDTO(0, "admin", "µ¶¼­", 8));
		
		System.out.println(service.getUserCategory("admin"));
	}
	
	
}
