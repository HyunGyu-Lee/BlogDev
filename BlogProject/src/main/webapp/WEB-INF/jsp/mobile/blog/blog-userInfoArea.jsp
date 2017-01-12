<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		Kakao.init('a43018ad95cd229f7260dcd2270add32');
		
		$(document).on('click','.manage-blog',function(){
			location.href = '${contextPath}/manage?type=typography&user_id=${user.id}';
		});
	});
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="idRef" value="${user.id}"/>

<c:if test="${empty feature.bgimg and sessionScope.user.id eq user.id}">
	<div style="margin-top: 20px; margin-bottom:10px; width: 100%;">
		<div class="alert alert-info" role="alert">
			처음 오셨나요? <br/>
			<a href="${contextPath}/manage?type=typography&user_id=${user.id}" class="alert-link">관리</a>화면애서 블로그 커버이미지를 설정해보세요<br/><br/>
			<a href="${contextPath}/manage?type=typography&user_id=${user.id}" class="btn btn-primary form-control">설정하러가기</a>
		</div>
		<c:if test="${sessionScope.user.id eq user.id and empty category}">
			<a href="<c:url value="manage/categoryInfo?blogId=${user.id}"/>" class="btn btn-danger form-control">첫 카테고리를 만들어보세요!</a>
		</c:if>
	</div>
</c:if>
<c:if test="${not empty feature.bgimg}">
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
</c:if>
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