<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/include/app-header.jspf"%>
		<title>${user.nickname}님의 블로그</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="/WEB-INF/jsp/home/home-header.jsp" %>
			
			<div class="pull-left" style="width: 21%; margin-right: 50px;">
				<%@ include file="/WEB-INF/jsp/blog/blog-leftSection.jsp" %>
			</div>
			<div class="pull-left" style="width: 73%">
				<%@ include file="/WEB-INF/jsp/blog/blog-rightSection.jsp" %>				
			</div>
		</div>
		<script type="text/javascript">
			$(window).load(function(){
				var categoryView = $('.post-category');
				var postCategoryArea = $('.post-category-area');
				categoryView.each(function(){
					var currentView = $(this);
					console.log(currentView);
					var data = {
						main_category_id : $(this).attr('main_category_id'),
						sub_category_id : $(this).attr('sub_category_id')
					};

					$.ajax({
						url : '/blog/ajax/getCategory',
						type : 'post',
						data : data,
						success : function(response){
							currentView.text(response.name);
							postCategoryArea.html(response.name);
						}
					});
				});
			})	
		</script>		
	</body>
</html>