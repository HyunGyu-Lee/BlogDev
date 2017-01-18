<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<!-- 이걸 추가하면 PC, Mobile에 따라 부트스트랩이 크기를 자동으로 조절함 -->
		<meta name="viewport" content="width=100%; initial-scale=1; maximum-scale=1; minimum-scale=1; user-scalable=no;"/>
		<script type="text/javascript" src="<c:url value="/resources/js/html2canvas.js"/>"></script>
	</head>
	<body>
		<h2>템플릿 테이블</h2>
		<div id="template">
			<table class="table table-striped">
				<tr>
					<th>일련번호</th>
					<th>저자</th>
					<th>제목</th>
				</tr>
				<tr>
					<td>1</td>
					<td>김뚝딱</td>
					<td>뚝딱이의 하루</td>
				</tr>
				<tr>
					<td>2</td>
					<td>박뚝딱</td>
					<td>뚝딱이처럼 살자</td>
				</tr>
			</table>
		</div>
		<hr>
		<h2>HTML 직접 적기</h2>
		<div style="margin-bottom: 100px;">
			<textarea class="form-control" id="html-source"><h1 id="z">키야야약</h1></textarea>
			<div id="generated" style="display: none;"></div>
		</div>
		<div align="center" style="margin-bottom: 100px;">
			<p>버튼을 클릭하시면 아래에 이미지가 랜더링됩니다.</p>
			<button type="button" class="test btn btn-primary">테이블 랜더링 테스트</button>
			<button type="button" class="test btn btn-primary" su="true">직접 적은 HTML 랜더링 하기</button>
		</div>
		
		<div id="rendering-area" align="center" style="display: none;">
			<h3>랜더링된 이미지</h3>
			<img id="render-target"/>
		</div>
		
		<script type="text/javascript">
		
		$(document).ready(function(){
			
			$(document).on('click','.test',function(){
				var template;
				
				if($(this).attr('su')=='true')
				{
					var generated = $('#generated');
					generated.show();
					generated.html($('#html-source').val());
					template = generated;
				}
				else
				{
					template = $('#template');					
				}
				
				console.log(template);
				
				var settings = {
					onrendered : function(canvas) {
						var imgData = canvas.toDataURL();
						$('#render-target').attr('src', imgData);
						$('#rendering-area').show();
						if(generated!='undefined')generated.hide();
					}
				}
				
				html2canvas(template, settings);
			})
			
		});
		</script>
	</body>
</html>