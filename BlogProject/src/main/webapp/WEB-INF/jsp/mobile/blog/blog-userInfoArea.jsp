<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="idRef" value="${user.id}"/>
<c:choose>
<c:when test="${not empty posts}">
<div class="mobile-blog-info-box">
	<img src="/blog/blogBgImage/${user.id}" class="bgimg">
	<div class="overary">
		<div class="feature">
			<h2><b>${feature.title}</b></h2>
		</div>
		<img src="/blog/ajax/profileImage/${user.id}" class="profile_view"/> <span class="user-nick">${user.nickname}</span>
		<ul class="control">
			<li><button type="button" class="outline-btn"><span class="glyphicon glyphicon-cog"></span></button></li>
			<li><button type="button" class="outline-btn categoryToggle"><span class="glyphicon glyphicon-list"></span></button></li>
			<li><button type="button" class="outline-btn"><span class="glyphicon glyphicon-stats"></span></button></li>
		</ul>
	</div>
</div>
<hr/>
<div class="feature-description">
	<strong><i>Description.</i></strong><br/>
	${feature.description}
</div>
<hr/>
<div class="category-box" style="display: none;">
	<div class="panel panel-primary">
		<div class="panel-heading">
			카테고리
		</div>
		<div class="clearfix">
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
</c:when>
<c:otherwise>
<div class="category-box">
	<div class="panel panel-primary">
		<div class="panel-heading">
			카테고리
		</div>
		<div class="clearfix">
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
</c:otherwise>
</c:choose>