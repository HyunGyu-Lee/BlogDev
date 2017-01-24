package com.leelab.blogproject.notification.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.leelab.blogproject.notification.vo.NotificationVo;

@Repository
public interface NotificationDAO {

	List<NotificationVo> select(NotificationVo vo);
	
	void insert(NotificationVo notification);

	void update(String notificate_target, int stateChecked);

	void insertAll(@Param("nVo")NotificationVo notificationVo, @Param("targets")String[] targets);
	
}
