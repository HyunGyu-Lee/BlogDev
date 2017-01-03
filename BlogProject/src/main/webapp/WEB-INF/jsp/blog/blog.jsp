<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${feature.title} : Public Blog</title>
	</head>
	<body>
		<!-- Blog Feature - Background Title Image -->
		<c:if test="${not empty feature.bgimg}">
			<div style="margin-top: 20px; margin-bottom:10px; width: 100%; height: 300px;">
				<img src="/blog/blogBgImage/${user.id}" class="bgimg">
			</div>
		</c:if>
		<c:if test="${empty feature.bgimg and sessionScope.user.id eq user.id}">
			<div style="margin-top: 20px; margin-bottom:10px; width: 100%;">
				<div class="alert alert-warning" role="alert">
					블로그에 커버이미지가 없습니다. <br/>
					<a href="/blog/manage?type=typography&user_id=${user.id}" class="alert-link">관리</a>페이지에서 블로그 배경화면과 블로그 정보를 설정할 수 있습니다.
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