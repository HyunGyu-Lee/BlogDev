package com.leelab.blogproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.leelab.blogproject.user.UserDTO;

public class NormalRequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(NormalRequestInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		logger.info("{}", session);
		if(session.getAttribute("user")==null)
		{
			logger.info("Login require.");
			response.sendRedirect("openLogin");
			return false;
		}
		
		logger.info("========================================================== START REQUEST ==========================================================");
		logger.info("Request URL : {}", request.getRequestURL());
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView.getViewName().contains("redirect")) {
			logger.info("=========================================================== REDIRECT ===========================================================");
			super.postHandle(request, response, handler, modelAndView);
			return;
		}
		Device device = DeviceUtils.getCurrentDevice(request);
		String deviceHandle;
		if(device.isMobile())
			deviceHandle = "mobile/";
		else
			deviceHandle = "desktop/";
		
		UserDTO user = (UserDTO)request.getSession().getAttribute("user");
		
		if(user!=null)
		{
			if(user.getAuth().equals("false"))
			{
				modelAndView.setViewName(deviceHandle+"auth");
				logger.info("View name : {}",modelAndView.getViewName());
				logger.info("=========================================================== END REQUEST ===========================================================");
				super.postHandle(request, response, handler, modelAndView);
				return;
			}
		}
		
		modelAndView.setViewName(deviceHandle+modelAndView.getViewName());

		super.postHandle(request, response, handler, modelAndView);
		logger.info("View name : {}",modelAndView.getViewName());
		logger.info("=========================================================== END REQUEST ===========================================================");
	}
	
}
