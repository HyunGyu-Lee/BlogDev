<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Blog - 메인</title>
	</head>
	<body>
		<div class="">
			<%@ include file="/WEB-INF/jsp/home/home-leftSection.jsp" %>
		</div>
		
		<ul>
			<c:forEach begin="0" end="4" var="i">
				<li>${subjects.get(i).name}</li>
			</c:forEach>
			<li>모두보기</li>
		</ul>
		<div>
			<c:forEach items="${subjects}" var="item">
				${item.name}
			</c:forEach>		
		</div>
		
	</body>
</html>