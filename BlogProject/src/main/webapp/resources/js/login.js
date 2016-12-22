$(function(){
	
	$('.login-button').click(function(){
		var id = $('input[name="id"]').val();
		var password = $('input[name="password"]').val();
		console.log($('input[name="id"]').val());
		if(!isEmptyString(id)&&!isEmptyString(password))
		{
			$.ajax({
				url : '/blog/ajax/login',
				type : 'post',
				data : $('#login').serialize(),
				dataType : 'json',
				success : function(response) {
					if(response.result==true)
					{
						if(response.redirectUri==null)
						{
							location.href = '/blog/';
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