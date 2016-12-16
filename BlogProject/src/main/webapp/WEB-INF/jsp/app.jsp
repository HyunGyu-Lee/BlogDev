<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/WEB-INF/include/app-header.jspf" %>
		<title>Insert title here</title>
	</head>
	<body>
		
		<!-- <input type="button" value="HTML코드 보기" id="showHTML"/>
	 -->
		<div id="summernote"></div>
		
		<!-- <textarea id="htmlText"></textarea>
		 -->
		<script type="text/javascript">
			$(document).ready(function(){
				var toolbar = [
					['style', ['bold', 'italic', 'underline', 'clear']],
				    ['font', ['strikethrough', 'superscript', 'subscript']],
				    ['fontsize', ['fontsize']],
				    ['color', ['color']],
				    ['para', ['ul', 'ol', 'paragraph']],
				    ['height', ['height']],
				    ['table', ['table']],
				    ['insert', ['link', 'picture', 'hr']],
				    ['view', ['fullscreen', 'codeview']],
				    ['help', ['help']]
				];
				
				var setting = {
					height : 300,
					minHeight: null,
					maxHeight: null,
					focus : true,
					lang : 'ko-KR',	
					toolbar : toolbar
				};
				
				$('#summernote').summernote(setting);
			});
			
			$(document).on('click','#showHTML',function(){
				var markUp = $('#summernote').summernote('code');
				$('#htmlText').val(markUp);
			});
		</script>
	</body>
</html>