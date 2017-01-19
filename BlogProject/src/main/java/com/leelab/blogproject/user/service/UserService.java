package com.leelab.blogproject.user.service;

import java.io.IOException;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.leelab.blogproject.feature.dao.FeatureDAO;
import com.leelab.blogproject.feature.vo.FeatureVo;
import com.leelab.blogproject.user.dao.UserDAO;
import com.leelab.blogproject.user.dto.UserDTO;
import com.leelab.blogproject.utils.CollectionUtils;
import com.leelab.blogproject.utils.FileUtils;
import com.leelab.blogproject.utils.StringUtils;
import com.leelab.blogproject.utils.mail.HTMLGenerator;
import com.leelab.blogproject.utils.mail.MailTemplate;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDAO userDao;

	@Autowired
	private FeatureDAO featureDao;
	
	@Autowired
	private MailTemplate mail;
	
	public UserDTO getUser(String id) {
		return userDao.selectUser(id);
	}
	
	/* ID 중복검사 */
	public boolean duplicateUserCheck(String id) {
		UserDTO user = getUser(id);
		
		if(user==null)return true;
		
		return false;
	}
	
	/* 회원가입  */
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
		/* Default Feature 삽입 */
		featureDao.insert(new FeatureVo(id, nickname+"의 블로그", nickname+"의 블로그입니다.", null, "", 1));
		String ctxPath = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getContextPath();
		String html = "<h1>Public Blog에 오신걸 환영합니다.</h1><br/>"
					+ user.getNickname()+"님, 가입을 환영합니다.<br/><br/>"
					+ "<a href=\"http://publicblog.xyz"+ctxPath+"/"+user.getId()+"\">로그인</a> 후 아래 인증코드를 입력해주세요. <br/><br/><br/>"
					+ "# 인증코드<br/>"
					+ "<b>"+user.getAuth_key()+"</b>";
		
		mail.send("Public Blog 가입 인증 메일", html, new String[]{user.getEmail()});
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
			userDao.update(user);
			return true;
		}
		
		return false;
	}

	public void reAuth(String id) throws MessagingException {
		String auth_key = StringUtils.getRandomString();
		UserDTO user = getUser(id);
		user.setAuth_key(auth_key);
		String ctxPath = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getContextPath();
		HTMLGenerator html = new HTMLGenerator();
		html.h1("Public Blog에서 보내드립니다.").br()
		    .b(user.getNickname()).plain("님이 요청하신 인증코드입니다.").br(2)
		    .plain("<a href=\"http://publicblog.xyz"+ctxPath+"/"+user.getId()+"\">로그인</a> 후 아래 인증코드를 입력해주세요.").br(3)
		    .plain("# 인증코드").br()
		    .b(auth_key);
		
		mail.send("Public Blog 가입 인증 코드", html.generateHTML(), new String[]{user.getEmail()});
		
		userDao.update(user);
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

	public void updateUser(UserDTO user) {
		userDao.update(user);
	}

	public void editProfilePhoto(String id, MultipartFile file) throws IllegalStateException, IOException {
		String extension = FileUtils.getFileExtension(file.getOriginalFilename());
		String saveFileName = StringUtils.getRandomString()+extension;

		UserDTO user = getUser(id);
		user.setProfile_url(saveFileName);
		
		userDao.update(user);
		
		FileUtils.save(file, FileUtils.PROFILE+saveFileName);		
	}

	public void registSocialUser(UserDTO user) {
		user.setPassword("");
		user.setEmail("");
		user.setPhone("");
		user.setAuth_key("");
		userDao.add(user);
		featureDao.insert(new FeatureVo(user.getId(), user.getNickname()+"의 블로그", user.getNickname()+"의 블로그입니다.", null, "", 1));
	}
}
