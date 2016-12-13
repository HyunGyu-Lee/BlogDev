package com.leelab.blogproject.category;

import java.util.ArrayList;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.category.main.MainCategoryDAO;
import com.leelab.blogproject.category.main.MainCategoryDTO;
import com.leelab.blogproject.category.sub.SubCategoryDAO;
import com.leelab.blogproject.category.sub.SubCategoryDTO;

@Service
public class CategoryService {

	private SqlSessionTemplate session;
	
	private MainCategoryDAO mainCategoryDao;
	private SubCategoryDAO subCategoryDao;
	
	@Autowired
	public void setSession(SqlSessionTemplate session) {
		this.session = session;
		mainCategoryDao = this.session.getMapper(MainCategoryDAO.class);
		subCategoryDao = this.session.getMapper(SubCategoryDAO.class);
	}
	
	public Map<MainCategoryDTO, ArrayList<SubCategoryDTO>> getUserCategory(String id) {
		CategoryVO categories = new CategoryVO();
		
		ArrayList<MainCategoryDTO> mains = mainCategoryDao.selectByUserId(id);
		
		for(MainCategoryDTO main : mains)
		{
			ArrayList<SubCategoryDTO> subs = subCategoryDao.selectByMainCategoryId(main.getId());
			categories.addCategories(main, subs);
		}
		
		return categories.getMap();
	}
	
	public void editCategoryName(int id, String name, String type) {
		if(type.equals("main"))
		{
			MainCategoryDTO main = new MainCategoryDTO();
			main.setId(id);
			main.setName(name);
			mainCategoryDao.update(main);
		}
		else
		{
			SubCategoryDTO sub = new SubCategoryDTO();
			sub.setId(id);
			sub.setName(name);
			subCategoryDao.update(sub);
		}
	}

}
