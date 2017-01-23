package com.leelab.blogproject.notification.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.notification.dao.NotificationDAO;
import com.leelab.blogproject.notification.vo.NotificationVo;

@Service
public class NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	@Autowired
	private NotificationDAO notificationDao;
	
	public List<NotificationVo> getNotification(NotificationVo vo) {
		List<NotificationVo> list = notificationDao.select(vo);
		logger.info("{}", vo);
		for(NotificationVo item : list)
		{
			logger.info("{}",item);
		}
		
		return list;
	}

	public int getUncheckedNotificationCount(List<NotificationVo> notifications) {
		int cnt = 0;

		for(NotificationVo vo : notifications)
		{
			if(vo.getCheck_state()==NotificationVo.state_not_checked)
			{
				cnt++;
			}
		}
		
		/* 일괄 읽음 처리 */
		if(cnt!=0)notificationDao.update(notifications.get(0).getNotificate_target(), NotificationVo.state_checked);
		
		return cnt;
	}
	
	/**
	 *  나에게 이웃 추가 요청 - 이웃신청 요청 받은 사람
	 *  나의 이웃 요청이 수락 또는 거절 - 수락,거절 결과를 받을 사람에게 알림
	 *  이웃의 새 포스트 - 그 사람의 이웃들에게 알림
	 *	내 포스트의 댓글 - 포스트 쓴 사람, 해당 포스트에 댓글 남긴 사람들한테 알김
	 *	내 포스트가 공유됨 - 포스트 쓴 사람에게 알림
	 *  각각 메소드를 구현해두고 필요한 곳에 Autowired해서 사용하자
	 * */
	
}
