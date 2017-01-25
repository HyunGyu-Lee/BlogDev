<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Wrapper</title>
		<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">	
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.2.1/sweetalert2.min.css">
		<link rel="stylesheet" href="<c:url value="/resources/css/search-box.css"/>">
		<style>
			body {
				background-image: url('http://source.unsplash.com/category/nature/1600x900');
				background-size: cover;
				min-height: 100vh;
				margin: 0;
				display: flex;
				justify-content: center;
				align-items: center;
			}
			#in-center-area {
				display: block;
				align-items: center;
			}
			h1 {
				background-color : rgba(255,255,255,0.3);
				padding:0.3rem;
				font-family: 'Josefin Sans', sans-serif;
				font-size: 200;
			}
		</style>
	</head>
	<body>
		
		<div id="in-center-area">
			<div class="search-wrapper">
			    <div class="input-holder">
			        <input type="text" class="search-input" placeholder="Type to search" />
			        <button type="button" class="search-icon" onclick="searchToggle(this, event);"><span></span></button>
			    </div>
			    <span class="close" onclick="searchToggle(this, event);"></span>
			</div>
		</div>

    <script src="https://cdn.jsdelivr.net/sweetalert2/6.2.1/sweetalert2.min.js"></script>
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/search-box.js"/>"></script>
	<script type="text/javascript">
	    function logic(input) {
	    	location.href = '<c:url value="/?type=search&search_by=post&keyword='+input+'"/>';
	    }
    </script> 
	</body>
</html>