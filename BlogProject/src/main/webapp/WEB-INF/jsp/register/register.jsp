<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/include/app-header.jspf" %>
		<title>Blog - 회원가입</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="/WEB-INF/jsp/home/home-header.jsp" %>
			
			<div class="panel panel-primary">
				<div class="panel panel-heading"><h3>회원가입</h3></div>
				<div class="panel panel-body">
					<h2 class="page-header">프로필 이미지 설정</h2>
					<form id="register" enctype="multipart/form-data">
					<div align="center">
						<label for=".profile_view">권장 크기 (64X64)px</label><br/>
						<img src="image/profile_view_placeholder.png" class="profile_view"> <br/><br/>
						<input type="file" name="profile_url" accept="image/*"/>
					</div>
					<br/><br/>
					<div class="page-header">
					<h2>기본 정보 입력</h2>
					</div>
					<span class="email-warn">E-mail을 정확히 입력하지 않으면 인증메일을 받을 수 없습니다.</span><br/><br/>
					<div align="center">
						<table class="table" cellpadding="0" cellspacing="0" style="width:70%;">
							<tr>
								<td><span class="register_form_label">아이디</span></td>
								<td><input type="text" name="id" class="register_form_control"/></td>
								<td align="center"><input type="button" class="btn btn-info duplicate-check" value="중복확인"/></td>
							</tr>
							<tr>
								<td><span class="register_form_label">비밀번호</span></td>
								<td><input type="password" name="password" class="register_form_control"/></td>
								<td></td>
							</tr>
							<tr>
								<td><span class="register_form_label">비밀번호 확인</span></td>
								<td><input type="password" id="password_confirm" class="register_form_control"/></td>
								<td align="center"><span class="confirm-no">불일치</span></td>
							</tr>
							<tr>
								<td><span class="register_form_label">닉네임</span></td>
								<td><input type="text" name="nickname" class="register_form_control"/></td>
								<td></td>
							</tr>
							<tr>
								<td><span class="register_form_label">이메일</span></td>
								<td><input type="text" name="email" class="register_form_control"/></td>
								<td></td>
							</tr>
							<tr>
								<td><span class="register_form_label">전화번호</span></td>
								<td><input type="text" name="phone" class="register_form_control"/></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td align="center"><input type="button" class="btn btn-success regist" value="가입하기"/></td>
							</tr>
						</table>
					</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>