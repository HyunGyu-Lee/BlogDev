<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<div>
	<div class="blog-header">
		<div class="alert alert-info alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h6>From <span style="font-size: 15px;"> 블로그씨</span></h6>
			블로그에 스킨 기능이 1달내로 적용될 예정입니다!
		</div>
	</div>
	<input type="hidden" id="user_id" value="${user.id}"/>
	<input type="hidden" id="session_user_id" value="${sessionScope.user.id}"/>
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
							<a href="#" class="label label-warning postEditBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">수정</a> | 
							<a href="#" class="label label-danger postDeleteBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">삭제</a> | 
						</c:if>
						<a href="#" class="label label-primary">전용뷰어</a>
					</span>
				</div>
				<div class="panel-body post-content">
					<div align="right" style="margin-bottom: 20px;">
						<a href="/blog/postview/${user.id}/${post.id}?main_category_id=${post.main_category_id}&sub_category_id=${post.sub_category_id}&currentPage=${page.currentPage}" class="label label-info">www.publicblog.com/blog/postview/${user.id}/${post.id}</a>
					</div>
					<div>
					${post.content}
					</div>					
				</div>
				<div class="panel-footer">
				<div class="post-footer-control clearfix">
					<div class="pull-left">
						<span class="viewCommentBtn closeToggle" post_id="${post.id}"><span class="glyphicon glyphicon-triangle-bottom"></span> 댓글보기</span>
					</div>
					<div class="pull-right">
						<span class="label label-success clickable">공유하기</span>
						<c:if test="${sessionScope.user.id eq user.id}">
							<a href="#" class="label label-warning postEditBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">수정</a>
							<a href="#" class="label label-danger postDeleteBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">삭제</a>
							<span class="label label-default">설정</span>
						</c:if>	
					</div>
				</div>
				<div class="post-comment-area form-inline" style="display: none; padding-top: 15px;">
					<div class="comments">

					</div>
					<div class="comment-paging-area" align="center">
						
					</div>
					<div class="comment-input-box" align="center">
					<c:if test="${empty sessionScope.user}">
						댓글을 작성하시려면 로그인 해주세요!
					</c:if>
					<c:if test="${not empty sessionScope.user}">
						<textarea class="form-control comment-input-content" rows="3" name="content" style="width: 88%"></textarea>
						<input type="button" class="btn btn-primary addComment" post_id="${post.id}"value="comment"/>
					</c:if>
					</div>
				</div>
			</div>
			</div>
		</c:forEach>
		<div class="well" align="center">
		<c:if test="${search.main_category_id != 0}">
			<c:set var="queryString" value="main_category_id=${search.main_category_id}&"/>
		</c:if>
		<c:if test="${search.sub_category_id != 0}">
			<c:set var="queryString" value="${queryString}sub_category_id=${search.sub_category_id}&"/>
		</c:if>
			<!-- 이전 버튼 -->
			<c:if test="${page.currentPage==page.prevPage}">
			<a class="disabled">이전</a>
			</c:if>
			<c:if test="${page.currentPage!=page.prevPage}">
			<a href="/blog/${user.id}?${queryString}currentPage=${page.prevPage}">이전</a>
			</c:if>
			|
			<!-- 페이지 버튼 5개씩 출력 -->
			<c:forEach begin="${page.firstPage}" end="${page.lastPage}" var="pageIdx">
				<c:if test="${pageIdx == page.currentPage}">
				<a href="/blog/${user.id}?${queryString}currentPage=${pageIdx}"><b><u>${pageIdx}</u></b></a> | 
				</c:if>
				<c:if test="${pageIdx != page.currentPage}">
				<a href="/blog/${user.id}?${queryString}currentPage=${pageIdx}">${pageIdx}</a> | 
				</c:if>
			</c:forEach>
				
			<!-- 다음 버튼 -->
			<c:if test="${page.currentPage==page.nextPage}">
				<a class="disabled">다음</a>			
			</c:if>
			<c:if test="${page.currentPage!=page.nextPage}">
				<a href="/blog/${user.id}?${queryString}currentPage=${page.nextPage}">다음</a>
			</c:if>
		</div>
	</c:when>
	<c:when test="${empty post}">
		포스트가 없습니다.
	</c:when>
	<c:otherwise>
		<input type="hidden" id="post_id" value="${post.id}"/>
		<c:if test="${search.main_category_id != 0}">
			<c:set var="queryString" value="?main_category_id=${search.main_category_id}"/>
		</c:if>
		<c:if test="${search.sub_category_id != 0}">
			<c:set var="queryString" value="${queryString}&sub_category_id=${search.sub_category_id}"/>
		</c:if>
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="pull-left" id="post_title"><strong>${post.title} </strong></span>&nbsp;&nbsp;| 
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
						<a class="label label-warning postEditBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">수정</a> | 
						<a class="label label-danger postDeleteBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">삭제</a> | 
					</c:if>
					<a href="#" class="label label-primary">전용뷰어</a>
				</span>
			</div>
			<div class="panel-body post-content">
				<div align="right" style="margin-bottom: 20px;">
					<a href="/blog/postview/${user.id}/${post.id}${queryString}&currentPage=${page.currentPage}" class="label label-info">www.publicblog.com/blog/postview/${user.id}/${post.id}</a>
				</div>
				<div>
				${post.content}
				</div>					
			</div>
			<div class="panel-footer">
				<div class="post-footer-control clearfix">
					<div class="pull-left">
						<span class="viewCommentBtn closeToggle" post_id="${post.id}"><span class="glyphicon glyphicon-triangle-bottom"></span> 댓글보기</span>
					</div>
					<div class="pull-right">
						<span class="label label-success clickable">공유하기</span>
						<c:if test="${sessionScope.user.id eq user.id}">
							<a href="#" class="label label-warning postEditBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">수정</a>
							<a href="#" class="label label-danger postDeleteBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">삭제</a>
							<span class="label label-default">설정</span>
						</c:if>
					</div>
				</div>
				<div class="post-comment-area form-inline" style="display: none; padding-top: 15px;">
					<div class="comments">

					</div>
					<div class="comment-paging-area" align="center">
						
					</div>
					<div class="comment-input-box" align="center">
					<c:if test="${empty sessionScope.user}">
						댓글을 작성하시려면 로그인 해주세요!
					</c:if>
					<c:if test="${not empty sessionScope.user}">
						<textarea class="form-control comment-input-content" rows="3" name="content" style="width: 88%"></textarea>
						<input type="button" class="btn btn-primary addComment" post_id="${post.id}"value="comment"/>
					</c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
		<div class="panel-heading">
		<strong>
				<span class="post-category-area">
					<c:choose>
						<c:when test="${empty post.sub_category_name}">'${post.main_category_name}'</c:when>
						<c:otherwise>'${post.sub_category_name}'</c:otherwise>
					</c:choose>
				</span>
			</strong> 카테고리의 다른 글
		</div>
		<div style="padding-left: 10px; padding-right: 10px">
			<table class="table near-post-area">
				<c:forEach items="${footer}" var="footItem">
					<c:if test="${post.id == footItem.id}"><tr class="success"></c:if>
					<c:if test="${post.id != footItem.id}"><tr></c:if>
						<td align="left">

						<c:if test="${post.id == footItem.id}"><span class="glyphicon glyphicon-ok"></span></c:if>
							<a href="/blog/postview/${user.id}/${footItem.id}${queryString}&currentPage=${page.currentPage}">${footItem.title}</a>
						</td>
						<td align="right">
							<fmt:formatDate value="${footItem.create_at}" pattern="yyyy.MM.dd"/>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="panel-footer">
		<div align="center">
				<!-- Ajax로 요청 날려 다음 페이지 목록 받은 후 위 table을 갱신해야함 -->
				<c:if test="${page.currentPage == 1}">
					<a class="prevPostListBtn disabled" prev_page="${page.prevPage-1}">이전</a>
				</c:if>
				<c:if test="${page.currentPage != 1}">
					<a class="prevPostListBtn" prev_page="${page.currentPage-1}" query_string="${queryString}">이전</a>
				</c:if>
				|
				<c:if test="${page.currentPage == page.totalPage}">
					<a class="nextPostListBtn disabled" next_page="${page.currentPage+1}">다음</a> 
				</c:if>
				<c:if test="${page.currentPage != page.totalPage}">
					<a class="nextPostListBtn" next_page="${page.currentPage+1}" query_string="${queryString}">다음</a> 				
				</c:if>
			</div>
		</div>
		</div>
	</c:otherwise>
	</c:choose>	
</div>


