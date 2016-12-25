<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>포스트 수정</title>
	</head>
	<body>
			<div class="alert alert-info" role="alert" style="width: 35%;">
				주의! 이미지는 최대 680px의 넓이로 제한됩니다.
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				  <span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="editor-box">
				<div class="editor-top">
					<span class="category-selector">
						<select style="width: 20%;" id="category">
							<c:forEach items="${category}" var="mainCategory">
								<option type="main" key="${mainCategory.key.id}">${mainCategory.key.name}</option>
								<c:forEach items="${mainCategory.value}" var="subCategory">
									<option type="sub" main_category_id = "${subCategory.main_category_id}" key="${subCategory.id}">--${subCategory.name}</option>
								</c:forEach>
							</c:forEach>
						</select>
					</span>
					<input type="text" name="title" value="${post.title}" style="width: 78%; border-radius: 5px;"/>
				</div>			
				<div id="editor"></div>
				<div align="right">
				<input type="button" value="미리보기" class="btn btn-warning postPreviewBtn"/>
				<input type="button" value="수정" class="btn btn-info postUpdateBtn"/>
				</div>
			</div>
		<script type="text/javascript">
			$(document).on('click','.postUpdateBtn',function(){
				var user_id = '${post.user_id}';
				var title = $('input[name*="title"]').val();
				var content = $('#editor').summernote('code');
				
				if(!isValidData(title,content))return;
				
				var categoryItem = $('#category option:selected');
				var main_category_id;
				var sub_category_id;
				
				if(categoryItem.attr('type')=='main')
				{
					main_category_id = categoryItem.attr('key');
					sub_category_id = 0;
				}
				else
				{
					main_category_id = categoryItem.attr('main_category_id');
					sub_category_id = categoryItem.attr('key');
				}

				var data = {
					id : ${post.id},
					user_id : user_id,
					title : title,
					content : content,
					main_category_id : main_category_id,
					sub_category_id : sub_category_id
				};
				
				console.dir(data);
				$.ajax({
					url : '/blog/updatePost',
					type : 'post',
					data : data,
					success : function() {
						swal('','포스트가 수정됐습니다.','success').then(function(){
							location.href='/blog/postview/${post.user_id}/${post.id}';
						});
					}
				});
			});
			
			$(document).ready(function(){
				/* 카테고리 선택 */
				$('#category option').each(function(){
					if($(this).attr('type')=='main')
					{
						if(${post.main_category_id}==$(this).attr('key'))
						{
							$(this).attr('selected','selected');
						}
					}
					else
					{
						if(${post.sub_category_id}==$(this).attr('key'))
						{
							$(this).attr('selected','selected');
						}
					}					
				});
				
				/* 포스트 본문 */
				$('#editor').summernote('code', '${post.content}');
			});
		</script>
	</body>
</html>