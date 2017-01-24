package com.leelab.blogproject.notification.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leelab.blogproject.neighbor.vo.NeighborVo;
import com.leelab.blogproject.notification.dao.NotificationDAO;
import com.leelab.blogproject.notification.vo.NotificationVo;

@Service
public class NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	@Autowired
	private NotificationDAO notificationDao;
	
	public List<NotificationVo> getNotification(NotificationVo vo) {
		List<NotificationVo> list = notificationDao.select(vo);
		for(NotificationVo item : list)logger.debug("{}",item);
		return list;
	}

	public int getUncheckedNotificationCount(List<NotificationVo> notifications) {
		int cnt = 0;

		for(NotificationVo vo : notifications)
		{
			if(vo.getCheck_state()==NotificationVo.state_not_checked)cnt++;
		}
		
		/* 일괄 읽음 처리 */
		if(cnt!=0)notificationDao.update(notifications.get(0).getNotificate_target(), NotificationVo.state_checked);
		
		return cnt;
	}
	
	public void notificate(NotificationVo vo, List<NeighborVo> neighborList) {
		notificationDao.insertAll(vo, resolveTarget(neighborList));		
	}

	public String[] resolveTarget(List<NeighborVo> neighborList) {
		String[] targets = new String[neighborList.size()];
		int idx=0;
		
		for(NeighborVo n : neighborList)
		{
			if(n.getTag().equals("USER"))targets[idx++] = n.getRel_user_id();
			else if(n.getTag().equals("REL_USER"))targets[idx++] = n.getUser_id();
		}
		
		return targets;
	}

	public void notificate(NotificationVo vo) {
		notificationDao.insert(vo);
	}
	
	/**
	 *  이웃 추가 요청 - 이웃신청 요청 받은 사람에게 알림 	 / Neighbor
	 *  나의 이웃 요청이 수락 또는 거절 - 수락,거절 결과를 받을 사람에게 알림	/ Neighbor
	 * */
	
}
