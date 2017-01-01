<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${user.nickname}님의 블로그</title>
	</head>
	<body>
		<!-- Blog Feature - Background Title Image -->
		<div style="margin-top: 20px; margin-bottom:10px; width: 100%; height: 300px;">
			<img src="/blog/blogBgImage/${user.id}" class="bgimg">
		</div>
	
		<div class="pull-left" style="width: 20%;">
			<%@ include file="/WEB-INF/jsp/blog/blog-leftSection.jsp" %>
		</div>
		<div class="pull-right" style="width: 75%;">
			<%@ include file="/WEB-INF/jsp/blog/blog-rightSection.jsp" %>				
		</div>
	</body>
</html>