package com.leelab.blogproject.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionUtils {

	public static String[] getFieldNames(Class<?> clazz) {
		ArrayList<String> fieldNames = new ArrayList<String>();
		
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields)
		{
			fieldNames.add(field.getName());
		}
		
		return fieldNames.toArray(new String[fieldNames.size()]);
	}
	
}
