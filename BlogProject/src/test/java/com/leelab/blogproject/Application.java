package com.leelab.blogproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.utils.json.JsonUtils;

public class Application {

	public static void main(String[] args) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		
		CommonResponseVO cvo = new CommonResponseVO(1, new Object[]{"A",SimpleHashMap.newInstance().put("A", 1), new Integer[]{1,2,3}});

		ObjectMapper m = new ObjectMapper();
		
		System.out.println(m.writeValueAsString(cvo));
		System.out.println(JsonUtils.toJsonString(cvo));

	}
	
}
