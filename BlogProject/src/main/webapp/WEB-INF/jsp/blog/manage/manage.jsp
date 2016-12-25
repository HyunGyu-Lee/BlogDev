<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>${user.nickname}님의 블로그</title>
	</head>
	<body>
		<input type="hidden" id="idRef" value="${user.id}"/>
		<div class="pull-left menu-box" style="width: 25%">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4><span class="glyphicon glyphicon-cog"></span> 블로그 관리</h4>
				</div>
				<div class="panel-body">
					미구현 <br/>
		  		</div>
			</div>
		</div>
		
		<div class="pull-right info-box" style="width: 70%;">
			<c:choose>
				<c:when test="${type eq 'categoryInfo'}">
					<%@ include file="/WEB-INF/jsp/blog/manage/categoryInfo.jsp"%>
				</c:when>
			</c:choose>
		</div>
	</body>
</html>