package com.leelab.blogproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.feature.dao.FeatureDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"classpath:config/spring/context-*.xml"}
)
public class FeatureTestCase {
	
	@Inject
	FeatureDAO dao;
	
	@Test
	public void test() {
		System.out.println(dao.select("gusrb0808"));
	}
	
}
