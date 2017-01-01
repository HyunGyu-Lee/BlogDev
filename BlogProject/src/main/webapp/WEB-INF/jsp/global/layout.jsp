<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=100%; initial-scale=1; maximum-scale=1; minimum-scale=1; user-scalable=no;"/>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> 
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/3.51/jquery.form.js"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.2.1/sweetalert2.min.css">
		<link rel="stylesheet" href="/blog/resources/bootstrap-3.3.2-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="/blog/resources/css/summernote.css">
		<link rel="stylesheet" href="/blog/resources/css/register.css">
		<link rel="stylesheet" href="/blog/resources/css/login.css">
		<link rel="stylesheet" href="/blog/resources/css/a.css">		
		<link rel="stylesheet" href="/blog/resources/css/home.css">
		<link rel="stylesheet" href="/blog/resources/css/userInfo.css">
		<link rel="stylesheet" href="/blog/resources/css/blog.css">
		<link rel="stylesheet" href="/blog/resources/css/manage-categoryInfo.css">
		<link rel="stylesheet" href="/blog/resources/css/writePost.css">
		<link rel="stylesheet" href="/blog/resources/css/bootstrap-drawer.min.css">		
		<title><decorator:title default="Welcome Public Blog"/> </title>
	</head>
	<body class="has-drawer" style=" padding-top: 70px;">
		<!-- Header -->
		<page:applyDecorator name="header"/>

		<!-- 기기 속성에 따른 Container 설정 -->
		<c:choose>
			<c:when test="${device eq 'mobile'}">
				<c:set var="container" value="container-fluid"/>
			</c:when>
			<c:otherwise>
				<c:set var="container" value="container"/>		
			</c:otherwise>
		</c:choose>
		
		<!-- Body -->
		<div class="${container}" style="padding: 0px">			
			<div class="clearfix">
				<decorator:body/>
			</div>
		</div>
		
		<!-- Footer -->
		<page:applyDecorator name="footer"/>		
	</body>
</html>