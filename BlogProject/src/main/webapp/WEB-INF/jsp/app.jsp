<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="URL" value="http://ec2-35-165-223-153.us-west-2.compute.amazonaws.com/BlogProject/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Hello World</title>
	</head>
	<body>
		<h4>카카오 API 테스트</h4>
		<div align="center">
			
			<div id="kakaostory-share-button"></div>
			<a href="https://story.kakao.com/share?url=${URL}" onclick="window.open(this.href, '', 'resizable=no,status=no,location=no,toolbar=no,menubar=no,fullscreen=no,scrollbars=no,dependent=no,width=600,height=600'); return false;">카스로공유</a>
		</div>
		

		
		<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
		<script type="text/javascript">
			Kakao.init('a43018ad95cd229f7260dcd2270add32');
			Kakao.Auth.logout();
			
		</script>
	</body>
</html>