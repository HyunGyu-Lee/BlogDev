<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${user.nickname}님의 블로그</title>
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