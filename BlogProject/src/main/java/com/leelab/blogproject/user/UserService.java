package com.leelab.blogproject.user;

import java.io.IOException;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leelab.blogproject.mail.HTMLGenerator;
import com.leelab.blogproject.mail.MailTemplate;
import com.leelab.blogproject.utils.CollectionUtils;
import com.leelab.blogproject.utils.FileUtils;
import com.leelab.blogproject.utils.StringUtils;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private SqlSession session;	
	private UserDAO userDao;

	@Autowired
	private MailTemplate mail;
	
	@Autowired
	public void setSqlSession(SqlSession session) {
		this.session = session;
		userDao = this.session.getMapper(UserDAO.class);
	}
	
	public UserDTO getUser(String id) {
		return userDao.selectUser(id);
	}
	
	/* ID �ߺ� �˻� */
	public boolean duplicateUserCheck(String id) {
		UserDTO user = getUser(id);
		
		if(user==null)return true;
		
		return false;
	}
	
	/* ȸ������  */
	public void registUser(String id,
						   String password,
						   String nickname,
						   String email,
						   String phone,
						   MultipartFile saveFile) throws IllegalStateException, IOException, MessagingException {
		
		String originalFileExtension = FileUtils.getFileExtension(saveFile.getOriginalFilename());
		String profile_url = StringUtils.getRandomString()+originalFileExtension;
		
		UserDTO user = new UserDTO();
		user.setId(id);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setPhone(phone);
		user.setProfile_url(profile_url);
		user.setAuth("false");
		user.setAuth_key(StringUtils.getRandomString());
		
		logger.info("Regist new user => {}",user);
		userDao.add(user);
		
		FileUtils.save(saveFile, FileUtils.PROFILE+profile_url);
		
		String html = "<h1>Blog���� �����帳�ϴ�.</h1><br/>"
					+ user.getNickname()+"��, ȸ������ ���ּż� �����մϴ�.<br/><br/>"
					+ "http://121.164.173.147:1234/blog �� �α��� �� �Ʒ� �����ڵ带 �Է����ּ���.<br/><br/><br/>"
					+ "#�����ڵ�<br/>"
					+ "<b>"+user.getAuth_key()+"</b>";
		
		mail.send(user.getEmail(), "Blog ȸ������ ���� �����Դϴ�.", html);
		logger.info("Send mail complete");
	}
	
	public boolean login(String id, String password) {
		UserDTO user = getUser(id);
		
		if(user==null)return false;
		
		if(user.getPassword().equals(password)) return true;

		return false;
	}
	
	

	public boolean auth(String id, String auth_key) {
		UserDTO user = getUser(id);
		
		if(user.getAuth_key().equals(auth_key)) 
		{
			user.setAuth("true");
			userDao.updateUser(user);
			return true;
		}
		
		return false;
	}

	public void reAuth(String id) throws MessagingException {
		String auth_key = StringUtils.getRandomString();
		UserDTO user = getUser(id);
		user.setAuth_key(auth_key);
		
		HTMLGenerator html = new HTMLGenerator();
		html.h1("Blog���� �����帳�ϴ�.").br()
		    .b(user.getNickname()).plain("��, ȸ������ �� �ּż� �����մϴ�.").br(2)
		    .plain("http://121.164.173.147:1234/blog �� �α��� �� �Ʒ� �����ڵ带 �Է����ּ���.").br(3)
		    .plain("#�����ڵ�").br()
		    .b(auth_key);
		
		mail.send(user.getEmail(), "Blog �̸��� ���� �����ڵ��Դϴ�.", html.generateHTML());
		
		userDao.updateUser(user);
	}
	
	public byte[] getProfileImage(String id) throws IOException {
		UserDTO user = getUser(id);
		return FileUtils.read(FileUtils.PROFILE+user.getProfile_url());
	}
	
	public HashMap<String, Object> getUserInfo(String id) {
		UserDTO dto = getUser(id);
		return CollectionUtils.generateBeanAsHashMap(dto);
	}

	public boolean passwordAuth(String id, String password) {
		UserDTO user = getUser(id);
		
		if(user.getPassword().equals(password))return true;
		
		return false;
	}
}
