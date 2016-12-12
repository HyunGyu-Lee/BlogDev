<%@page import="com.leelab.blogproject.category.sub.SubCategoryDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.leelab.blogproject.category.main.MainCategoryDTO"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="blog-profile-box row">
	<div class="col-md-12">
		<img src="ajax/profileImage/${sessionScope.user.id}" class="profile_view pull-left" style="margin-right: 20px;">
		<div class="info">
			${sessionScope.user.nickname}님 
			<a href="logout" class="label label-danger">로그아웃</a>
		</div>
		<span class="control">
			<a href="#" class="label label-info writePostBtn">포스트 쓰기</a>
			<a href="#" class="label label-warning manageBtn">관리</a>
			<a href="#" class="label label-success statisticBtn">통계</a>
		</span>
	</div>
</div>

<div class="category-box row">
	<div class="panel panel-primary">
		<div class="panel-heading">
			카테고리 영역
		</div>
		<div class="panel-body">
			<%
				HashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>> category = (HashMap<MainCategoryDTO, ArrayList<SubCategoryDTO>>)request.getAttribute("category");
				Set<MainCategoryDTO> mainCategories = category.keySet();
				for(MainCategoryDTO mainCategory : mainCategories)
				{
			%>
				<h1><%=mainCategory.getName()%></h1>
				
				<%for(SubCategoryDTO subCategory : category.get(mainCategory)) { %>
				&nbsp&nbsp&nbsp&nbsp&nbsp<%=subCategory.getName()%><br/>
				<%} %>
				
				<br/>
			<%		
				}
			%>
  		</div>
	</div>

</div>