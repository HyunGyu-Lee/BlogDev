package com.leelab.blogproject.common.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class CustomMultipartRequestResolver implements HandlerMethodArgumentResolver {

	public static final Logger logger = LoggerFactory.getLogger(CustomMultipartRequestResolver.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest arg2, WebDataBinderFactory arg3) throws Exception {
		HttpServletRequest request = (HttpServletRequest)arg2.getNativeRequest();
		MultipartRequest multipartRequest = new MultipartRequest((MultipartHttpServletRequest)request);
		Enumeration<String> paramNames =  request.getParameterNames();
		
		while(paramNames.hasMoreElements())
		{
			String key = paramNames.nextElement();
			multipartRequest.set(key, request.getParameter(key));
		}

		return multipartRequest;
	}

	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		if(!MultipartRequest.class.isAssignableFrom(arg0.getParameterType()))return false;
		
		logger.info("Detecting MultipartRequest on {}", arg0.getMethod().getName());
		return true;
	}

	
	
	
}
