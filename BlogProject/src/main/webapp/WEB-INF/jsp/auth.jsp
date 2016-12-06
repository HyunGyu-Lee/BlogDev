<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Blog - 인증</title>
		<%@ include file="/WEB-INF/include/app-header.jspf" %>
	</head>
	<body>
		<div class="container">
			<%@ include file="/WEB-INF/jsp/home/home-header.jsp" %>
		
			<div align="center">
				<div class="panel panel-primary" style="width: 50%;">
				<div class="panel panel-heading"><h3>이메일 인증</h3></div>
				<div class="panel panel-body" align="left">
					<b>${sessionScope.user.nickname}</b>님의 계정은 아직 이메일 인증이 되지 않은 계정입니다.<br/>
					아래의 메일 주소를 확인 후 인증번호를 입력해주세요 <br/>
					${sessionScope.user.email}<br/><br/>	
				
					<label for="auth_key_input">인증키 입력</label><br/>
					<input type="text" name="auth_key" id="auth_key_input" class="form-control"/><br/>
					<div class="pull-left">
						<span class="auth_info" style="color: red;"><b>인증번호가 일치하지 않습니다.</b></span>
					</div>
					<div class="pull-right">
						<input type="button" id="re-auth" value="인증메일 재요청" class="btn btn-warning"/>
						<input type="button" id="auth" value="인증요청" class="btn btn-info"/>
					</div>
				</div>
			</div>
			</div>
		</div>
		
	<script type="text/javascript">
	$(function(){
		
		$('.auth_info').hide();
		
		$('#auth').click(function(){
			$.ajax({
				url : 'ajax/auth',
				type : 'post',
				data : {auth_key : $('input[name*="auth_key"]').val()},
				dataType : 'json',
				success : function(response) {
					if(response.result==true)
					{
						alert('이메일 인증에 성공하셨습니다.');
						location.href = "/blog/";
					}
					else
					{
						$('.auth_info').show();
					}
				}
			})
		});
		
		$('#re-auth').click(function(){
			$.ajax({
				url : 'ajax/reAuth',
				type : 'post',
				success : function() {
					alert('새 인증코드가 발송됐습니다.');
				}
			})
		});
	});
	</script>	
	</body>
</html>