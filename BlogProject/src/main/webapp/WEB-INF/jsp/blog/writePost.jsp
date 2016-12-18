<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/include/app-header.jspf"%>
		<title>글쓰기</title>
	</head>
	<body>
		<div class="container">
			<%@ include file="/WEB-INF/jsp/home/home-header.jsp"%>
			<div class="alert alert-info" role="alert" style="width: 35%;">
				주의! 이미지는 최대 680px의 넓이로 제한됩니다.
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				  <span aria-hidden="true">&times;</span>
				</button>
			</div>
			<input type="hidden" id="idRef" value="${id}"/>
			<div class="editor-top">
				<span class="category-selector">
					<select style="width: 130px;" id="category">
						<c:forEach items="${category}" var="mainCategory">
							<option type="main" key="${mainCategory.key.id}">${mainCategory.key.name}</option>
							<c:forEach items="${mainCategory.value}" var="subCategory">
								<option type="sub" main_category_id = "${subCategory.main_category_id}" key="${subCategory.id}">--${subCategory.name}</option>
							</c:forEach>
						</c:forEach>
					</select>
				</span>
				<input type="text" name="title" style="width: 995px"/>
			</div>			
			<div id="editor"></div>
			
			<input type="button" value="미리보기" class="postPreviewBtn"/>
			<input type="button" value="저장" class="postWriteBtn"/>
		</div>
	</body>
</html>