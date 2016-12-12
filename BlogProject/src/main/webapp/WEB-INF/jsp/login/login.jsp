<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Blog - 로그인</title>
		<%@ include file="/WEB-INF/include/app-header.jspf" %>
	</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>Blog, 블로그</h1>
		</div>
		
		<div align="center">
			<form id="login">
				<c:choose>
					<c:when test="${not empty redirectUri}">
						<input type="hidden" name="redirectUri" value="${redirectUri}"/>					
					</c:when>
					<c:otherwise>
						<input type="hidden" name="redirectUri" value="/"/>				
					</c:otherwise>
				</c:choose>
				<table class="table logintab table-borderless">
					<tr>
						<td><label for="id">아이디</label></td>
						<td><input type="text" id="id" name="id" class="form-control"/></td>
						<td rowspan="2"><input type="button" value="로그인" class="form-control login-button"/></td>
					</tr>
					<tr>
						<td><label for="password">비밀번호</label></td>
						<td><input type="password" id="password" name="password" class="form-control"/></td>
					</tr>
				</table>
			</form>
		</div>
	
	</div>
</body>
</html>