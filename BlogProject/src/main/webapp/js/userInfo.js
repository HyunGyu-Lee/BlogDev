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
	
});