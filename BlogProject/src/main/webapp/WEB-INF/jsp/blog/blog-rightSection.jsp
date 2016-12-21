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
					<span class="post-category" main_category_id = "${post.main_category_id}" sub_category_id="${post.sub_category_id}">
						<c:choose>
							<c:when test="${empty post.sub_category_name}">
								${post.main_category_name}
							</c:when>
							<c:otherwise>
								${post.sub_category_name}
							</c:otherwise>
						</c:choose>
					</span>
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
						<a href="/blog/postview/${user.id}/${post.id}?sub_category_id=${post.sub_category_id}" class="label label-info">www.publicblog.com/blog/postview/${user.id}/${post.id}</a>
					</div>
					<div>
						${post.content}
					</div>					
				</div>
			</div>
		</c:forEach>
		<div class="well" align="center">
		<c:if test="${search.main_category_id != 0}">
			<c:set var="queryString" value="?main_category_id=${search.main_category_id}"/>
		</c:if>
		<c:if test="${search.sub_category_id != 0}">
			<c:set var="queryString" value="${queryString}&sub_category_id=${search.sub_category_id}"/>
		</c:if>
		
			<a href="/blog/${user.id}?${queryString}&currentPage=${page.prevPage}">prev</a> |
				<c:forEach begin="${page.firstPage}" end="${page.lastPage}" var="pageIdx">
					<a href="/blog/${user.id}?${queryString}&currentPage=${pageIdx}">${pageIdx}</a> | 
				</c:forEach>
			<a href="/blog/${user.id}?${queryString}&currentPage=${page.nextPage}">next</a>
		</div>
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="pull-left"><strong>${post.title} </strong></span>&nbsp;&nbsp;| 
				<span class="post-category" main_category_id = "${post.main_category_id}" sub_category_id="${post.sub_category_id}">
						<c:choose>
							<c:when test="${empty post.sub_category_name}">
								${post.main_category_name}
							</c:when>
							<c:otherwise>
								${post.sub_category_name}
							</c:otherwise>
						</c:choose>
					</span>
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
		<div class="panel panel-default"><div class="panel-body">
			<strong>
				<span class="post-category-area">
					<c:choose>
						<c:when test="${empty post.sub_category_name}">'${post.main_category_name}'</c:when>
						<c:otherwise>'${post.sub_category_name}'</c:otherwise>
					</c:choose>
				</span>
			</strong> 카테고리의 다른 글<br/><br/>
			<table class="table">
				<c:forEach items="${footer}" var="footItem">
					<c:if test="${post.id == footItem.id}"><tr class="success"></c:if>
					<c:if test="${post.id != footItem.id}"><tr></c:if>
						<td align="left">
						<c:if test="${post.id == footItem.id}"><span class="glyphicon glyphicon-ok"></span></c:if>
							${footItem.title}
						</td>
						<td align="right">
							<fmt:formatDate value="${footItem.create_at}" pattern="yyyy.MM.dd"/>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div align="center">
				이전 | 다음
			</div>
		</div></div>
	</c:otherwise>
	</c:choose>	
</div>


