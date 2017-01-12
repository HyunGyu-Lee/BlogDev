<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Blog - 회원가입</title>
	</head>
	<body>
		<div class="clearfix">
			<div class="pull-left">
				<h2>프로필 이미지 설정</h2>
			</div>
			<div class="pull-right" style="padding-top: 15px;">
				<a href="<c:url value="/"/>" class="btn btn-danger">돌아가기</a>
			</div>
		</div>
		<hr>
		<form id="register" enctype="multipart/form-data">
		<div align="center">
			<label for=".profile_view">권장 크기 (64X64)px</label><br/>
			<img src="${contextPath}/resources/image/profile_view_placeholder.png" class="profile_view"> <br/><br/>
			<input type="file" name="profile_url" accept="image/*"/>
		</div>
		<hr>
		<h2>기본 정보 입력</h2>
		<hr>
		<span class="email-warn">E-mail을 정확히 입력하지 않으면 인증메일을 받을 수 없습니다.</span><br/><br/>
		<div align="center">
			<table class="table" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span class="register_form_label">아이디</span><br/>
						<input type="text" name="reg_id" class="register_form_control"/>
					</td>
					<td align="center" width="30%;"><input type="button" class="btn btn-info duplicate-check" value="중복확인"/></td>
				</tr>
				<tr>
					<td>
						<span class="register_form_label">비밀번호</span><br/>
						<input type="password" name="reg_password" class="register_form_control"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<span class="register_form_label">비밀번호 확인</span><br/>
						<input type="password" id="password_confirm" class="register_form_control"/>
					</td>
					<td align="center"><span class="confirm-no">불일치</span></td>
				</tr>
				<tr>
					<td>
						<span class="register_form_label">닉네임</span><br/>
						<input type="text" name="nickname" class="register_form_control"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<span class="register_form_label">이메일</span><br/>
						<input type="text" name="email" class="register_form_control"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<span class="register_form_label">전화번호</span><br/>
						<input type="text" name="phone" class="register_form_control"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align="center"><input type="button" class="btn btn-success regist" value="가입하기"/></td>
				</tr>
			</table>
		</div>
		</form>
	</body>
</html>