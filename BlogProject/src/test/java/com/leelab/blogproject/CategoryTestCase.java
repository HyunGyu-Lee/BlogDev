package com.leelab.blogproject;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.category.dao.MainCategoryDAO;
import com.leelab.blogproject.category.dao.SubCategoryDAO;
import com.leelab.blogproject.category.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"classpath:config/spring/context-*.xml"}
)
public class CategoryTestCase {
	
	@Inject
	private MainCategoryDAO mDao;
	
	@Inject
	private SubCategoryDAO sDao;
	
	private CategoryService service;
	
	@Before
	public void setUp() {
		service = new CategoryService();
		service.setMainCategoryDao(mDao);
		service.setSubCategoryDao(sDao);
	}
	
	@Test
	public void test() {
		
		service.deleteCategory(1, "main");
		
	}
	
}
