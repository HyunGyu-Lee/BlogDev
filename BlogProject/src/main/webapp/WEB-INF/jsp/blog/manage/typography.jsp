<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4><strong>블로그 정보</strong></h4>

<table class="table" style="table-layout: fixed;">
	<tr>
		<td>제목</td>
		<td><input type="text" class="form-control" value="${feature.title}" name="edit_feature_title" style="font-size: 12px;"/></td>
		<td><small>25자 이내</small> </td>
	</tr>
	<tr>
		<td>소개글</td>
		<td style="width: 40%;"><textarea class="form-control" name="edit_feature_description" style="font-size: 12px;">${feature.description}</textarea></td>
		<td style="color : gray;"><small>블로그 프로필 영역의<br/> 프로필사진 아래에 반영됩니다.</small></td>
	</tr>
	<tr>
		<td>블로그 커버 사진</td>
		<td align="center">
			<img src="/blog/blogBgImage/${feature.user_id}" id="coverPreview" alt="블로그 커버 이미지를 등록해주세요" style="width: 100%; height: auto;"/>
		</td>
		<td><small>
			<input type="button" value="등록" id="filePopup" class="btn btn-sm"/><br/><br/>
			블로그스킨에 따라 이미지가 축소/확대되어 적용됩니다.<br/>
			gif, png 이미지 사용을 권장합니다. jpg는 화질이 저하될 수 있습니다.</small>
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
		<td><small>관심 포스트 추천, 주제별 블로그 탐색 등 여러 기능에 활용됩니다.</small></td>
	</tr>
	<tr>
		<td colspan="3" align="right">
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
							url : '/blog/updateCoverImage',
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
							url : '/blog/updateBlogFeature',
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