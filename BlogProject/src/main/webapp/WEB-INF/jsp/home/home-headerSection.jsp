<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:if test="${not empty sessionScope.user.auth && sessionScope.user.auth eq 'false'}">
<div class="alert alert-danger" role="alert">
	인증되지 않은 계정입니다. 인증하시려면 <a href="userInfo" class="alert-link">여기</a>를 클릭하세요
</div>
</c:if>
<script>
	$(document).ready(function(){
		var type = '${type}';
		var search_by = '${search_by}';
		if(type=='')type = 'home';
		
		$('.'+type).css('background-color','#607D8B');
		$('.'+type).css('color','white');
		$('.'+type).css('border-radius','8px');
		
		if(search_by!='')
		{
			$('.selectpicker').selectpicker('val',search_by);
			$('input[name="keyword"]').val('${keyword}');
		}
	});
</script>
<div style="margin-top: 15px; margin-bottom: 15px;">
	<form class="form-inline" role="search">
		<input type="hidden" name="type" value="search"/>
		<div class="form-group">
			<select class="selectpicker" data-width="fit" name="search_by" style="">
				<option value="post">포스트</option>
				<option value="blog">블로그</option>
				<option value="nickname">닉네임</option>
			</select>
	         <input type="text" class="form-control" name="keyword" placeholder="Search">
	       </div>
	       <button type="submit" class="btn btn-primary">검색</button>
	</form>
</div>
<nav class="navbar navbar-default">
	<div class="continaer-fluid">
		<div class="navbar-header">
			<a href="${contextPath}" class="navbar-brand home">블로그 홈</a>
		</div>
		
		<div class="collapse navbar-collapse">
		<ul class="nav navbar-nav">
			<c:forEach begin="0" end="4" var="i">
				<li><a href="${contextPath}?subject_id=${subjects.get(i).id}">${subjects.get(i).name}</a></li>
			</c:forEach>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">모든주제 <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<c:forEach items="${subjects}" var="item">
					<li><a href="${contextPath}?subject_id=${item.id}" id="${item.id}">${item.name}</a></li>
					</c:forEach>	
				</ul>
			</li>
			
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">파워블로그</a></li>
			<li><a href="#">공식블로그</a></li>
			<li><a href="#">이달의블로그</a></li>
			<li><a href="#">블로그 빅데이터</a></li>
		</ul>		
		</div>
	</div>
</nav>
