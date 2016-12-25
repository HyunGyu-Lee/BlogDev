<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="idRef" value="${user.id}"/>
<div class="blog-profile-box">
	<div class="clearfix">
		<c:url value="/ajax/profileImage/${user.id}" var="IMAGE"/>
		<img src="${IMAGE}" class="profile_view pull-left" style="margin-right: 20px;">
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

<div class="category-box">
	<div class="panel panel-primary">
		<div class="panel-heading">
			카테고리
		</div>
		<div class="panel-body">
			<div class="pull-left">
			<ul>
				<li><a href="/blog/${user.id}?currentPage=1">전체보기</a></li>
				<c:forEach items="${category}" var="mainCategory">
					<li><a href="/blog/${user.id}?main_category_id=${mainCategory.key.id}">${mainCategory.key.name}</a>
					<ul>
					<c:forEach items="${mainCategory.value}" var="subCategory">
						<li><a href="/blog/${user.id}?main_category_id=${mainCategory.key.id}&sub_category_id=${subCategory.id}">${subCategory.name}</a></li>
					</c:forEach>
					</ul>
					</li>
				</c:forEach>				
			</ul>
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