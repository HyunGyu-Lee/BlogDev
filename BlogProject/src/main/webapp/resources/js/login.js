$(function(){
	
	$('.login-button').click(function(){
		var id = $(this).parent().parent().parent().find('input[name="id"]').val();
		var password = $(this).parent().parent().parent().find('input[name="password"]').val();
		var redirectUri = $('input[name=redirectUri]').val();
		if(!isEmptyString(id)&&!isEmptyString(password))
		{
			$.ajax({
				url : '/blog/ajax/login',
				type : 'post',
				data : {"id" : id, "password" : password, "redirectUri" : redirectUri},
				dataType : 'json',
				success : function(response) {
					if(response.result==true)
					{
						if(response.redirectUri=='/blog')
						{
							window.location = '/blog';
						}
						else
						{
							location.href = response.redirectUri;
						}
					}
					else
					{
						swal('','ID 또는 비밀번호를 확인하세요','error');
					}
				}
			})
		}
		else
		{
			swal('','ID, 비밀번호를 모두 입력해주세요','error');
		}
	});
	
});