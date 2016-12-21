package com.leelab.blogproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	public static final String ROOT_DIRECTORY = "C:\\Users\\leehyungyu\\Desktop\\upload\\";
	
	public static final String PROFILE = ROOT_DIRECTORY+"profile\\";
	public static final String TEMP = ROOT_DIRECTORY+"temp\\";
	
	public static void save(MultipartFile file, String saveFileName) throws IllegalStateException, IOException {
		file.transferTo(new File(saveFileName));
		logger.info("File saved in {}", saveFileName);
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
