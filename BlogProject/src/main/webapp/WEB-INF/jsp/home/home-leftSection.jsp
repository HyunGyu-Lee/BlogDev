<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty sessionScope.user.auth && sessionScope.user.auth eq 'false'}">
<div class="alert alert-danger" role="alert">
	인증되지 않은 계정입니다. 인증하시려면 <a href="userInfo" class="alert-link">여기</a>를 클릭하세요
</div>
</c:if>

<div>
	메인 영역
</div>
