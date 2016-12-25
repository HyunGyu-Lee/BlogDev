<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>파일파일</title>
	</head>
	<body>
		<h4>컨트롤!</h4>
		<form id="sendForm">
			<input type="file" name="uploadImage" class="form-control"/><br/><br/>
			<input type="button" class="send form-control" value="이미지보내기"/><br/><br/>
		</form>

		<h3>업로드한 사진 보이는곳!</h3>
		<img id="preview" width="100%" height="auto;"/>
		
		<script type="text/javascript">
			$(document).on('change', 'input[name*=uploadImage]', function(){
				var file = $(this)[0].files[0];
				
				var fr = new FileReader();
				
				fr.onload = function(e){
					$('#preview').attr('src', e.target.result);
				};
				
				fr.readAsDataURL(file);
			});
			
			$(document).on('click','.send',function(){
				$('#sendForm').ajaxSubmit({
					url : '/blog/ajax/send',
					type : 'post',
					success : function(){
						swal('♥♥♥♥','사랑해화연아~~~~','success');						
					}
				});
			});
		</script>
	</body>
</html>