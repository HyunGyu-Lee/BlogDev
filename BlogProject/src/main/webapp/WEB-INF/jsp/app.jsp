<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hello World</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/chartist.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/chartist-plugin-tooltip.css"/>"/>
</head>
<body>
	<div align="center">
		<div style="float: left;">
			<div style="width: 500px;">
				<div class="ct-chart ct-perfect-fourth"></div>
			</div>
		</div>
		<div style="float: right;">
			<div style="width: 500px;">
				<div class="ct-chart2 ct-perfect-fourth"></div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/chartist.min.js"/>"></script>
<script src="<c:url value="/resources/js/chartist-plugin-tooltip.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$.ajax({
			url : '/sample',
			type : 'get',
			success : function(response) {
				var days = new Array();
				var metas = new Array();
				/* 데이터 변환 */
				$.each(response.vos, function(idx, item){
					days[idx] = item.date;
					metas[idx] = {meta:(item.date+'일'),value:item.count};
				})
				
				/* 데이터 설정 */
				var data = {
					labels : days,
					series : [
						metas
					]
				};
				/* 옵션 설정 */
				var options = {
						low : 0,
						lineSmooth:false,
						plugins: [
							Chartist.plugins.tooltip()
						]
				};
				/* 차트 그림 */
				new Chartist.Line('.ct-chart', data, options);
			}
		})
		
		$.ajax({
			url : '/sample',
			type : 'get',
			success : function(response) {
				var days = new Array();
				var cnts = new Array();
					
				$.each(response.vos, function(idx, item){
					days[idx] = item.date;
					cnts[idx] = item.count;
				})
				
				var data = {
					labels : days,
					series : [
						cnts
					]
				};
				
				new Chartist.Bar('.ct-chart2', data);
			}
		})
	})
</script>
</body>
</html>