package com.leelab.blogproject.utils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class StringUtils {

	public static final String charset[] = {"KSC5601","8859_1", "ascii", "UTF-8", "EUC-KR", "MS949"};
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String encodeUTF_8(String source) throws UnsupportedEncodingException {
		return new String(source.getBytes("ISO-8859-1"),"UTF-8");
	}
	
	public static void loop(String s) throws UnsupportedEncodingException {
		 for(int i=0; i<charset.length ; i++)
		 {
			 for(int j=0 ; j<charset.length ; j++)
			 {
				 if(i==j){ continue;}
		         else
		         {
		        	 System.out.println(charset[i]+" : "+charset[j]+" :" +new String (s.getBytes(charset[i]),charset[j])+"<br>");
		         }
		      }
		  }  
	}
	
	
}
