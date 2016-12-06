package com.leelab.blogproject.resolver;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class MultipartRequest {

	private MultipartHttpServletRequest multipartHttpServletRequest;
	private HashMap<String, String> map = new HashMap<String, String>();
	
	public MultipartRequest(MultipartHttpServletRequest request) {
		this.multipartHttpServletRequest = request;
	}

	public MultipartHttpServletRequest getMultipartHttpServletRequest() {
		return multipartHttpServletRequest;
	}

	public void setMultipartHttpServletRequest(MultipartHttpServletRequest multipartHttpServletRequest) {
		this.multipartHttpServletRequest = multipartHttpServletRequest;
	}
	
	public MultipartFile getFile(int i) {
		String filaName = multipartHttpServletRequest.getFileNames().next();
		return multipartHttpServletRequest.getFile(filaName);
	}
	
	public void set(String key, String value) {
		map.put(key, value);
	}
	
	public String get(String key) {
		return map.get(key);
	}
}
