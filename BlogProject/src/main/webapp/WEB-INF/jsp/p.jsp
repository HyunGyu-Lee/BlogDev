<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<!-- 이걸 추가하면 PC, Mobile에 따라 부트스트랩이 크기를 자동으로 조절함 -->
		<meta name="viewport" content="width=100%; initial-scale=1; maximum-scale=1; minimum-scale=1; user-scalable=no;"/>
	</head>
	<body>
		<!-- container는 좌우 여백있고, container-fluid로 하면 여백 없음 -->
		<div class="container">
			
			<!-- 이름 그대로 페이지 헤더 역활 -->
			<div class="page-header">
				<h2>안녕하세요?</h2>
			</div>
			
			<!-- 본문 영역 -->
			<div class="clearfix">
				<!-- left, right로 본문영역을 나누고 각 영역 style width로 크기조절 가능 -->
				<div class="pull-left" style="width: 25%; margin-right: 10px; background-color: red;">
					메뉴
				</div>
				<div class="pull-right" style="width: 70%; background-color: blue">
					본문영역으로 사용
				</div>
			</div>			
		</div>		
	</body>
</html>