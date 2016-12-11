package com.leelab.blogproject.utils.json;

import java.util.HashMap;
import java.util.Set;

public class SimpleHashMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	private static final String DELEMETER = "\"";
	
	public SimpleHashMap put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public static SimpleHashMap newInstance() {
		return new SimpleHashMap();
	}
	
	@Deprecated
	public String toJsonString() {
		Set<String> set = this.keySet();
		
		StringBuilder json = new StringBuilder();
		json.append("{");
		
		for(String key : set)
		{
			json.append(DELEMETER).append(key).append(DELEMETER).append(":");
			
			Object value = this.get(key);
			
			if(value instanceof String)
			{
				json.append(DELEMETER).append(value).append(DELEMETER);
			}
			else
			{
				json.append(value);				
			}
			json.append(",");
		}
		json.deleteCharAt(json.lastIndexOf(","));
		json.append("}");		
		return json.toString();
	}
}
