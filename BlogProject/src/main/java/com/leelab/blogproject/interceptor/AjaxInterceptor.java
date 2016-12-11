package com.leelab.blogproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.leelab.blogproject.annotation.LoginRequired;
import com.leelab.blogproject.utils.ReflectionUtils;

public class AjaxInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("=============== Ajax Request Map ==================");
		logger.info("Request URL : {}", request.getRequestURL());
		
//		if(ReflectionUtils.isAnnotatedOn(handler, LoginRequired.class)==true && request.getSession().getAttribute("user")==null) {
//			logger.info("Login Required");
//			response.setContentType("application/json");
//			response.getWriter().println("{\"code\":-1}");
//			return false;
//		}

		for(Object key : request.getParameterMap().keySet())
		{
			String value = request.getParameter(key.toString());
			logger.info("{} : {}", key, value);
		}
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		logger.info("=============== End Ajax Request ==================");
	}
	
}
