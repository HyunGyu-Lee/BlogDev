<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		$(document).on('click','.manage-blog',function(){
			location.href = '${contextPath}/manage?type=typography&user_id=${user.id}';
		});
	});
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="idRef" value="${user.id}"/>
<div class="mobile-blog-info-box">
	<img src="${contextPath}/blogBgImage/${user.id}" class="bgimg">
	<div class="overary">
		<div class="feature">
			<h2><b>${feature.title}</b></h2>
		</div>
		<img src="${contextPath}/ajax/profileImage/${user.id}" class="profile_view"/> <span class="user-nick">${user.nickname}</span>
		<ul class="control">
		<li><button type="button" class="outline-btn categoryToggle"><span class="glyphicon glyphicon-list"></span></button></li>
		<c:choose>
		<c:when test="${sessionScope.user.id eq user.id}">
				<li><button type="button" class="outline-btn manage-blog"><span class="glyphicon glyphicon-cog"></span></button></li>
				<li><button type="button" class="outline-btn"><span class="glyphicon glyphicon-stats"></span></button></li>
		</c:when>
		<c:otherwise>		
			<li><button type="button" class="outline-btn"><span class="glyphicon glyphicon-plus"></span></button></li>
		</c:otherwise>
		</c:choose>		
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
					<c:if test="${mainCategory.value.size() != 0}">	<span class="clickable" data-toggle="collapse" data-target="#msarea${mainCategory.key.id}">&nbsp;&nbsp;&nbsp;▼</span></c:if>
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