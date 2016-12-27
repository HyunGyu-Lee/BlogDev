package com.leelab.blogproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.comment.dao.CommentDAO;
import com.leelab.blogproject.comment.vo.CommentVO;
import com.leelab.blogproject.post.vo.SearchVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"classpath:config/spring/context-*.xml"}
)
public class CommentTestCase {

	@Inject
	CommentDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentTestCase.class);
	
	@Test
	public void test() {
		SearchVO search = new SearchVO();

		
	}
	
	
}
