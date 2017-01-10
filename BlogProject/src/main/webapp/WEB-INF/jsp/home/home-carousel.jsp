<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- 968px X 120px -->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="margin-bottom: 20px;">
	<ol class="carousel-indicators">
		<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    	<li data-target="#carousel-example-generic" data-slide-to="1"></li>
  	</ol>

  	<!-- Wrapper for slides -->
  	<div class="carousel-inner" role="listbox">
    	<div class="item active">
    		<img src="${contextPath}/resources/image/blog_banner_1.png" alt="...">
    	</div>
    	<div class="item">
      		<img src="${contextPath}/resources/image/blog_banner_2.png" alt="...">
    	</div>
  	</div>

  	<!-- Controls -->
  	<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
  		<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    	<span class="sr-only">Previous</span>
  	</a>
  	<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    	<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    	<span class="sr-only">Next</span>
  	</a>
</div>