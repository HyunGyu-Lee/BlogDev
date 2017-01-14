<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="contextPath" value="${contextPath}"/>
<div class="navigation">
	<!-- 햄버거 메뉴, 사이트 로고 -->
	<div class="pull-left">
		<ul>
			<li><a href="#main-menu" data-toggle="drawer" aria-foldedopen="false" aria-controls="main-menu"><span class="glyphicon glyphicon-menu-hamburger clickable main-menu"></span></a></li>
			<li><a href="${contextPath}/home" class="nav-title"><b>Public Blog</b></a></li>
		</ul>
	</div>
	<!-- 우측 메뉴 -->
	<div class="pull-right mobile-category-area" style="margin-right: 15px;">
		<c:if test="${empty sessionScope.user.id}">
			<span 
			data-container="body"
			data-html="true"
			data-toggle="popover"
			data-placement="left"
			style="font-size:30px;
			color:white;" class="clickable"><span class="glyphicon glyphicon-log-in"></span></span>
			<div class="hide" id="login-control">
		<div style="background-color: #CFD8DC; width: 300px; padding: 20px 25px 30px;">
			<img id="profile-img" class="profile-img-card" src="<c:url value="/resources/image/logo.png"/>" style="width: 180px;"/>
			<form class="form-signin-popover" style="margin-bottom: 10px;">
				<span id="reauth-email" class="reauth-email"></span>
				<input type="hidden" name="redirectUri" value="${redirectUri}">
				<input type="text" name="id" class="form-control" placeholder="ID" required autofocus>
				<input type="password" name="password" class="form-control" placeholder="Password" required>
				<div id="remember" class="checkbox">
					<label>
						<input type="checkbox" value="remember-me"> Remember me
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block btn-signin" id="login-btn-popover" type="button">로그인</button>
				<button class="btn btn-lg btn-primary btn-block btn-signin" type="button">비밀번호찾기</button>
			</form><!-- /form -->
			<a href="<c:url value="openRegister"/>" class="forgot-password">
				아직 회원이 아니신가요?
			</a>
		</div>
	</div>
		</c:if>
		<c:if test="${not empty sessionScope.user.id}">
			<span style="font-size:30px; color:white;" class="clickable" onclick="location.href='<c:url value="logout"/>'">
				<span class="glyphicon glyphicon-log-out"></span>
			</span>
		</c:if>
	</div>	
</div>


<div id="main-menu" class="drawer dw-xs-10 dw-sm-6 dw-md-4 fold" aria-labelledby="main-menu">
    <div class="drawer-contents">
        <div class="drawer-heading" style="background-color: #2196F3;">
           	<div style="padding-left: 10px;">
           		<h2><a style="color:white;" href="#main-menu" data-toggle="drawer" aria-foldedopen="false" aria-controls="main-menu"><span class="glyphicon glyphicon-menu-hamburger clickable"></span></a><span style="font-size: 30px; margin-left: 10px;">
	           	<a href="${contextPath}" style="color:white;"><b>Public Blog</b></a></span></h2>
           	</div>
           	<div style="padding-left:10px; padding-bottom:10px; margin-top: 15px;">
           		<%@ include file="/WEB-INF/jsp/home/login-control.jsp"%>
           	</div>
        </div>
        <div class="drawer-body">
        	<nav>
        		<ul class="">
	        		<li><span class="glyphicon glyphicon-bell"></span></li>
	        		<li><span class="glyphicon glyphicon glyphicon-user"></span></li>
	        		<li><span class="glyphicon glyphicon glyphicon-cog"></span></li>        		
	        	</ul>
        	</nav>
        </div>
        <ul class="drawer-nav">
            <li role="presentation" class="active"><a href="${contextPath}/${sessionScope.user.id}">내 블로그</a></li>
            <li role="presentation"><a href="#">컨텐츠2</a></li>
            <li role="presentation"><a href="#">컨텐츠3</a></li>
        </ul>
        <div class="drawer-footer">
            <small>&copy;Hyungyu, LEE</small>
        </div>
    </div>
</div>
<div class="container">
    <!-- content as per usual -->
</div>

<script type="text/javascript">
	$(document).ready(function(){
		Kakao.init('a43018ad95cd229f7260dcd2270add32');
		
		$('[data-toggle="popover"]').popover({
			html : true,
			container : 'body',
			content : function(){
				return $('#login-control').html();
			}
		});
		
		$(document).on('click','#login-btn-popover',function(){
			var data = $('.popover-content .form-signin-popover').serialize();
			console.log(data);
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
 							else if(response.redirectUri=='')
 							{
 								location.reload();
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