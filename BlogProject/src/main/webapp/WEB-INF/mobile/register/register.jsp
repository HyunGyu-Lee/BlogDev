<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/include/app-header.jspf" %>
		<title>Blog - 회원가입</title>
	</head>
	<body>
		<form action="regist" id="register">
			아이디 : <input type="text" name="user_id"/> <input type="button" value="중복확인"/><br/>
			비밀번호 : <input type="text" name="user_password"/> <br/>
			<input type="button" class="btn regist" value="가입하기"/>			
		</form>
	</body>
</html>