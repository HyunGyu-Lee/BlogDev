package com.leelab.blogproject.utils;

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

}
