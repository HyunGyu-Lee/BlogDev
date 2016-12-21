<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<div>
	<div class="blog-header">블로그 헤더 영역으로 활용하자</div>
	
	<c:choose>
	<c:when test="${not empty posts}">
		<c:forEach items="${posts}" var="post">
			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="pull-left"><strong>${post.title} </strong></span>&nbsp;&nbsp;| 
					<span class="post-category" main_category_id = "${post.main_category_id}" sub_category_id="${post.sub_category_id}"></span>
					<span class="pull-right">
						<fmt:formatDate value="${post.create_at}" pattern="yyyy.MM.dd. HH:mm"/>	| 
						<c:if test="${sessionScope.user.id eq user.id}">
							<a href="#" class="label label-warning">수정</a> | 
							<a href="#" class="label label-danger">삭제</a> | 
						</c:if>
						<a href="#" class="label label-primary">전용뷰어</a>
					</span>
				</div>
				<div class="panel-body post-content">
					<div align="right" style="margin-bottom: 20px;">
						<a href="/blog/postview/${user.id}/${post.id}" class="label label-info">www.publicblog.com/blog/postview/${user.id}/${post.id}</a>
					</div>
					<div>
						${post.content}
					</div>					
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="pull-left"><strong>${post.title} </strong></span>&nbsp;&nbsp;| 
				<span class="post-category" main_category_id = "${post.main_category_id}" sub_category_id="${post.sub_category_id}"></span>
				<span class="pull-right">
					<fmt:formatDate value="${post.create_at}" pattern="yyyy.MM.dd. HH:mm"/>	| 
					<c:if test="${sessionScope.user.id eq user.id}">
						<a href="#" class="label label-warning">수정</a> | 
						<a href="#" class="label label-danger">삭제</a> | 
					</c:if>
					<a href="#" class="label label-primary">전용뷰어</a>
				</span>
			</div>
			<div class="panel-body post-content">
				<div align="right" style="margin-bottom: 20px;">
					<a href="/blog/postview/${user.id}/${post.id}" class="label label-info">www.publicblog.com/blog/postview/${user.id}/${post.id}</a>
				</div>
				<div>
					${post.content}
				</div>					
			</div>
			<div class="panel-footer">
				댓글보기, 공유하기, 수정, 삭제, 설정 버튼 추가
			</div>
		</div>
		<div class="panel panel-default post-list">
			<div class="panel-body">
				'<strong><span class="post-category-area"></span></strong>' 카테고리의 다른 글<br/><br/>
				리스트를 띄워준다.
			</div>
		</div>
	</c:otherwise>
	</c:choose>	
</div>


