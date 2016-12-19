 package com.leelab.blogproject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.leelab.blogproject.category.CategoryService;
import com.leelab.blogproject.resolver.MultipartRequest;
import com.leelab.blogproject.user.UserDTO;
import com.leelab.blogproject.user.UserService;
import com.leelab.blogproject.utils.FileUtils;
import com.leelab.blogproject.utils.StringUtils;
import com.leelab.blogproject.utils.json.SimpleHashMap;

@RestController
@RequestMapping("/ajax")
public class AjaxCallController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxCallController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="duplicate_check", method=RequestMethod.POST)
	public HashMap<String, Object> duplicateCheck(@RequestParam String id) {
		boolean result = userService.duplicateUserCheck(id);

		logger.info("Duplicate check : {} -> {}", id, result);
		
		return SimpleHashMap.newInstance().put("result", result);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public HashMap<String, Object> register(MultipartRequest request) throws IllegalStateException, IOException, MessagingException {
		userService.registUser(request.get("id"),
							   request.get("password"),
							   request.get("nickname"),
							   request.get("email"),
							   request.get("phone"),
							   request.getFile(0));
		
		return SimpleHashMap.newInstance().put("result", true);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public HashMap<String, Object> login(@RequestParam Map<String, String> requestScope, HttpSession session) {
		String id = requestScope.get("id");
		String password = requestScope.get("password");
		String redirectUri = requestScope.get("redirectUri");
		logger.info(redirectUri);
		boolean result = userService.login(id, password);
		if(result)
		{
			session.setAttribute("user", userService.getUser(id));
		}
		
		logger.info("Login check {} - {} => {}", id, password, result);

		return SimpleHashMap.newInstance().put("result", result).put("redirectUri", redirectUri);
	}
	
	@RequestMapping("/auth")
	public HashMap<String, Object> auth(@RequestParam String auth_key, HttpSession session) {
		String id = ((UserDTO)session.getAttribute("user")).getId();
		boolean result = userService.auth(id, auth_key);
		if(result)
		{
			session.setAttribute("user", userService.getUser(id));
		}
		logger.info("Auth check {} - {}", auth_key, result);
		return SimpleHashMap.newInstance().put("result", result);
	}
	
	@RequestMapping("/reAuth")
	public void reAuth(@SessionAttribute UserDTO user) throws MessagingException {
		String id = user.getId();
		userService.reAuth(id);
		logger.info("New Auth_key generated on {}", id);
	}
	
	@RequestMapping("/profileImage/{id}")
	public ResponseEntity<byte[]> profileImage(@PathVariable String id) throws IOException {
		logger.info("Return profile image data");
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(userService.getProfileImage(id), header, HttpStatus.CREATED);
	}
	
	@RequestMapping("/temp/{filename:.+}")
	public ResponseEntity<byte[]> tempView(@PathVariable String filename) throws IOException {
		logger.info("Return Temp image data");
		logger.info(filename);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(FileUtils.read(FileUtils.TEMP+filename), header, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="passwordAuth", method=RequestMethod.POST)
	public HashMap<String, Object> passwordAuth(@RequestParam String password, @SessionAttribute UserDTO user) {
		boolean result = userService.passwordAuth(user.getId(), password);		
		return SimpleHashMap.newInstance().put("result", result).put("password", password);
	}
	
	@RequestMapping(value="modifyField", method=RequestMethod.POST)
	public void modifyField(UserDTO user, @SessionAttribute("user") UserDTO sessionUser) {
		user.setId(sessionUser.getId());
		userService.updateUser(user);
	}
	
	@RequestMapping(value="editProfilePhoto", method=RequestMethod.POST)
	public void editProfilePhoto(MultipartRequest multi,@SessionAttribute("user") UserDTO user) throws IllegalStateException, IOException {
		userService.editProfilePhoto(user.getId(), multi.getFile(0));
	}
	
	@RequestMapping(value="getCategory", method=RequestMethod.POST)
	public HashMap<String, Object> getCategory(@RequestParam("main_category_id") String main_category_id,
											   @RequestParam("sub_category_id") String sub_category_id) {
		if(Integer.parseInt(sub_category_id)==-999)
		{
			return SimpleHashMap.objectToMap(categoryService.getMainCategory(Integer.parseInt(main_category_id)));
		}
		else
		{
			return SimpleHashMap.objectToMap(categoryService.getSubCategory(Integer.parseInt(sub_category_id)));
		}
	}
	
	@RequestMapping(value="send", method=RequestMethod.POST)
	public HashMap<String, Object> f(MultipartRequest multi) throws IllegalStateException, IOException {
		MultipartFile file = multi.getFile(0);
		
		String saveURL = StringUtils.getRandomString()+FileUtils.getFileExtension(file.getOriginalFilename());
		String savePath = FileUtils.TEMP + saveURL;
		
		FileUtils.save(file, savePath);
		
		return SimpleHashMap.newInstance().put("save_url", saveURL);
	}
}
