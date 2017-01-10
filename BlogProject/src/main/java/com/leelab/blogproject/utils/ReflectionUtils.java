package com.leelab.blogproject.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

public class ReflectionUtils {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
	
	public static String[] getFieldNames(Class<?> clazz) {
		ArrayList<String> fieldNames = new ArrayList<String>();
		
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields)
		{
			fieldNames.add(field.getName());
		}
		
		return fieldNames.toArray(new String[fieldNames.size()]);
	}
	
	public static boolean isAnnotatedOn(Object object, Class<? extends Annotation> annotation) {
		if(object instanceof HandlerMethod) return isAnnotatedOn((HandlerMethod)object, annotation);
		
		return false;
	}
	
	public static boolean isAnnotatedOn(HandlerMethod method, Class<? extends Annotation> annotation) {
		if(method.getMethodAnnotation(annotation)==null)return false;
		return true;
	}

	public static Object getValue(String key, Object target) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = target.getClass().getDeclaredField(key);
		f.setAccessible(true);
		return f.get(target);
	}
}
