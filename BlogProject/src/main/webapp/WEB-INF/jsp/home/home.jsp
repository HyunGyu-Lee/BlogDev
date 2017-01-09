<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Blog - 메인</title>
	</head>
	<body>
		<div>
			<%@ include file="/WEB-INF/jsp/home/home-headerSection.jsp" %>
		</div>
		
		<c:choose>
			<c:when test="${empty type or type eq 'home'}">
				홈
				이웃 소식
			</c:when>
			<c:when test="${type eq 'search'}">
				검색결과
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
		
		
		
	</body>
</html>