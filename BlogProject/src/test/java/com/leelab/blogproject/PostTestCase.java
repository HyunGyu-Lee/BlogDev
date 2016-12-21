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
		
		int postCount = dao.getPostsCount(searchVO);	// 총 포스트 갯수
		
		for(int i=1;i<20;i++)
		{
			int postCountPerPage = 5;						// 페이지 당 포스트 갯수
			int pageGruopCount = 5;							// 노출될 페이지 갯수
			
			int pageNo = i;	// 페이지 번호
			
			int lastPostId = pageNo * postCountPerPage;	// 현재 페이지의 마지막 포스트 번호
			
			if(lastPostId > postCount) lastPostId = postCount;
			
			int firstPostId = lastPostId - (postCountPerPage - 1);	// 현재 페이지의 첫 포스트 번호
			
			if(firstPostId < 0) firstPostId = 1;

			int pageCount = postCount / postCountPerPage + (postCount % postCountPerPage == 0 ? 0 : 1); // 총 페이지 갯수
			
			if(pageNo > pageCount) pageNo = pageCount;
			
			int groupNo = pageNo / pageGruopCount + (pageNo % pageGruopCount == 0 ? 0 : 1);		// 페이지 내 그룹 번호
			
			int lastPageNo = groupNo * pageGruopCount;	// 현재 그룹의 마지막 페이지 번호
			int firstPageNo = lastPageNo - (pageGruopCount - 1);	// 현재 그룹의 첫 페이지 번호
			
			if(lastPageNo > pageCount) lastPageNo = pageCount;
			
			int prevPageNo = firstPageNo - pageGruopCount;	// 이전 페이지 번호
			int nextPageNo = firstPageNo + pageGruopCount;	// 다음 페이지 번호
					
			if(prevPageNo < 0) prevPageNo = 1;
			if(nextPageNo > pageCount) nextPageNo = pageCount;
			
			logger.info("==================================================================");			
			logger.info("총 레코드 개수      : {}", postCount);
			logger.info("현재 페이지          : {}", pageNo);
			logger.info("시작 레코드 번호    : {}", firstPostId);
			logger.info("마지막 레코드 번호 : {}", lastPostId);
			logger.info("총 페이지 갯수       : {}", pageCount);
			logger.info("현재 그룹번호        : {}", groupNo);
			logger.info("현재 그룹 첫 페이지 번호       : {}", firstPageNo);
			logger.info("현재 그룹 마지막 페이지 번호 : {}", lastPageNo);
			logger.info("이전 페이지 번호 : {}", prevPageNo);
			logger.info("다음 페이지 번호 : {}", nextPageNo);
			logger.info("==================================================================");
		}
		
	}
	
}
