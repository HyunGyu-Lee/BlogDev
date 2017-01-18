package com.leelab.blogproject.utils.json;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class JsonUtils {

	public enum ObjectType {
		STRING,
		NONE_STRING,
		ARRAY,
		MAP,
		NULL,
		OBJECT
	}
	
	private static final Class<?>[] NONE_STRING_SCOPE = {
										Integer.class, int.class,
										Double.class, double.class,
										Float.class, float.class,
										Byte.class, byte.class,
										Boolean.class, boolean.class
									};
	
	public static String toJsonString(Object object) {
		JsonUtils util = new JsonUtils();
		try
		{
			return util.json(object);
		}
		catch (IllegalAccessException e)
		{
			return null;
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String json(Object object) throws IllegalArgumentException, IllegalAccessException {
		ObjectType type = resolveType(object);
		
		StringBuilder jsb = new StringBuilder();
		
		if(type==ObjectType.OBJECT)
		{
			jsb.append("{");
			Class<?> objectType = object.getClass();
			
			Field[] fields = objectType.getDeclaredFields();
			for(Field field : fields)
			{
				if(Modifier.isStatic(field.getModifiers())) continue;
				field.setAccessible(true);
				jsb.append("\"").append(field.getName()).append("\"")	// key 제네레이트
				   .append(":")											
				   .append(json(field.get(object)))						// 재귀호출로 얻은 해석된 value 문자열 제네레이트
				   .append(",");
			}
			
			if(jsb.charAt(jsb.length()-1)==',') jsb.deleteCharAt(jsb.length()-1);
			
			jsb.append("}");
		}
		else if(type==ObjectType.STRING)
		{
			jsb.append("\"").append(object).append("\"").append(",");				
		}
		else if(type==ObjectType.NONE_STRING)
		{
			jsb.append(object).append(",");
		}
		else if(type==ObjectType.ARRAY)
		{
			jsb.append("[");
			Object[] objArray = (Object[])object;
			for(Object obj : objArray)
			{
				jsb.append(json(obj)).append(",");
			}
			
			if(jsb.charAt(jsb.length()-1)==',') jsb.deleteCharAt(jsb.length()-1);
			
			jsb.append("]");
		}
		else if(type==ObjectType.MAP)
		{
			jsb.append("{");
			Map objMap = (Map)object;
			Set<Entry> entries = objMap.entrySet();
			for(Entry entry : entries)
			{
				jsb.append("\"").append(entry.getKey()).append("\"") // key 제네레이트
				   .append(":")
				   .append(json(entry.getValue())); // 재귀호출로 얻은 해석된 value 문자열 제네레이트
			}
			jsb.append("}");
		}
		else if(type==ObjectType.NULL)
		{
			jsb.append(object).append(",");
		}
		
		if(jsb.charAt(jsb.length()-1)==',') jsb.deleteCharAt(jsb.length()-1);

		return jsb.toString();
	}
	
	public ObjectType resolveType(Object object) {
		if(object == null) return ObjectType.NULL;
		
		ObjectType type = ObjectType.OBJECT;
		
		Class<?> clazz = object.getClass();
		
		if(object instanceof String || object instanceof Character) return ObjectType.STRING;
		
		if(clazz.isArray() || object instanceof List) return ObjectType.ARRAY;
		
		if(object instanceof Map) return ObjectType.MAP;
		
		for(Class<?> scope : NONE_STRING_SCOPE)
		{
			if(clazz.equals(scope))
			{
				return ObjectType.NONE_STRING;
			}
		}
		
		return type;
	}
	
}
