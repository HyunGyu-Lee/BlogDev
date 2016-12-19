<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<div>
	<div class="blog-header">블로그 헤더 영역으로 활용하자</div>
	
	<c:forEach items="${posts}" var="post">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<span class="pull-left"><strong>${post.title}</strong></span> | 
				<span class="post-category" main_category_id = "${post.main_category_id}" sub_category_id="${post.sub_category_id}"></span>
				<span class="pull-right">
					<fmt:formatDate value="${post.create_at}" pattern="yyyy.MM.dd. hh:mm"/>	| 
					<c:if test="${sessionScope.user.id eq user.id}">
						<a href="#" class="label label-warning">수정</a> | 
						<a href="#" class="label label-danger">삭제</a> | 
					</c:if>
					<a href="#" class="label label-primary">전용뷰어</a>
				</span>
			</div>
			<div class="panel-body post-content">
				${post.content}
			</div>
		</div>
	</c:forEach>	
</div>


