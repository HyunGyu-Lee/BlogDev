package com.leelab.blogproject.utils.prettylogger;

import java.util.List;

import org.slf4j.Logger;

public interface PrettyLogger extends Logger{
	
	/**
	 * @return String
	 * */
	public <T> String list(String name, List<T> list);

}
