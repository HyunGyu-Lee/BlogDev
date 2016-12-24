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
			
			<input type="hidden" id="idRef" value="${id}"/>
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
					<input type="text" name="title" style="width: 78%; border-radius: 5px;"/>
				</div>			
				<div id="editor"></div>
				<div align="right">
					<input type="button" value="미리보기" class="btn btn-warning postPreviewBtn"/>
					<input type="button" value=" 저장 " class="btn btn-info postWriteBtn"/>
				</div>
			</div>
		</div>		
	</body>
</html>