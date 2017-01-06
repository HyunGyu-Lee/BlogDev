<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="contextPath" value="${contextPath}"/>
<div class="navigation">
	<!-- 햄버거 메뉴, 사이트 로고 -->
	<div class="pull-left">
		<ul>
			<li><a href="#main-menu" data-toggle="drawer" aria-foldedopen="false" aria-controls="main-menu"><span class="glyphicon glyphicon-menu-hamburger clickable main-menu"></span></a></li>
			<li><a href="${contextPath}" class="nav-title"><b>Public Blog</b></a></li>
		</ul>
	</div>
	<!-- 우측 메뉴 -->
	<div class="pull-right mobile-category-area">
		<span style="font-size:30px; color:white; padding-right: 5px;" class="clickable"><span class="glyphicon glyphicon-option-vertical closeToggle"></span></span>
	</div>
</div>


<div id="main-menu" class="drawer dw-xs-10 dw-sm-6 dw-md-4 fold" aria-labelledby="main-menu">
    <div class="drawer-contents">
        <div class="drawer-heading" style="background-color: #2196F3;">
           	<div style="padding-left: 10px;">
           		<h2><a style="color:white;" href="#main-menu" data-toggle="drawer" aria-foldedopen="false" aria-controls="main-menu"><span class="glyphicon glyphicon-menu-hamburger clickable"></span></a><span style="font-size: 30px; margin-left: 10px;">
	           	<a href="${contextPath}" style="color:white;"><b>Public Blog</b></a></span></h2>
           	</div>
           	<div style="padding-left:10px; padding-bottom:10px; margin-top: 15px;">
           		<%@ include file="/WEB-INF/jsp/home/login-control.jsp"%>
           	</div>
        </div>
        <div class="drawer-body">
        	<nav>
        		<ul class="">
	        		<li><span class="glyphicon glyphicon-bell"></span></li>
	        		<li><span class="glyphicon glyphicon glyphicon-user"></span></li>
	        		<li><span class="glyphicon glyphicon glyphicon-cog"></span></li>        		
	        	</ul>
        	</nav>
        </div>
        <ul class="drawer-nav">
            <li role="presentation" class="active"><a href="${contextPath}/${sessionScope.user.id}">내 블로그</a></li>
            <li role="presentation"><a href="#">컨텐츠2</a></li>
            <li role="presentation"><a href="#">컨텐츠3</a></li>
        </ul>
        <div class="drawer-footer">
            <small>&copy;Hyungyu, LEE</small>
        </div>
    </div>
</div>
<div class="container">
    <!-- content as per usual -->
</div>