<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${user.nickname}님의 블로그</title>
		<meta property="og:type" content="website">
		<meta property="og:title" content="${feature.title} : Public Blog">
		<meta property="og:description" content="${empty metaDesc ? '세상과 소통하는 모두의 블로그, Public Blog' : metaDesc}">
		<meta property="og:image" content="${host}${contextPath}/resources/error.jpg">
		<meta name="description" content="다양한 주제로 포스트를 적어가는 사람들을 만나보세요">
	</head>
	<body>
		<div>
			<%@ include file="/WEB-INF/jsp/mobile/blog/blog-userInfoArea.jsp" %>
		</div>
		
		<div>
			<%@ include file="/WEB-INF/jsp/mobile/blog/blog-postArea.jsp" %>				
		</div>
	</body>
</html>