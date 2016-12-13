<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="blog-profile-box row">
	<div class="col-md-12">
		<img src="ajax/profileImage/${user.id}" class="profile_view pull-left" style="margin-right: 20px;">
		<div class="info">
			${user.nickname} 님
			<!-- <a href="logout" class="label label-danger">로그아웃</a> -->
		</div>
		<span class="control">
		<c:choose>
			<c:when test="${sessionScope.user.id eq user.id}">
				<a href="#" class="label label-info writePostBtn">포스트 쓰기</a>
				<a href="#" class="label label-warning manageBtn">관리</a>
				<a href="#" class="label label-success statisticBtn">통계</a>
			</c:when>
			<c:otherwise>
				<a href="#" class="label label-primary">이웃 추가</a>
			</c:otherwise>
		</c:choose>

		</span>
	</div>
</div>

<div class="category-box row">
	<div class="panel panel-primary">
		<div class="panel-heading">
			카테고리
		</div>
		<div class="panel-body">
			<div class="pull-left">
				<c:forEach items="${category}" var="mainCategory">
					<h3>${mainCategory.key.name}</h3>
					<c:forEach items="${mainCategory.value}" var="subCategory">
						&nbsp&nbsp&nbsp&nbsp&nbsp${subCategory.name}<br/>
					</c:forEach>			
				</c:forEach>
			</div>
			<div class="pull-right">
			<c:choose>
				<c:when test="${sessionScope.user.id eq user.id}">
					<input type="hidden" id="idRef" value="${user.id}"/>
					<span class="label label-info editCategoryBtn">Edit</span>
				</c:when>
			</c:choose>
			</div>
  		</div>
	</div>

</div>