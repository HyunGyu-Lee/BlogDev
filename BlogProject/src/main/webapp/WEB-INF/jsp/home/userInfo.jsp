<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/include/app-header.jspf"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Blog - 사용자 정보 상세</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="/WEB-INF/jsp/home/home-header.jsp" %>
			<div>
				<div class="panel panel-primary">
					<div class="panel-heading"><h1>회원 정보</h1></div>
				  	<div class="panel-body" align="center">
				    	<table class="table">
				    		<tr>
				    			<td rowspan="6" align="center">
				    				<img src="ajax/profileImage/${sessionScope.user.id}" class="profile_view_normal"><br/><br/><br/>
				    				<input type="file" name="profile_url_normal" value="변경"/>
				    			</td>
				    			<td>ID</td>
				    			<td>${sessionScope.user.id}</td>
				    		</tr>
				    		<tr>
				    			<td>닉네임</td>
				    			<td>${sessionScope.user.nickname}</td>
				    		</tr>
				    		<tr>
				    			<td>비밀번호</td>
				    			<td style="padding-top: 15px;">
				    				<span class="password_view label label-danger">비밀번호 확인을 위해 한번더 인증</span>
				    				<div class="auth_view">
				    					<br/>
				    					<input type="text" name="password"/><input type="button" value="인증"/>
				    				</div>				    				
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>이메일</td>
				    			<td><span>${sessionScope.user.email}</span></td>
				    		</tr>
				    		<tr>
				    			<td>전화번호</td>
				    			<td><span>${sessionScope.user.phone}</span></td>
				    		</tr>
				    		<tr>
				    			<td>가입일</td>
				    			<td><span><fmt:formatDate value="${sessionScope.user.create_at}" pattern="yyyy년 MM월 dd일 HH시mm분ss초"/></span></td>
				    		</tr>
				    	</table>
				  	</div>
				  	<div class="panel-footer">
				  		Panel footer
				  	</div>
				</div>
			</div>
		</div>
	</body>
</html>