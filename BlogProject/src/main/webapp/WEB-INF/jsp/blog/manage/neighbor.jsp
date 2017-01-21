<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div>
	<div class="clearfix">
		<div class="pull-left" style="">
			<h3><span class="glyphicon glyphicon-user"></span> 내 이웃현황</h3>
		</div>
		<div class="pull-right" style="width: 20%; margin-top: 10px;">
			<select class="form-control" onchange="location = this.value;">
				<option value="${contextPath}/manage?type=neighbor&user_id=${user.id}&sub_type=we">내 이웃목록</option>
				<option value="${contextPath}/manage?type=neighbor&user_id=${user.id}&sub_type=to">내 신청목록</option>
				<option value="${contextPath}/manage?type=neighbor&user_id=${user.id}&sub_type=from">나에게 이웃요청</option>
			</select>
		</div>
	</div>
	<c:choose>
		<c:when test="${sub_type eq 'we'}">
		<table class="table table-striped">
			<tr align="center">
				<td><strong>닉네임(아이디)</strong></td>
				<td><strong>인삿말</strong></td>
				<td><strong>마지막 상태 변경 시간</strong></td>
				<td></td>
			</tr>
			<c:if test="${empty relations}">
			<tr align="center">
				<td colspan="4">
					이웃이 없습니다.
				</td>
			</tr>
			</c:if>
			<c:if test="${not empty relations}">
				<c:forEach items="${relations}" var="relation">
				<tr align="center">
					<td>
						<c:if test="${relation.tag eq 'USER'}">
							${relation.rel_user_nickname}(${relation.rel_user_id})
						</c:if>
						<c:if test="${relation.tag eq 'REL_USER'}">
							${relation.rel_user_nickname}(${relation.user_id})
						</c:if>					
					</td>
					<td>${relation.apply_msg}</td>
					<td>${relation.rel_create_at}</td>
					<td>
						<c:if test="${relation.tag eq 'USER'}">
							<a href="${contextPath}/${relation.rel_user_id}" class="btn btn-primary">블로그</a>
						</c:if>
						<c:if test="${relation.tag eq 'REL_USER'}">
							<a href="${contextPath}/${relation.user_id}" class="btn btn-primary">블로그</a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>
		</c:when>
		<c:when test="${sub_type eq 'to'}">
		<table class="table table-striped">
			<tr align="center">
				<td><strong>닉네임(아이디)</strong></td>
				<td><strong>인삿말</strong></td>
				<td><strong>마지막 상태 변경 시간</strong></td>
				<td><strong>상태</strong></td>
			</tr>
			<c:if test="${empty relations}">
				<tr align="center">
					<td colspan="4">신청한 이웃 요청이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty relations}">
				<c:forEach items="${relations}" var="relation">
				<tr align="center">
					<td>${relation.rel_user_nickname}(${relation.rel_user_id})</td>
					<td>${relation.apply_msg}</td>
					<td>${relation.rel_create_at}</td>
					<td>
						<c:choose>
							<c:when test="${relation.rel_state eq 1}">
								신청대기
							</c:when>
							<c:when test="${relation.rel_state eq 2}">
								거절
							</c:when>
							<c:when test="${relation.rel_state eq 3}">
								이웃
							</c:when>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>
		</c:when>
		<c:when test="${sub_type eq 'from'}">
		<table class="table table-striped">
			<tr align="center">
				<td><strong>닉네임(아이디)</strong></td>
				<td><strong>인삿말</strong></td>
				<td><strong>마지막 상태 변경 시간</strong></td>
				<td>상태</td>
			</tr>
			<c:if test="${empty relations}">
				<tr align="center">
					<td colspan="4">새로운 이웃 요청이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty relations}">
				<c:forEach items="${relations}" var="relation">
				<tr align="center">
					<td>${relation.rel_user_nickname}(${relation.user_id})</td>
					<td>${relation.apply_msg}</td>
					<td>${relation.rel_create_at}</td>
					<td>
						<c:choose>
							<c:when test="${relation.rel_state eq 1}">
								<button type="button" class="btn btn-primary acceptRelation" rel_user_id="${relation.user_id}" user_id="${relation.rel_user_id}">수락</button>
								<button type="button" class="btn btn-danger denyRelation" rel_user_id="${relation.user_id}" user_id="${relation.rel_user_id}">거절</button>
							</c:when>
							<c:when test="${relation.rel_state eq 2}">
								거절
							</c:when>
							<c:when test="${relation.rel_state eq 3}">
								이웃
							</c:when>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</table>
		</c:when>
	</c:choose>
</div>
<script>
	$(document).ready(function(){
		$('select option[value="${contextPath}/manage?type=neighbor&user_id=${user.id}&sub_type=${sub_type}"]').attr('selected','selected');
		
		$(document).on('click','.acceptRelation',function(){
			var user_id = $(this).attr('user_id');
			var rel_user_id = $(this).attr('rel_user_id');
			$.ajax({
				url : '${contextPath}/accept',
				type : 'post',
				data : {user_id : rel_user_id, rel_user_id:user_id},
				success : function(response) {
					if(response.code) {
						swal({
							html : '<h4>이웃목록에 추가했습니다.</h4> <a href="${contextPath}/manage?type=neighbor&user_id='+user_id+'&sub_type=we">목록보러가기</a>',
							type : 'success',
							confirmButtonText : '확인'
						}).then(function(){
							location.reload();
						});;
					}
				}
			})
		})
		
		$(document).on('click','.denyRelation',function(){
			var user_id = $(this).attr('user_id');
			var rel_user_id = $(this).attr('rel_user_id');
			$.ajax({
				url : '${contextPath}/deny',
				type : 'post',
				data : {user_id : rel_user_id, rel_user_id:user_id},
				success : function(response) {
					if(response.code) {
						swal({
							html : '<h4>거절 했습니다.</h4>',
							type : 'success',
							confirmButtonText : '확인'
						}).then(function(){
							location.reload();
						});
					}
				}
			})
		})
	})
</script>