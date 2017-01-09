<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Blog - 에러</title>
	</head>
	<body>
		<div align="center" styl.e="margin-bottom: 20px;">
			<img src="${contextPath}/resources/image/error.jpg" style="margin-top : 20px;">
			<h3>문제가 발생했습니다!</h3>
			문제가 발생할 수 있는 요인은 다음과 같습니다. <br/><br/>
			<ul style="list-style: none; margin: 0px; padding: 0px;">
				<li style="color: red;">올바르지 않은 접근 경로로 요청을 한 경우</li>
				<li style="color: red;">요청을 수행할 권한이 없는 경우</li>
				<li style="color: red;">서버 내부에서 장애가 발생한 경우</li>
			</ul><br/><br/>
			이 페이지가 계속해서 보이신다면 <strong>gusrb0808@naver.com</strong>으로 문의해주시기 바랍니다.			
			<br/><br/>
			<a href="${contextPath}">홈으로 돌아가기</a>
		</div>
	</body>
</html>