package com.leelab.blogproject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leelab.blogproject.post.dao.PostDAO;
import com.leelab.blogproject.post.vo.SearchVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"classpath:config/spring/context-*.xml"}
)
public class PostTestCase {
	
	private static final Logger logger = LoggerFactory.getLogger(PostTestCase.class);
	
	@Inject
	private PostDAO dao;
	
	private int[] mCateArray = {16,17,18,24,25,26};
	
	@Test
	public void test() {
		SearchVO searchVO = new SearchVO();
		searchVO.setUser_id("admin");
//		searchVO.setMain_category_id(16);
//		searchVO.setSub_category_id(2);
		
		int postCount = dao.getPostsCount(searchVO);	// �� ����Ʈ ����
		
		for(int i=1;i<20;i++)
		{
			int postCountPerPage = 5;						// ������ �� ����Ʈ ����
			int pageGruopCount = 5;							// ����� ������ ����
			
			int pageNo = i;	// ������ ��ȣ
			
			int lastPostId = pageNo * postCountPerPage;	// ���� �������� ������ ����Ʈ ��ȣ
			
			if(lastPostId > postCount) lastPostId = postCount;
			
			int firstPostId = lastPostId - (postCountPerPage - 1);	// ���� �������� ù ����Ʈ ��ȣ
			
			if(firstPostId < 0) firstPostId = 1;

			int pageCount = postCount / postCountPerPage + (postCount % postCountPerPage == 0 ? 0 : 1); // �� ������ ����
			
			if(pageNo > pageCount) pageNo = pageCount;
			
			int groupNo = pageNo / pageGruopCount + (pageNo % pageGruopCount == 0 ? 0 : 1);		// ������ �� �׷� ��ȣ
			
			int lastPageNo = groupNo * pageGruopCount;	// ���� �׷��� ������ ������ ��ȣ
			int firstPageNo = lastPageNo - (pageGruopCount - 1);	// ���� �׷��� ù ������ ��ȣ
			
			if(lastPageNo > pageCount) lastPageNo = pageCount;
			
			int prevPageNo = firstPageNo - pageGruopCount;	// ���� ������ ��ȣ
			int nextPageNo = firstPageNo + pageGruopCount;	// ���� ������ ��ȣ
					
			if(prevPageNo < 0) prevPageNo = 1;
			if(nextPageNo > pageCount) nextPageNo = pageCount;
			
			logger.info("==================================================================");			
			logger.info("�� ���ڵ� ����      : {}", postCount);
			logger.info("���� ������          : {}", pageNo);
			logger.info("���� ���ڵ� ��ȣ    : {}", firstPostId);
			logger.info("������ ���ڵ� ��ȣ : {}", lastPostId);
			logger.info("�� ������ ����       : {}", pageCount);
			logger.info("���� �׷��ȣ        : {}", groupNo);
			logger.info("���� �׷� ù ������ ��ȣ       : {}", firstPageNo);
			logger.info("���� �׷� ������ ������ ��ȣ : {}", lastPageNo);
			logger.info("���� ������ ��ȣ : {}", prevPageNo);
			logger.info("���� ������ ��ȣ : {}", nextPageNo);
			logger.info("==================================================================");
		}
		
	}
	
}
