function modifyAjax(obj) {
	var name = obj;
	var value = $('.'+obj).children('input:eq(0)').val()

	$.ajax({
		url : 'ajax/modifyField',
		type : 'post',
		data : name +'='+ value,
		success : function(response) {
			/* 성공시 성공 내용반영 */
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
});
