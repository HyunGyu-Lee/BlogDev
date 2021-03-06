<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="host" value="http://www.publicblog.xyz"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${feature.title} : Public Blog</title>
		<c:if test="${empty posts}">
			<c:set var="metaDesc" value="${post.title}"/>
		</c:if>
		<meta property="og:url" content="${host}">
		<meta property="og:type" content="website">
		<meta property="og:title" content="${feature.title} : Public Blog">
		<meta property="og:description" content="${empty metaDesc ? '세상과 소통하는 모두의 블로그, Public Blog' : metaDesc}">
		<meta property="og:image" content="${host}${contextPath}/resources/image/logo.png">
		<meta name="description" content="다양한 주제로 포스트를 적어가는 사람들을 만나보세요">
		<style>
			body {
				background-image: url('http://source.unsplash.com/random');
				background-size: cover;
			}
		</style>
	</head>
	<body>
		<!-- Blog Feature - Background Title Image -->
		<c:if test="${not empty feature.bgimg}">
			<div style="margin-top: 20px; margin-bottom:10px; width: 100%; height: 300px;">
				<img src="${contextPath}/blogBgImage/${user.id}" class="bgimg">
			</div>
		</c:if>
		<c:if test="${empty feature.bgimg and sessionScope.user.id eq user.id}">
			<div style="margin-top: 20px; margin-bottom:10px; width: 100%;">
				<div class="alert alert-warning" role="alert">
					블로그에 커버이미지가 없습니다. <br/>
					<a href="${contextPath}/manage?type=typography&user_id=${user.id}" class="alert-link">관리</a>페이지에서 블로그 배경화면과 블로그 정보를 설정할 수 있습니다.
				</div>
			</div>
		</c:if>
		
		<div style="margin-bottom: 10px;"></div>
		
		<div class="pull-left" style="width: 20%;">
			<%@ include file="/WEB-INF/jsp/blog/blog-leftSection.jsp" %>
		</div>
		<div class="pull-right" style="width: 75%;">
			<%@ include file="/WEB-INF/jsp/blog/blog-rightSection.jsp" %>				
		</div>
	</body>
</html>