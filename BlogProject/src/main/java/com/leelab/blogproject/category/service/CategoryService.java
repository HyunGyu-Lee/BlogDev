package com.leelab.blogproject.category.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.category.dao.MainCategoryDAO;
import com.leelab.blogproject.category.dao.SubCategoryDAO;
import com.leelab.blogproject.category.dto.MainCategoryDTO;
import com.leelab.blogproject.category.dto.SubCategoryDTO;
import com.leelab.blogproject.category.vo.CategoryVO;

@Service
public class CategoryService {

	@Autowired
	private MainCategoryDAO mainCategoryDao;
	
	public void setMainCategoryDao(MainCategoryDAO mainCategoryDao) {
		this.mainCategoryDao = mainCategoryDao;
	}

	public void setSubCategoryDao(SubCategoryDAO subCategoryDao) {
		this.subCategoryDao = subCategoryDao;
	}

	@Autowired
	private SubCategoryDAO subCategoryDao;
	
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

	public void editCategoryOrder(int targetId, int switchedId, String type) {
		if(type.equals("main"))
		{
			MainCategoryDTO target = mainCategoryDao.selectById(targetId);
			MainCategoryDTO switched = mainCategoryDao.selectById(switchedId);
			
			int temp = target.getCategory_order();
			
			target.setCategory_order(switched.getCategory_order());
			switched.setCategory_order(temp);
			
			mainCategoryDao.update(target);
			mainCategoryDao.update(switched);
		}
		else
		{
			SubCategoryDTO target = subCategoryDao.selectById(targetId);
			SubCategoryDTO switched = subCategoryDao.selectById(switchedId);
			
			int temp = target.getCategory_order();
			
			target.setCategory_order(switched.getCategory_order());
			switched.setCategory_order(temp);
			
			subCategoryDao.update(target);
			subCategoryDao.update(switched);
		}
	}

	public void deleteCategory(int id, String type) {
		if(type.equals("main"))
		{
			mainCategoryDao.delete(id);
		}
		else
		{
			subCategoryDao.delete(id);
		}		
	}
	
	/** ���� ������ ī�װ� �߰�
	 * id : �߰��� ī�װ� �� ī�װ� id
	 * next_category_id : �߰��� ī�װ� ���� ī�װ� id, -999�� next_category�� ���� ���
	 * type : �߰��� ī�װ��� 'main'���� 'sub'����
	 * name : �߰��� ī�װ��� �̸�
	 * */
	public void addCategoryOnCurrentLevel(int id, int next_category_id, String type, String name) {
		if(type.equals("main"))
		{
			MainCategoryDTO category = mainCategoryDao.selectById(id);
			/* ���� ī�װ��� ���ϴ��� ��� */
			if(next_category_id==-999)
			{
				mainCategoryDao.insert(new MainCategoryDTO(0, category.getUser_id(), name, category.getCategory_order()+1));
				return;
			}
			
			MainCategoryDTO nextCategory = mainCategoryDao.selectById(next_category_id);
			
			/* ���� ī�װ� �Ʒ��� �ٷ� �߰��� �� �ִ� ��� */
			if(category.getCategory_order()+1!=nextCategory.getCategory_order())
			{
				mainCategoryDao.insert(new MainCategoryDTO(0, category.getUser_id(), name, category.getCategory_order()+1));
			}
			/* �׷��� ���� ��� ����Ȯ�� */
			else
			{
				ArrayList<MainCategoryDTO> categories = mainCategoryDao.selectByUserIdOver(category.getUser_id(), category.getCategory_order());
				for(MainCategoryDTO mCate : categories)
				{
					mCate.setCategory_order(mCate.getCategory_order()+1);
					mainCategoryDao.update(mCate);
				}
				mainCategoryDao.insert(new MainCategoryDTO(0, category.getUser_id(), name, category.getCategory_order()+1));
			}
		}
		else
		{
			SubCategoryDTO category = subCategoryDao.selectById(id);
			
			if(next_category_id==-999)
			{
				subCategoryDao.insert(new SubCategoryDTO(0, category.getUser_id(), name, category.getCategory_order()+1, category.getMain_category_id()));
				return;
			}
			
			SubCategoryDTO nextCategory = subCategoryDao.selectById(next_category_id);
			
			if(category.getCategory_order()+1!=nextCategory.getCategory_order())
			{
				subCategoryDao.insert(new SubCategoryDTO(0, category.getUser_id(), name, category.getCategory_order()+1, category.getMain_category_id()));
			}
			/* �׷��� ���� ��� ���� Ȯ�� */
			else
			{
				ArrayList<SubCategoryDTO> categories = subCategoryDao.selectByUserIdOver(category.getUser_id(), category.getCategory_order(), category.getMain_category_id());
				for(SubCategoryDTO sCate : categories)
				{
					sCate.setCategory_order(sCate.getCategory_order()+1);
					subCategoryDao.update(sCate);
				}
				subCategoryDao.insert(new SubCategoryDTO(0, category.getUser_id(), name, category.getCategory_order()+1, category.getMain_category_id()));
			}
		}
	}
	
	/* ���� ������ ī�װ� �߰� */
	public void addCategoryInnerLevel(int main_category_id, String name) {
		MainCategoryDTO main = mainCategoryDao.selectById(main_category_id);
		Integer maxOrder = subCategoryDao.getMaxOrderByMainCategoryId(main.getId());
		if(maxOrder==null)
		{
			maxOrder = 0;
		}		
		subCategoryDao.insert(new SubCategoryDTO(0, main.getUser_id(), name, maxOrder+1, main.getId()));
	}

	public MainCategoryDTO getMainCategory(int main_category_id) {
		return mainCategoryDao.selectById(main_category_id);
	}

	public SubCategoryDTO getSubCategory(int sub_category_id) {
		return subCategoryDao.selectById(sub_category_id);
	}

	public void addFirstCategory(String name, String blog_id) {
		MainCategoryDTO main = new MainCategoryDTO();
		main.setCategory_order(1);
		main.setName(name);
		main.setUser_id(blog_id);
		mainCategoryDao.insert(main);
	}

}
