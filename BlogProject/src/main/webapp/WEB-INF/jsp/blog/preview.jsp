<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="decorator" content="no">
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=100%; initial-scale=1; maximum-scale=1; minimum-scale=1; user-scalable=no;"/>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.1.min.js"/>"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/3.51/jquery.form.js"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.2.1/sweetalert2.min.css">
		<link rel="stylesheet" href="${contextPath}/resources/bootstrap-3.3.2-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/summernote.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/register.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/login.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/a.css">		
		<link rel="stylesheet" href="${contextPath}/resources/css/home.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/userInfo.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/blog.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/manage-categoryInfo.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/writePost.css">
		<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap-drawer.min.css">
		<title>포스트 미리보기</title>
	</head>
	<body><div class="container">
		<%@ include file="/WEB-INF/jsp/global/header.jsp" %>
		<div class="pull-left" style="width: 25%;">
			<%@ include file="/WEB-INF/jsp/blog/blog-leftSection.jsp" %>
		</div>
		<div id="post" class="pull-right" style="width: 70%;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="post-title"><strong>${title}</strong></span><span class="post-category"> | ${categoryName}</span>
				</div>
				<div class="panel-body">
					${content}
				</div>
			</div>
		</div>
</div></body>
</html>