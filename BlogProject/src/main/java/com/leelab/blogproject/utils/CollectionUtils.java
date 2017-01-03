package com.leelab.blogproject.utils;

import java.lang.reflect.Field;
import java.util.HashMap;

public class CollectionUtils {

	public static HashMap<String, Object> generateHashMap(String[] key, Object[] value) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if(key.length!=value.length)return null;
		
		for(int i=0;i<key.length;i++)
		{
			map.put(key[i], value[i]);
		}
		
		return map;
	}

	public static HashMap<String, Object> generateBeanAsHashMap(Object bean) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		Field[] fields = bean.getClass().getDeclaredFields();
		
		for(Field field : fields)
		{
			field.setAccessible(true);
			try
			{
				map.put(field.getName(), field.get(bean));
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		
		return map;
	}

	public static <T> T[] array(T...t) {
		return t;
	}
	
	public static Object[] objectArray(Object...o) {
		return o;
	}

//	public static <K, V> void sort(HashMap<K, V> map, Comparator<MainCategoryDTO> comparator) {
//		TreeMap<K, V> sortedMap = new TreeMap<K, V>(map);
//		return sortedMap;
//	}
}
