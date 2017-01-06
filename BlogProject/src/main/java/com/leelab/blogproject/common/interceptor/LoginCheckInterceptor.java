package com.leelab.blogproject.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.leelab.blogproject.common.annotation.NotLoginCheck;
import com.leelab.blogproject.user.dto.UserDTO;
import com.leelab.blogproject.utils.ReflectionUtils;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {		
		HttpSession session = request.getSession(false);

		if(session==null||session.getAttribute("user")==null)
		{
			if(ReflectionUtils.isAnnotatedOn(handler, NotLoginCheck.class))
			{
				logger.info("{} -> Login Check Off", request.getRequestURI());
				return true;
			}

			StringBuilder sb = new StringBuilder();
			@SuppressWarnings("unchecked")
			Map<String, String[]> map = request.getParameterMap();
			sb.append("?");
			if(!map.isEmpty())
			{
				for(Map.Entry<String, String[]> entry : map.entrySet())
				{
					logger.info("{}", entry.getKey());
					sb.append(entry.getKey()).append("=").append(entry.getValue()[0]).append("AND");
				}
			}
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))
			{
				logger.info("Ajax Request Not Auth");
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println("{\"status\":-1}");
				response.getWriter().flush();
				response.getWriter().close();
			}
			else
			{	if(sb.length()!=1)
				{
					logger.info("{}", sb.toString());
					sb.delete(sb.length()-3, sb.length()-1);
					sb.deleteCharAt(sb.length()-1);
				}
				response.sendRedirect(request.getContextPath()+"/openLogin?requestUri="+request.getRequestURI()+sb.toString());
			}
			
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
