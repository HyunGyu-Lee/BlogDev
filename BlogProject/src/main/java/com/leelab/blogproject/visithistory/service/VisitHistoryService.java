package com.leelab.blogproject.visithistory.service;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leelab.blogproject.visithistory.dao.VisitHistoryDAO;
import com.leelab.blogproject.visithistory.vo.VisitHistoryVO;
import com.leelab.blogproject.visithistory.vo.VisitorCountVO;

@Service
public class VisitHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(VisitHistoryService.class);
	
	@Autowired
	private VisitHistoryDAO visitHistoryDao;

	public VisitorCountVO getBlogVisitorCountInfo(VisitHistoryVO historyVo) {
//		logger.info("===================== �湮��� insert ���� DB ��Ȳ ==========================");
		historyVo.setVisitor_id(null);
		for(VisitHistoryVO v : visitHistoryDao.select(historyVo))
		{
//			logger.info("�湮 ��α� : {}, �湮�� : {}, �湮 �ð� {}",v.getBlog_id(),v.getVisitor_id(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(v.getVisited_at()));
		}
		
		VisitorCountVO cVo = visitHistoryDao.selectCount(historyVo);
//		logger.info("===================== ��ȯ�Ǵ� �湮�� ��� ===================================");
//		logger.info("TODAY    : {}", cVo.getUnique_today_visitor());
//		logger.info("TOTAL    : {}", cVo.getUnique_total_visitor());
//		logger.info("��ü TODAY : {}", cVo.getToday_visitor());
//		logger.info("��ü TOTAL : {}", cVo.getTotal_visitor());
//		logger.info("========================================================================");
		return cVo;
	}

	public void visitBlog(VisitHistoryVO historyVo) {
		visitHistoryDao.insert(historyVo);
	}
	

}
