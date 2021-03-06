package com.leelab.blogproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	public static String PATH_DELEMETER;	
	
	public static String ROOT_DIRECTORY;
	public static String PROFILE;
	public static String TEMP;
	public static String BLOG;

	static
	{
		if(System.getProperty("os.name").contains("Windows"))
		{
			ROOT_DIRECTORY = "C:\\Users\\leehyungyu\\Desktop\\upload\\";			
			PATH_DELEMETER = "\\";
		}
		else
		{
			ROOT_DIRECTORY = "/home/ec2-user/upload/";
			PATH_DELEMETER = "/";
		}
		
		PROFILE	= ROOT_DIRECTORY + "profile" + PATH_DELEMETER;
		TEMP = ROOT_DIRECTORY + "temp" + PATH_DELEMETER;
		BLOG = ROOT_DIRECTORY +"blog" + PATH_DELEMETER;
	}
	
	public static void save(MultipartFile file, String saveFileName) throws IllegalStateException, IOException {
		file.transferTo(new File(saveFileName));
		logger.info("File saved in {}", saveFileName);
	}
	
	public static byte[] read(URL url) throws IOException {
		InputStream source = url.openStream();
		byte[] data = new byte[url.openConnection().getContentLength()];
		IOUtils.read(source, data);
		return data;
	}
	
	public static byte[] read(String readFileName) throws IOException {
		byte[] fileData = null;
		fileData = IOUtils.toByteArray(new FileInputStream(new File(readFileName)));
		return fileData;
	}
	
	public static String getFileExtension(String longFileName) {
		return longFileName.substring(longFileName.lastIndexOf("."), longFileName.length());
	}
}
