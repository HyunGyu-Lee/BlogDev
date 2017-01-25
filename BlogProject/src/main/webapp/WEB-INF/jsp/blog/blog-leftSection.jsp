<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="idRef" value="${user.id}"/>
<div class="thumbnail">
	<div style="width: 100%; height: auto;">
		<img src="${contextPath}/ajax/profileImage/${user.id}" class="bgimg">
	</div>
	<div class="caption" style="background-color: #eeeeee;">
		
		<div style="margin-top: 10px;">
			<span>${user.nickname}</span><br/>
			<span>(${user.id})</span><br/>
			<p>${feature.description}</p>
			<c:choose>
				<c:when test="${sessionScope.user.id eq user.id}">
					<c:choose>
						<c:when test="${empty category}">
							<strong><a href="${contextPath}/manage/categoryInfo?blogId=${sessionScope.user.id}" style="color:red;">블로그의 첫 카테고리를 만드세요!</a></strong><br/>
						</c:when>
						<c:otherwise>
							<a href="#" class="label label-info writePostBtn">포스트 쓰기</a>					
						</c:otherwise>
					</c:choose>
					<a href="${contextPath}/manage?type=typography&user_id=${user.id}" class="label label-warning manageBtn">관리</a>
					<a href="#" class="label label-success statisticBtn">통계</a>
				</c:when>
				<c:otherwise>
					<span class="label label-primary applyNeighbor clickable" user_id="${sessionScope.user.id}" rel_user_id="${user.id}">이웃 추가</span>
				</c:otherwise>
			</c:choose>
		</div>
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
				<a href="${contextPath}/${user.id}?currentPage=1">전체보기</a>
			</li>
			<c:forEach items="${category}" var="mainCategory">
				<li class="list-group-item"><a href="${contextPath}/${user.id}?main_category_id=${mainCategory.key.id}">${mainCategory.key.name}</a>
					<c:if test="${mainCategory.value.size() != 0}">	<span class="caret clickable" data-toggle="collapse" data-target="#msarea${mainCategory.key.id}"></span></c:if>
					<div id="msarea${mainCategory.key.id}" class="collapse">
						<ul>
						<c:forEach items="${mainCategory.value}" var="subCategory">
							<li class="list-group-item"><a href="${contextPath}/${user.id}?main_category_id=${mainCategory.key.id}&sub_category_id=${subCategory.id}">${subCategory.name}</a></li>
						</c:forEach>
						</ul>
					</div>
				</li>
			</c:forEach>				
		</ul>  		
	</div>
</div>

<div class="well" style="background-color: #e1e1e1;">
	<table style="margin: 0; width: 100%" >
		<tr>
			<td width="50%"><small><strong>TODAY</strong></small></td>
			<td align="center">${visit_count.unique_today_visitor}</td>
		</tr>
		<tr>
			<td><small><strong>TOTAL</strong></small></td>
			<td align="center">${visit_count.unique_total_visitor}</td>
		</tr>
	</table>
</div>