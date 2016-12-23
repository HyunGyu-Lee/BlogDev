<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<%@ include file="/WEB-INF/include/app-header.jspf" %>
	</head>
	<body>
		
		<input type="button" class="btn" value="Ajax예제"/>
		
		<div id="area" style="width: 500px; height: 500px; background-color: red;">
			AREA
		</div>
		
		<script type="text/javascript">
			
			$('.btn').click(function(){
				$('#area').hide();
				
			});
		</script>		
	</body>
</html>