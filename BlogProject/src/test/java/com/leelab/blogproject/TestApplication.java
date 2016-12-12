package com.leelab.blogproject;

import java.util.ArrayList;
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
	private SqlSession session;
	
	@Autowired
	private MailTemplate mail;
	
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
		//mail.send("gusrb0808@naver.com", "Blog 회원가입 인증 메일입니다.", "<h1>Blog에서 보내드립니다.</h1>인증코드는 이현규짱 입니다.");
		
//		UserDTO user = userDao.selectUser("admin");
//		
//		System.out.println(CollectionUtils.generateBeanAsHashMap(user));

		MainCategoryDTO newCategory = new MainCategoryDTO();
		newCategory.setUser_id("admin");
		newCategory.setName("사전");
		
		//mCateDao.insert(newCategory);
		
		SubCategoryDTO sub = new SubCategoryDTO(0, "admin", "영어사전", 16);
		SubCategoryDTO sub2 = new SubCategoryDTO(0, "admin", "국어사전", 16);

		sCateDao.insert(sub);
		sCateDao.insert(sub2);
		
		ArrayList<MainCategoryDTO> categories = mCateDao.selectByUserId("admin");
		ArrayList<SubCategoryDTO> subCate = sCateDao.selectByUserId("admin");

		HashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>> categoryBundle = new HashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>>();
		
		for(MainCategoryDTO category : categories)
		{
			ArrayList<SubCategoryDTO> sc = new ArrayList<SubCategoryDTO>();
			
			for(SubCategoryDTO subC : subCate)
			{
				if(subC.getMain_category_id()==category.getId())
				{
					sc.add(subC);
				}
			}
			categoryBundle.put(category, sc);
		}
		
		System.out.println(categoryBundle);
		
		
		
	}
	
	
}
