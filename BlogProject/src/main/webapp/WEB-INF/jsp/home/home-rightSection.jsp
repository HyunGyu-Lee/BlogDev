<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%-- <c:if test="${empty sessionScope.user.id}">
	<div>
		<form id="login">
			<table class="table table-borderless">
				<tr>
					<td><input type="text" id="id" name="id" class="form-control"/></td>
					<td rowspan="2"><button type="button" value="로그인" class="form-control login-button"> 로그인</button></td>
				</tr>
				<tr>
					<td><input type="password" id="password" name="password" class="form-control"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div align="right">
		<button type="button" class="btn register">회원가입</button>
	</div>
</c:if>
<c:if test="${not empty sessionScope.user.id}">
	<div class="profile-box">
		<div class="userInfo">
			<img src="ajax/profileImage/${sessionScope.user.id}" class="profile_view">
			<span class="username">${sessionScope.user.nickname}</span>님 
			<span class="logoutBtn"><a href="logout" class="label label-danger">로그아웃</a></span>
		</div>
		<div class="control">
			<input type="hidden" id="idRef" value="${sessionScope.user.id}"/>
			<a href="#" class="label label-success openMyBlog">내 블로그</a>
		</div>
	</div>
</c:if> --%>