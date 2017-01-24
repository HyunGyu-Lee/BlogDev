<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="host" value="http://publicblog.xyz"/>
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
					<span class="pull-left"><strong>${post.title}</strong></span>&nbsp;&nbsp;| 
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
						<a href="${contextPath}/postview/${user.id}/${post.id}?main_category_id=${post.main_category_id}&sub_category_id=${post.sub_category_id}&currentPage=${page.currentPage}" class="label label-info">www.publicblog.com${contextPath}/postview/${user.id}/${post.id}</a>
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
						<a class="label label-success clickable" data-toggle="collapse" href=".share-box" aria-expanded="false" aria-controls="collapseExample">공유하기</a>
						
						<c:if test="${sessionScope.user.id eq user.id}">
							<a href="#" class="label label-warning postEditBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">수정</a>
							<a href="#" class="label label-danger postDeleteBtn" post_id="${post.id}" main_category_id="${post.main_category_id}" sub_category_id="${post.sub_category_id}">삭제</a>
							<span class="label label-default">설정</span>
						</c:if>	
						
						<div class="share-box collapse" style="margin-top: 15px;">
							<div class="well" share_uri="${host}${contextPath}/postview/${user.id}/${post.id}?main_category_id=${post.main_category_id}&sub_category_id=${post.sub_category_id}&currentPage=${page.currentPage}" text="${post.title}">
								<span class="clickable kakao-share">
									  <img src="${contextPath}/resources/image/kakao_story_share.png">
								</span>
							</div>
						</div>
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
			<a href="${contextPath}/${user.id}?${queryString}currentPage=${page.prevPage}">이전</a>
			</c:if>
			|
			<!-- 페이지 버튼 5개씩 출력 -->
			<c:forEach begin="${page.firstPage}" end="${page.lastPage}" var="pageIdx">
				<c:if test="${pageIdx == page.currentPage}">
				<a href="${contextPath}/${user.id}?${queryString}currentPage=${pageIdx}"><b><u>${pageIdx}</u></b></a> | 
				</c:if>
				<c:if test="${pageIdx != page.currentPage}">
				<a href="${contextPath}/${user.id}?${queryString}currentPage=${pageIdx}">${pageIdx}</a> | 
				</c:if>
			</c:forEach>
				
			<!-- 다음 버튼 -->
			<c:if test="${page.currentPage==page.nextPage}">
				<a class="disabled">다음</a>			
			</c:if>
			<c:if test="${page.currentPage!=page.nextPage}">
				<a href="${contextPath}/${user.id}?${queryString}currentPage=${page.nextPage}">다음</a>
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
					<a href="${contextPath}/postview/${user.id}/${post.id}${queryString}&currentPage=${page.currentPage}" class="label label-info">www.publicblog.com${contextPath}/postview/${user.id}/${post.id}</a>
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
							<a href="${contextPath}/postview/${user.id}/${footItem.id}${queryString}&currentPage=${page.currentPage}">${footItem.title}</a>
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
<div id="USER_INFO_POPOVER_TEMPLATE" style="display: none;">
	<div class="clearfix" style="width: 400px; margin-left: 10px; margin-right: 10px; margin-top: 10px;">
		<div class="pull-left" style="margin-right: 10px; margin-bottom: 3px;">
			<img class="NIMAGE" src="" style="width: 64px; height: 64px;">
		</div>
		<div class="pull-left">
			<p class="NCONTENT">본문</p>
		</div>
	</div>
	<div align="right" style="margin-left: 10px; margin-right: 10px; margin-bottom: 10px;">
		<div class="btn-group" role="group">
			<button type="button" class="btn btn-default NREL" style="width: 80px; font-size: 11px;">이웃</button>
			<button type="button" class="btn btn-default NBLOG" style="width: 80px; font-size: 11px;">블로그</button>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	$(document).on('click','.NBLOG',function(){
		location.href='${contextPath}/'+$(this).attr('r_u_i');
	});
	
	$(document).on('click','.applyNeighbor',function(){
		var user_id = $(this).attr('user_id');
		var rel_user_id = $(this).attr('rel_user_id');
		if(user_id=='') {
			swal('','로그인이 필요한 서비스입니다.','info');
			return;
		}
		$.ajax({
			url : '${contextPath}/getFeature',
			type : 'post',
			data : {user_id:rel_user_id},
			success : function(response){
				var feature = response.feature;
				console.dir(feature);
				var typo = $('<div>').addClass('clearfix');
				var left = $('<div>').addClass('pull-left').append('<img src="${contextPath}/ajax/profileImage/'+feature.user_id+'" style="width:70px;height:70px;"/>').css('width','20%');
				var right = $('<div>').addClass('pull-left').append('관심분야 : '+feature.subject_name).append('<h5>'+feature.description+'</h5>');
				typo.append(left);
				typo.append(right);
				typo = $('<div>').append(typo).append('<br/><h4><strong>'+feature.nickname+'</strong>님께 인사말을 건네세요!</h4>');
				swal({
					title : feature.title,
					html : typo,
					input : 'textarea',
					inputValue :'우리 서로 이웃해요~',
					imageUrl : '${contextPath}/blogBgImage/'+feature.user_id,
					confirmButtonText : '신청',
					showCloseButton : true,
					showLoaderOnConfirm:true,
					preConfirm : function(textarea) {
						return new Promise(function(resolve, reject){
							$.ajax({
								url : '/apply',
								type : 'post',
								data : {user_id:user_id,rel_user_id:rel_user_id,apply_msg:textarea},
								success : function(response){
									resolve(response.code);
								}
							});
						});
					}
				}).then(function(code){
					if(code==1)swal('','이웃신청이 완료됐습니다.','success');
					else swal('','이미 이웃이거나 신청하셨습니다.','info');
					
				},function(){});
			}
		})
			
		var template;
	});
	
	$(document).on('mouseenter','[data-toggle="popover-user-info"]',function(){
		$('[data-toggle="popover-user-info"]').popover('destroy');
		var popover = $(this);
		var getInfo = function(rel_user_id) {
			new Promise(function(resolve, reject){
				$.ajax({
					url : '/getUserDetail',
					type:'get',
					data:{rel_user_id:rel_user_id},
					success:function(response){
						resolve(response);
					}
				});
			}).then(function(response){
				/* Ajax통신을 통해 user와 rel_user의 관계를 확인 */
				/* 관계가 없다면 X 
				      관계가 1이라면 수락대기중
				      관계가 2이라면 x아이콘 거절 빨간배경
				      관계가 3이라면 v아이콘 이웃 초록배경
				*/
				popover.popover({
					html:true,
					title:'<div class="clearfix"><div class="pull-left"><strong>'+response.feature.nickname+'</strong>님 정보</div><div class="pull-right clickable"><span class="glyphicon glyphicon-remove" onclick="$(&quot;.popover&quot).popover(&quot;hide&quot;);"></span></div></div>',
					trigger:'manual',
					container:'body',
					delay: { "show": 1500, "hide": 1000 },
					content:function(){
						var content = $('#USER_INFO_POPOVER_TEMPLATE');
						content.find('.NCONTENT').html(response.feature.description);
						content.find('.NIMAGE').attr('src','${contextPath}/ajax/profileImage/'+response.feature.user_id);
						content.find('.NBLOG').attr('r_u_i',response.feature.user_id);

						var item = content.find('.NREL');
						item.removeClass('btn-default');
						item.removeClass('btn-info');
						item.removeClass('btn-warning');
						item.removeClass('btn-danger');
						item.removeClass('btn-success');
						item.removeClass('btn-primary');
						if(response.rel_state==0)
						{
							content.find('.NREL').html('<span class="glyphicon glyphicon-plus"></span> 이웃추가');
							item.attr('user_id','${sessionScope.user.id}');
							item.attr('rel_user_id',response.feature.user_id);
							item.addClass('btn-info');
							item.addClass('applyNeighbor');
						}
						else if(response.rel_state==1)
						{
							item.html('<span class="glyphicon glyphicon-option-horizontal"></span> 수락대기');
							item.addClass('btn-warning');
						}
						else if(response.rel_state==2)
						{
							content.find('.NREL').html('<span class="glyphicon glyphicon-remove"></span> 거절됨');
							item.addClass('btn-danger');
						}
						else if(response.rel_state==3)
						{
							content.find('.NREL').html('<span class="glyphicon glyphicon-ok"></span> 이웃');
							item.addClass('btn-success');
						}
						return content.html();
					}
				});
				
				popover.popover('show');
			});
		};
		getInfo($(this).attr('rel_user_id'));
	})
	
});
</script>


