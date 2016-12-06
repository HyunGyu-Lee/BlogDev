package com.leelab.blogproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.leelab.blogproject.user.UserDTO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {		
		HttpSession session = request.getSession(false);
		
		if(session==null)
		{
			logger.info("Login require.");
			response.sendRedirect("openLogin");
			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		UserDTO user = (UserDTO)request.getSession().getAttribute("user");
		
		if(user!=null)
		{
			if(user.getAuth().equals("false"))
			{
				modelAndView.setViewName("auth");
				logger.info("Auth Require");
				super.postHandle(request, response, handler, modelAndView);
				return;
			}
		}
	}

}
