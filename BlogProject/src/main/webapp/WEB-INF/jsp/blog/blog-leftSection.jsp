<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="idRef" value="${user.id}"/>
<div class="blog-profile-box">
	<div style="width: 100%; height: 100px;">
		<img src="/blog/ajax/profileImage/${user.id}" class="bgimg">
	</div>
	<div style="margin-top: 10px;">
		<span>${user.nickname}</span><br/>
		<span>(${user.id})</span><br/>
		<p>${feature.description}</p>
		<c:choose>
			<c:when test="${sessionScope.user.id eq user.id}">
				<a href="#" class="label label-info writePostBtn">포스트 쓰기</a>
				<a href="/blog/manage" class="label label-warning manageBtn">관리</a>
				<a href="#" class="label label-success statisticBtn">통계</a>
			</c:when>
			<c:otherwise>
				<a href="#" class="label label-primary">이웃 추가</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="category-box">
	<div class="panel panel-primary">
		<div class="panel-heading clearfix">
			<div class="pull-left">
			카테고리
			</div>
			<div class="pull-right" style="padding-top: 5px;">
			<c:choose>
				<c:when test="${sessionScope.user.id eq user.id}">
					<input type="hidden" id="idRef" value="${user.id}"/>
					<span class="label label-info editCategoryBtn">Edit</span>
				</c:when>
			</c:choose>			
			</div>
		</div>
	
  		<ul class="list-group">
			<li class="list-group-item">
				<a href="/blog/${user.id}?currentPage=1">전체보기</a>
			</li>
			<c:forEach items="${category}" var="mainCategory">
				<li class="list-group-item"><a href="/blog/${user.id}?main_category_id=${mainCategory.key.id}">${mainCategory.key.name}</a>
					<c:if test="${mainCategory.value.size() != 0}">	<span class="caret clickable" data-toggle="collapse" data-target="#msarea${mainCategory.key.id}"></span></c:if>
					<div id="msarea${mainCategory.key.id}" class="collapse">
						<ul>
						<c:forEach items="${mainCategory.value}" var="subCategory">
							<li class="list-group-item"><a href="/blog/${user.id}?main_category_id=${mainCategory.key.id}&sub_category_id=${subCategory.id}">${subCategory.name}</a></li>
						</c:forEach>
						</ul>
					</div>
				</li>
			</c:forEach>				
		</ul>  		
	</div>

</div>