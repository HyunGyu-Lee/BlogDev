<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div>
	<div class="panel panel-primary">
		<div class="panel-heading"><h1>회원 정보</h1></div>
	  	<div class="panel-body" align="center">
	    	<table class="table">
	    		<tr>
	    			<td rowspan="6" align="center">
	    				<img src="ajax/profileImage/${user.id}" class="profile_view_normal"><br/><br/><br/>
	    				<form id="edit_profile" enctype="multipart/form-data">
	    					<input type="file" id="profile_url_normal" name="profile" value="변경"/>
	    				</form>
	    			</td>
	    			<td>ID</td>
	    			<td>${user.id}</td>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td>닉네임</td>
	    			<td class="nickname">${user.nickname}</td>
	    			<td><span class="label label-success edit">수정</span></td>
	    		</tr>
	    		<tr>
	    			<td>비밀번호</td>
	    			<td>
	    				<span class="password_view label label-danger">비밀번호 확인을 위해서는 비밀번호를 한번 더 입력해주세요</span>
	    				<div class="auth_view form-inline">
	    					<br/>
	    					<input type="password" class="form-control" name="password"/>
	    					<input type="button" class="btn password-auth-btn" value="인증"/>
	    					<span class="notValidPassword">비밀번호가 일치하지 않습니다!</span>
	    				</div>
	    			</td>
	    			<td></td>
	    		</tr>
	    		<tr>
	    			<td>이메일</td>
	    			<td class="email">${user.email}</td>
	    			<td><span class="label label-success edit">수정</span></td>
	    		</tr>
	    		<tr>
	    			<td>전화번호</td>
	    			<td class="phone">${user.phone}</td>
	    			<td style=""><span class="label label-success edit">수정</span></td>
	    		</tr>
	    		<tr>
	    			<td>가입일</td>
	    			<td><span><fmt:formatDate value="${user.create_at}" pattern="yyyy년 MM월 dd일 HH시mm분ss초"/></span></td>
	    			<td></td>
	    		</tr>
	    	</table>
	  	</div>
	  	<div class="panel-footer">
	  		Panel footer
	  	</div>
	</div>
</div>