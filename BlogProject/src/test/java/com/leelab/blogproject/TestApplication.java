package com.leelab.blogproject;

import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	private SqlSession session;
	
	@Autowired
	private MailTemplate mail;
	
	private UserDAO userDao;
	
	@Before
	public void applicationSetup() {
		userDao = session.getMapper(UserDAO.class);
	}
	
	@Test
	public void test() throws MessagingException {
		//mail.send("gusrb0808@naver.com", "Blog 회원가입 인증 메일입니다.", "<h1>Blog에서 보내드립니다.</h1>인증코드는 이현규짱 입니다.");
		
//		UserDTO user = userDao.selectUser("admin");
//		
//		System.out.println(CollectionUtils.generateBeanAsHashMap(user));

		UserDTO admin = new UserDTO();
		admin.setId("admin");
		admin.setNickname("관리자");
		userDao.update(admin);
		admin = userDao.selectUser("admin");
		System.out.println("변경 후 : "+admin);
		
	}
	
	
}
