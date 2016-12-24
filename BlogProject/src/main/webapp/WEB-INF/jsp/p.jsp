<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<%@ include file="/WEB-INF/include/app-header.jspf" %>
	</head>
	<body>
				
		<c:if test="${empty sessionScope.user}">
			<form id="loginForm">
				아이디 : <input type="text" name="id"/><br/>
				비밀번호 : <input type="password" name="password"/><br/>
				<input type="button" value="로그인"/>
			</form>		
		</c:if>
		<c:if test="${not empty sessionScope.user}">
			<img class="profile_view" src="/blog/ajax/profileImage/${sessionScope.user.id}">
			<strong>${sessionScope.user.nickname}</strong>님 환영합니다
			<a href="/blog/util/logout">로그아웃</a>
		</c:if>
		
		<script type="text/javascript">
			$(document).on('click','input[type="button"]', function(){				
				var data = $('#loginForm').serialize();
				
				$.ajax({
					url : '/blog/util/login',
					type : 'post',
					data : data,
					success : function(response){
						if(response.result)
						{
							swal('','로그인성공','success').then(function(){
								location.href = '/blog/util/test-p';
							});
						}
						else
						{
							swal('','로그인실패','error');
						}	
					}
				});
			});
		</script>
	</body>
</html>