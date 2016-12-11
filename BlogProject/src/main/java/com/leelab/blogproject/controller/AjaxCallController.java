 package com.leelab.blogproject.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.leelab.blogproject.annotation.LoginRequired;
import com.leelab.blogproject.resolver.MultipartRequest;
import com.leelab.blogproject.user.UserDTO;
import com.leelab.blogproject.user.UserService;
import com.leelab.blogproject.utils.CollectionUtils;
import com.leelab.blogproject.utils.json.SimpleHashMap;

@RestController
@RequestMapping("/ajax")
public class AjaxCallController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxCallController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="duplicate_check", method=RequestMethod.POST)
	public HashMap<String, Object> duplicateCheck(@RequestParam String id) {
		boolean result = userService.duplicateUserCheck(id);

		logger.info("Duplicate check : {} -> {}", id, result);
		
		return CollectionUtils.generateHashMap(new String[]{"result"}, new Object[]{result});
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public HashMap<String, Object> register(MultipartRequest request) throws IllegalStateException, IOException, MessagingException {
		userService.registUser(request.get("id"),
							   request.get("password"),
							   request.get("nickname"),
							   request.get("email"),
							   request.get("phone"),
							   request.getFile(0));
		
		return CollectionUtils.generateHashMap(new String[]{"result"}, new Object[]{true});
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public HashMap<String, Object> login(@RequestParam String id,
										 @RequestParam String password,
										 HttpServletRequest request,HttpServletResponse response) {
		boolean result = userService.login(id, password);
		if(result)
		{
			request.getSession().setAttribute("user", userService.getUser(id));
		}
		
		logger.info("Login check {} - {} => {}", id, password, result);
		return CollectionUtils.generateHashMap(new String[]{"result"}, new Object[]{result});
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
		return CollectionUtils.generateHashMap(new String[]{"result"}, new Object[]{result});
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
	
	@RequestMapping(value="passwordAuth", method=RequestMethod.POST)
	public HashMap<String, Object> passwordAuth(@RequestParam String password, @SessionAttribute UserDTO user) {
		boolean result = userService.passwordAuth(user.getId(), password);

		return CollectionUtils.generateHashMap(CollectionUtils.array("result","password"),
											   CollectionUtils.objectArray(result,password));
	}
	
	//@LoginRequired
	@RequestMapping(value="modifyField", method=RequestMethod.POST)
	public void modifyField(UserDTO user, @SessionAttribute("user") UserDTO sessionUser) {
		user.setId(sessionUser.getId());
		userService.updateUser(user);
	}
	
}
