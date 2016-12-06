<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/include/app-header.jspf"%>
		<title>Blog - 메인</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="/WEB-INF/jsp/home/home-header.jsp" %>
			
			<div class="pull-left">
				<%@ include file="/WEB-INF/jsp/home/home-leftSection.jsp" %>
			</div>
			<div class="pull-right" style="width: 25%">
				<%@ include file="/WEB-INF/jsp/home/home-rightSection.jsp" %>				
			</div>
		</div>
	</body>
</html>