package com.leelab.blogproject.notification.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.notification.service.NotificationService;
import com.leelab.blogproject.notification.vo.NotificationVo;
import com.leelab.blogproject.user.dto.UserDTO;

@Controller
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping("/getNotifications")
	@ResponseBody
	public HashMap<String, Object> getNotifications(@SessionAttribute UserDTO user) {
		NotificationVo vo = new NotificationVo();
		vo.setNotificate_target(user.getId());
		List<NotificationVo> notifications = notificationService.getNotification(vo);
		int unCheckedNotificationCount = notificationService.getUncheckedNotificationCount(notifications);
		return SimpleHashMap.newInstance().put("notifications", notifications).put("notification_count", unCheckedNotificationCount);
	}
	
}
