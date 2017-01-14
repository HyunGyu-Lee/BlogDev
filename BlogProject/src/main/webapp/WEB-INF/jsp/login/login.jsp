<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Blog - 로그인</title>
	</head>
<body>
	<div class="card card-container">
		<img id="profile-img" class="profile-img-card" src="<c:url value="/resources/image/logo.png"/>" style="width: 180px;"/>

		<form class="form-signin">
			<span id="reauth-email" class="reauth-email"></span>
			<c:if test="${not empty redirectUri}">
				<input type="hidden" name="redirectUri" value="${redirectUri}">
			</c:if>
			<input type="text" name="id" class="form-control" placeholder="ID" required autofocus>
			<input type="password" name="password" class="form-control" placeholder="Password" required>
			<div id="remember" class="checkbox">
				<label>
					<input type="checkbox" value="remember-me"> Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block btn-signin" id="login-btn" type="button">로그인</button>
			<button class="btn btn-lg btn-primary btn-block btn-signin" type="button">비밀번호찾기</button>
		</form><!-- /form -->
		<a href="<c:url value="openRegister"/>" class="forgot-password">
			아직 회원이 아니신가요?
		</a>
	</div>
	
	<script>
		$(document).ready(function(){
			$(document).on('click','#login-btn',function(){
				var data = $('.form-signin').serialize();
				
				if(true)
				{
					$.ajax({
						url : $('#contextPath').val()+'/ajax/login',
						type : 'post',
						data : data,
						dataType : 'json',
						success : function(response) {
							if(response.result==true)
							{
								console.log(response.redirectUri);
								if(response.redirectUri=='/blog')
								{
									window.location = $('#contextPath').val();
								}
								else if(redirectUri='')
								{
									window.location = $('#contextPath').val();
								}
								else
								{
									location.href = response.redirectUri;
								}
							}
							else
							{
								swal('','ID 또는 비밀번호를 확인하세요','error');
							}
						}
					})	
				}
				else
				{
					swal('','ID, 비밀번호를 모두 입력해주세요','error');
				}
			});
		});	
	</script>	
</body>
</html>