package com.leelab.blogproject.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.ModelAndView;

public class DeviceHandler extends DeviceResolverHandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(DeviceHandler.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))||modelAndView==null)
		{

		}
		else
		{
			Device device = DeviceUtils.getCurrentDevice(request);
			if(device.isMobile())
			{
				modelAndView.addObject("device", "mobile");
				if(modelAndView.getViewName().contains("blog/")||
				   modelAndView.getViewName().contains("register/")||
				   modelAndView.getViewName().contains("home/"))
				{
					modelAndView.setViewName("mobile/"+modelAndView.getViewName());
				}
			}
		}

		super.postHandle(request, response, handler, modelAndView);
	}
	
}
