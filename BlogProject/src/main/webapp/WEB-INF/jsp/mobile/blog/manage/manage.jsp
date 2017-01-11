<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${user.nickname}님의 블로그</title>
	</head>
	<body>
		<input type="hidden" id="idRef" value="${user.id}"/>
		<div class="navbar navbar-default container-fluid" style="padding-left: 0px;">
			<ul class="nav navbar-nav nav-pills navbar-left">
				<li role="presentation" class="typography"><a href="${contextPath}/manage?type=typography&user_id=${user.id}">블로그 설정</a></li>
				<li role="presentation" class="decoration"><a href="#">꾸미기 설정</a></li>
				<li role="presentation" class="categoryInfo"><a href="${contextPath}/manage/categoryInfo?blogId=${user.id}">글 관리</a></li>
				<li role="presentation" class="stat"><a href="#">통계</a></li>		
				<li><a href="${contextPath}/${user.id}"><strong>←</strong></a></li>	
			</ul>
		</div>
		
		<div class="info-box">
			<c:choose>
				<c:when test="${type eq 'categoryInfo'}">
					<%@ include file="/WEB-INF/jsp/mobile/blog/manage/categoryInfo.jsp"%>
				</c:when>
				<c:when test="${type eq 'typography'}">
					<%@ include file="/WEB-INF/jsp/mobile/blog/manage/typography.jsp"%>
				</c:when>
			</c:choose>
		</div>
		
		<script type="text/javascript">
			$(document).ready(function(){				
				var type = '${type}';
				$('.'+type).addClass('active');
				
				$(document).on('click','.gotoMyBlog',function(){
					swal('','')
				});
			});
		</script>
	</body>
</html>