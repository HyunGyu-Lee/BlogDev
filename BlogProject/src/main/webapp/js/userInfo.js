function modifyAjax(obj) {
	var name = obj;
	var value = $('.'+obj).children('input:eq(0)').val()
	
	if(isEmptyString(value))
	{
		swal('','변경할 값을 입력하세요','error');
		return;
	}
	
	if(name=='email')
	{
		if(!emailValidate(value))
		{
			swal('','올바른 이메일 형식이 아닙니다.','error');
			return;
		}		
	}
	
	$.ajax({
		url : 'ajax/modifyField',
		type : 'post',
		data : name +'='+ value,
		success : function(response) {
			swal('','변경 되었습니다.','success').then(function(){
				location.href = 'userInfo';
			});
		}
	});
}


$(function(){
	
	$('.password_view').click(function(){
		var auth_view = $('.auth_view');
		if(auth_view.is(':visible')==true)
		{
			auth_view.hide();
		}
		else
		{
			auth_view.show();
		}
	});
	
	$('.password-auth-btn').click(function(){
		$.ajax({
			url : 'ajax/passwordAuth',
			type : 'post',
			data : {password : $('input[name*="password"]').val()},
			dataType : 'json',
			success : function(response) {
				if(response.result == true)
				{
					$('.password_view').html(response.password);
					$('.auth_view').hide();
				}
				else
				{
					$('.notValidPassword').show();
				}
			}
		})
	})
	
	$('.edit').click(function(){
		var modifyTarget = $(this).parent().parent().children('td:eq(1)');

		if(modifyTarget.is('td')==true&&modifyTarget.children('input:eq(1)').is('input')==false)
		{
			var el = '<input type="text" value="'+modifyTarget.text()+'"/>';
			el += '<input type="button" onclick="modifyAjax(\''+modifyTarget.attr('class')+'\')" value="변경"/>';
			el += '<input type="hidden" value="'+modifyTarget.text()+'"/>';
			modifyTarget.html(el);
			$(this).html('취소');
		}
		else
		{
			modifyTarget.html(modifyTarget.children('input:eq(2)').val());
			$(this).html('수정');
		}		
	})
	
	$(document).on('change', '#profile_url_normal', function(){
		setImageFromFile(this,'.profile_view_normal');
		
		$('#edit_profile').ajaxSubmit({
			url : 'ajax/editProfilePhoto',
			type : 'post',
			success : function(){
				swal('','프로필 이미지가 변경되었습니다.','success');
			}
		});
	});
});
