<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="pull-left" style="width: 17%; background-color: #eeeeee; padding: 5px; border-radius: 3px; margin-right: 25px;">
		<ul class="nav nav-pills nav-stacked">
			<li role="presentation"><a href="#">조회수</a></li>
			<li role="presentation" class="active"><a href="#">방문자수</a></li>
		</ul>
	</div>
	<div class="pull-left" style="width: 80%;">
		<div style="font-size: 20px; color: #585858;" class="clearfix">
			<div class="pull-left" style="padding-top: 4px;">
				<span class="clickable"><span class="glyphicon glyphicon-chevron-left"></span></span>
				<span style="font-weight: 600;" class="today"></span>
				<span class="clickable"><span class="glyphicon glyphicon-chevron-right"></span></span>
			</div>
			<div class="pull-right" style=" width: 50%;">
				<div class="form-group pull-left" style="width: 50%;">
	                <div class='input-group date' id='picker'>
	                	<input type='text' class="form-control" id="sd"/>
	                	<span class="input-group-addon">
	                		<span class="glyphicon glyphicon-calendar"></span>
	                	</span>
	                </div>
	            </div>
	            <div class="pull-right">
	            	<button type="button" class="btn btn-defalut">일간</button>
		            <button type="button" class="btn btn-defalut">주간</button>
		            <button type="button" class="btn btn-defalut">월간</button>
	            </div>
			</div>
		</div>
		
		<div style=" width: 100%; height: 3px; background-color: #eeeeee;"></div>
		
		<h4>방문횟수</h4>
		<span style="font-size: 50px;">0</span>
		
		<div style="width: 100%;">
			<div class="ct-chart ct-major-twelfth"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){ 
	$('#picker').datetimepicker();
	$('.today').html(moment().format("YYYY.MM.DD"));
	req({now : 'true', gap : 'day'});
})
function req(data) {
	$.ajax({
		url : '/sample',
		type : 'get',
		data : data,
		success : function(response) {
			var days = new Array();
			var metas = new Array();
			/* 데이터 변환 */
			$.each(response.vos, function(idx, item){
				days[idx] = item.date+'일';
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
}
</script>