package com.leelab.blogproject.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.leelab.blogproject.notification.service.NotificationService;

@Aspect
public class Notificatior implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(Notificatior.class);

	@Autowired
	private NotificationService service;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("주입확인 {}", service);
	}
	
	//@Pointcut("execution(* com.leelab.blogproject..*.*(..))")
	//public void test(){};
	
	//@Pointcut("@annotation(com.leelab.blogproject.common.annotation.Notificational)")
	public void notification(){};
	
	//@Around("notification()")
	public Object notificate(ProceedingJoinPoint jp) throws Throwable { 
		Object returnVal = null;
		
		logger.info("{} 실행 전", jp.getSignature());

		returnVal = jp.proceed();
		Object[] args = jp.getArgs();
		
		for(Object arg : args)
		{
			logger.info("{}", arg);
		}
		
		logger.info("{} 실행 후", jp.getSignature());
		
		return returnVal;
	}
}
