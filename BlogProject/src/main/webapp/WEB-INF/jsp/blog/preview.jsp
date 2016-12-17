<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/3.51/jquery.form.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script src="https://cdn.jsdelivr.net/sweetalert2/6.2.1/sweetalert2.min.js"></script>
		<link rel="stylesheet" href="/blog/css/register.css">
		<link rel="stylesheet" href="/blog/css/login.css">
		<link rel="stylesheet" href="/blog/css/home.css">
		<link rel="stylesheet" href="/blog/css/userInfo.css">
		<link rel="stylesheet" href="/blog/css/blog.css">
		<link rel="stylesheet" href="/blog/css/manage-categoryInfo.css">
		<link rel="stylesheet" href="/blog/css/writePost.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.2.1/sweetalert2.min.css">
		<title>포스트 미리보기</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="/WEB-INF/jsp/home/home-header.jsp" %>
				
			<div class="pull-left" style="width: 21%; margin-right: 50px;">
				<%@ include file="/WEB-INF/jsp/blog/blog-leftSection.jsp" %>
			</div>
			<div id="post" class="pull-left" style="width: 73%;">
				<div class="panel panel-info">
					<div class="panel-heading">
						<span class="post-title"><strong>${title}</strong></span><span class="post-category"> | ${categoryName}</span>
					</div>
					<div class="panel-body">
						${content}
					</div>
				</div>
			</div>		
		</div>
	</body>
</html>