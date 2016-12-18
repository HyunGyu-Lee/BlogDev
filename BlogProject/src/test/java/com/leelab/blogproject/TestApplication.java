package com.leelab.blogproject;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.category.CategoryService;
import com.leelab.blogproject.category.main.MainCategoryDAO;
import com.leelab.blogproject.category.main.MainCategoryDTO;
import com.leelab.blogproject.category.sub.SubCategoryDAO;
import com.leelab.blogproject.category.sub.SubCategoryDTO;
import com.leelab.blogproject.mail.MailTemplate;
import com.leelab.blogproject.post.PostDAO;
import com.leelab.blogproject.post.PostDTO;
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
	
	private PostDAO postDao;
	
	@Before
	public void applicationSetup() {
		userDao = session.getMapper(UserDAO.class);
		mCateDao = session.getMapper(MainCategoryDAO.class);
		sCateDao = session.getMapper(SubCategoryDAO.class);
		postDao = session.getMapper(PostDAO.class);
	}
	
	@Test
	public void test() throws MessagingException {
		
		//PostDTO post = new PostDTO(0, "admin", "테스트제목", 16, 2, -1, "테스트값", null);
		//postDao.insert(post);
		
		PostDTO post = postDao.selectById(2);
		System.out.println(post);
	}
	
	
}
