<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:if test="${empty sessionScope.user.id}">
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
	<div align="center" style="margin-bottom: 10px;">
		<span class="clickable" id="login-on-kakao"><img src="<c:url value="/resources/image/login_with_kakao.png"/>"></span>
	</div>
	<a href="${contextPath}/openRegister" style="color:white;">아직 회원이 아니신가요? 클릭</a>
</c:if>
<c:if test="${not empty sessionScope.user.id}">
	<div class="profile-box clearfix">
		<div class="userInfo pull-left">
			<img src="${contextPath}/ajax/profileImage/${sessionScope.user.id}" class="profile_view" style="border-radius: 50em;">

			<span class="username">${sessionScope.user.nickname}</span>님 
			<span class="logoutBtn"><a href="${contextPath}/logout" class="label label-danger">로그아웃</a></span>
		</div>
		<div class="user-control pull-left">
			<span class="glyphicon glyphicon glyphicon-cog" style="cursor: pointer;"></span> <br/>
			<span class="glyphicon glyphicon-bell" style="cursor: pointer;"></span> 
		</div>
	</div>
</c:if>

<script>
	$(document).ready(function(){
		
		$(document).on('click','#login-on-kakao', function(){
			Kakao.Auth.login({
				success : function(auth) {
					Kakao.API.request({
						url : '/v1/user/me',
						success : function(profileObj) {
							$.User.setContextPath('${contextPath}');
							var settings = {
								id : profileObj.id,
								nickname : profileObj.properties.nickname,
								profile_url : profileObj.properties.profile_image,
								isSocial : true
							};
							$.User.login(settings, function(){
								location.reload();
							});
						},
						fail : function() {
							swal('','로그인 할 수 없습니다.','error');
						}
					});
				}
			});
		});		
	});
</script>