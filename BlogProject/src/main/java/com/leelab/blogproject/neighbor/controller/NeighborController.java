package com.leelab.blogproject.neighbor.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.neighbor.service.NeighborService;
import com.leelab.blogproject.neighbor.vo.NeighborVo;
import com.leelab.blogproject.notification.service.NotificationService;
import com.leelab.blogproject.notification.vo.NotificationBuilder;
import com.leelab.blogproject.notification.vo.NotificationVo;
import com.leelab.blogproject.user.service.UserService;

@Controller
public class NeighborController {

	private static final Logger logger = LoggerFactory.getLogger(NeighborController.class);

	@Autowired
	private NeighborService nService;
	
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doApply(NeighborVo vo) {
		logger.info("{}",vo);

		if(nService.isRelationEstablished(vo))
		{
			return SimpleHashMap.newInstance().put("code", -1);
		}
		else
		{
			nService.apply(vo);
			NotificationVo notification = new NotificationBuilder()
											  .setNotificateTarget(vo.getRel_user_id())
											  .setNotificator(vo.getUser_id())
											  .setLink("/manage?type=neighbor&user_id="+vo.getRel_user_id()+"&sub_type=from")
											  .setMessage("<strong>"+userService.getUser(vo.getUser_id()).getNickname()+"</strong>님이 이웃요청을 했습니다.")
											  .build();
			notificationService.notificate(notification);
		}
		
		
		
		return SimpleHashMap.newInstance().put("code", 1);
	}
	
	@RequestMapping(value="/accept", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doAccept(NeighborVo vo) {
		logger.info("{}",vo);
		nService.action(vo, NeighborVo.STATE_ACCEPT);
		
		NotificationVo notification = new NotificationBuilder()
				  .setNotificateTarget(vo.getUser_id())
				  .setNotificator(vo.getRel_user_id())
				  .setLink("/manage?type=neighbor&user_id="+vo.getUser_id()+"&sub_type=we")
				  .setMessage("<strong>"+userService.getUser(vo.getRel_user_id()).getNickname()+"</strong>님이 이웃요청을 승낙했습니다.")
				  .build();
		notificationService.notificate(notification);
		
		return SimpleHashMap.newInstance().put("code", 1);
	}
	
	@RequestMapping(value="/deny", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> doDeny(NeighborVo vo) {
		nService.action(vo, NeighborVo.STATE_DENY);
		
		NotificationVo notification = new NotificationBuilder()
				  .setNotificateTarget(vo.getUser_id())
				  .setNotificator(vo.getRel_user_id())
				  .setLink("/manage?type=neighbor&user_id="+vo.getUser_id()+"&sub_type=to")
				  .setMessage("<strong>"+userService.getUser(vo.getRel_user_id()).getNickname()+"</strong>님이 이웃요청을 거절했습니다.")
				  .build();
		notificationService.notificate(notification);
		
		return SimpleHashMap.newInstance().put("code", 1);
	}
}
