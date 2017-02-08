package com.leelab.blogproject.statistics.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.leelab.blogproject.common.annotation.NotLoginCheck;
import com.leelab.blogproject.common.vo.SimpleHashMap;
import com.leelab.blogproject.statistics.service.StatisticService;
import com.leelab.blogproject.statistics.vo.GeneralStatisticVo;
import com.leelab.blogproject.user.dto.UserDTO;
import com.leelab.blogproject.utils.DateUtils;
import com.leelab.blogproject.utils.prettylogger.ObjectPrinter;

@Controller
public class StatisticController {

	private static final Logger logger = LoggerFactory.getLogger(StatisticController.class);
										 //PrettyLoggerFactory.createInstance(StatisticController.class);
	
	@Autowired
	private StatisticService statService;
	
	@RequestMapping("sample")
	@ResponseBody
	@NotLoginCheck
	public HashMap<String, Object> sample(GeneralStatisticVo sVo, HttpServletRequest request, @SessionAttribute("user") UserDTO user) {
		
		if(request.getParameter("now").equals("true"))
		{
			Timestamp now = DateUtils.now();
			sVo.setStartDate(DateUtils.diff(now, Calendar.DAY_OF_WEEK, -14));
			sVo.setEndDate(now);
			sVo.setUser_id(user.getId());
			List<GeneralStatisticVo> vos = statService.getVisitCount(sVo);
			logger.info("Now 요청! {}",sVo);
			
			logger.info(ObjectPrinter.list("통계 객체 목록", vos, ""));			
		}
		
		List<SampleVo> vos = new ArrayList<SampleVo>();
		
		for(int i=14;i<=28;i++)
		{
			SampleVo vo = new SampleVo();
			vo.setDate(i+"");
			vo.setCount(RandomUtils.nextInt(6));
			vos.add(vo);
		}
		
		return SimpleHashMap.newInstance().put("vos", vos);
	}

	/**
	 * @author Lee Hyungyu
	 * @param date 파싱할 날짜 문자열
	 * @return 파싱결과, 파싱에 실패하면 null
	 */
	public Timestamp getTimestamp(String date) {
		Timestamp stamp = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stamp = new Timestamp(sdf.parse(date).getTime());
		} catch (ParseException e) {
			return null;
		}

		return stamp;
	}

	class SampleVo {
		private String date;
		private int count;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
}
