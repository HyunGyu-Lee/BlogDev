<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="clearfix">
	<div class="pull-left">
		<h4><strong>블로그 정보</strong></h4>	
	</div>
	<div class="pull-right">
		<input type="button" class="btn btn-primary" value="저장" id="saveTypography"/>
	</div>
</div>

<table class="table" style="table-layout: fixed;">
	<tr>
		<td width="30%;">제목</td>
		<td><input type="text" class="form-control" value="${feature.title}" name="edit_feature_title" style="font-size: 12px;"/></td>
	</tr>
	<tr>
		<td>소개글</td>
		<td><textarea class="form-control" name="edit_feature_description" style="font-size: 12px;">${feature.description}</textarea></td>
	</tr>
	<tr>
		<td>블로그 커버 사진</td>
		<td align="center">
			<img src="${contextPath}/blogBgImage/${feature.user_id}" id="coverPreview" alt="블로그 커버 이미지를 등록해주세요" style="width: 100%; height: auto;"/>
			<br><br>
			<input type="button" value="등록" id="filePopup" class="form-control"/>
		</td>
	</tr>
	<tr>
		<td>내 블로그 주제</td>
		<td>
			<c:forEach items="${subject}" var="item">
				<div class="radio">
					<label>
					<input type="radio" name="edit_subject" value="${item.id}"><span class="edit_subject_name${item.id}">${item.name}</span>
					</label>
				</div>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="button" class="btn btn-primary" value="저장" id="saveTypography"/>
		</td>
	</tr>
</table>
<script>
	$(document).ready(function(){
		/* 주제 선택 처리 */
		$('input:radio[name="edit_subject"][value="${feature.subject_id}"]').prop('checked', true);
		
		/* 커버 이미지  선택 버튼 클릭시*/
		$(document).on('click','#filePopup',function(){
			swal({
				  title: '이미지를 선택하세요',
				  input: 'file',
				  inputAttributes: {
				    accept: 'image/*'
				  }
				}).then(function (file) {
						var frmData = new FormData();
						frmData.append("cover", file);
						frmData.append("user_id", '${user.id}');
						$.ajax({
							url : '${contextPath}/updateCoverImage',
							type : 'post',
							processData: false,
							contentType: false,
							enctype:'multipart/form-data',
							data : frmData,
							success : function(response) {
								swal('변경됐습니다.');
							}
						});
						var reader = new FileReader
				  		reader.onload = function (e) {
				    		$('#coverPreview').attr('src', e.target.result);
				  		}
				  		reader.readAsDataURL(file)
					});
		});
		/* 저장 클릭시 */
		$(document).on('click','#saveTypography',function(){
			var data = {
				title : $('input:text[name="edit_feature_title"]').val(),
				description : $('textarea[name="edit_feature_description"]').val(),
				subject_id : $('input:radio[name="edit_subject"]:checked').val(),
				user_id : '${feature.user_id}'
			}
			
			var template = '<div class="panel panel-primary"><div class="panel-heading" align="left">아래 정보를 확인하세요!</div><table class="table table-striped"><tr><td>제목</td><td>'+data.title+'</td></tr>'+
			   '<tr><td>소개글</td><td>'+data.description+'</td></tr>'+
			   '<tr><td>주제</td><td>'+$('.edit_subject_name'+data.subject_id).html()+'</td></tr></table></div>';
			
			swal({
				title : '블로그 정보 수정',
				html : template,
				showCancelButton: true,
				confirmButtonText: '저장',
				cancelButtonText : '취소',
				showLoaderOnConfirm: true,
				preConfirm : function() {
					return new Promise(function(resolve, reject){
						$.ajax({
							url : '${contextPath}/updateBlogFeature',
							type : 'post',
							data : data,
							success: function() {resolve();},
							error : function(e) {reject();}
						});
					});
				}
			}).then(function(){
				swal('','수정됐습니다','success').then(function(){
					location.href = location.href;
				});
			}, function(){});
			
		});
	});
</script>