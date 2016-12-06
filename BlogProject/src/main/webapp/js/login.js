$(function(){
	
	$('.login-button').click(function(){
		var id = $('input[name*="id"]').val();
		var password = $('input[name*="password"]').val();
		
		if(!isEmptyString(id)&&!isEmptyString(password))
		{
			$.ajax({
				url : 'ajax/login',
				type : 'post',
				data : $('#login').serialize(),
				dataType : 'json',
				success : function(response) {
					if(response.result==true)
					{
						location.href = '/blog/';
					}
					else
					{
						alert('ID 또는 비밀번호를 확인하세요');
					}
				}
			})
		}
		else
		{
			alert('ID, 비밀번호를 모두 입력해주세요');
		}
	});
	
});