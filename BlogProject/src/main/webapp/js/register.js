var duplicate_check = false;
var password_confirm_check = false;

function isEmpty(name) {
	if($('input[name*="'+name+'"').val().trim().length==0)
	{
		return true;
	}
	return false;
}

$(function(){
	/* 가입하기 버튼 클릭시 */
	$('.btn.regist').click(function(){
		if(isEmpty('id')) {
			alert('아이디를 확인하세요');
			return;
		}
		if(isEmpty('password')) {
			alert('비밀번호를 확인하세요');
			return;
		}
		if(isEmpty('nickname')) {
			alert('닉네임을 확인하세요');
			return;
		}
		if(isEmpty('email')) {
			alert('이메일을 확인하세요');
			return;
		}
		if(isEmpty('phone')) {
			alert('전화번호를 확인하세요');
			return;
		}
		if(isEmpty('profile_url'))
		{
			alert('이미지 파일을 꼭 지정해주세요.');
			return;
		}
		
		if(!duplicate_check) {
			alert('아이디 중복확인을 해주세요');
			return;
		}
		if(!password_confirm_check) {
			alert('비밀번호 확인을 완료해주세요!');
			return;
		}
		
		var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
		if(!regExp.test($('input[name*="email"]').val()))
		{
			alert('올바른 이메일형식이 아닙니다.');
			return;
		}
		
		$('#register').ajaxSubmit({
			url : 'ajax/register',
			type : 'post',
			enctype : 'multipart/form-data',
			success : function(response) {
				alert('회원가입이 완료됐습니다. 이메일 인증을 완료하시면 로그인 할 수 있습니다.');
				location.href = '/blog/';
			},
			error : function(error) {
				alert('서버와 통신이 원할하지 않습니다.');
			}			
		});
		
//		var form_data = new FormData($("#register")[0]);
//		
//		form_data.append("profile_url", $('input[name*="profile_url"]')[0].files[0]);
//		
//		$.ajax({
//			url : 'ajax/register',
//			type : 'post',
//			enctype : 'multipart/form-data',
//			data : form_data,
//			processData: false,
//            contentType: false,
//			success : function(response) {
//				console.log(response);
//			},
//			error : function(error) {
//				console.log(error);
//			}			
//		});
	});
	
	/* 아이디 중복체크 클릭시 */
	$('.duplicate-check').click(function(){
		
		var id = $('input[name*="id"').val();
		id = id.trim();
		
		if(id.length==0)
		{
			alert('공백 제외 한 글자 이상 입력하세요.');
			return;
		}
		
		$.ajax({
			url : 'ajax/duplicate_check',
			type : 'post',
			data : {'id' :id},
			dataType : 'json',
			success : function(response) {
				if(response.result == true)
				{
					alert('사용할 수 있는 아이디입니다.');
					duplicate_check = true;
				}
				else
				{
					alert('이미 사용중인 아이디입니다.');
				}
			}			
		})
	});	
	
	$('#password_confirm').on("input",function(){
		var test = $('input[name*="password"').val();
		var target = $('#password_confirm').val();

		if(test==target)
		{
			var el = $('.confirm-no');
			el.attr('class','confirm');
			el.text('일치');
			password_confirm_check = true;
		}
		else
		{
			var el = $('.confirm');
			el.attr('class','confirm-no');
			el.text('불일치');
			password_confirm_check = false;
		}
	});
	
	$('input[name*="profile_url"]').change(function(){
		/* 파일 크기 제한 걸어야함 */
		if(this.files[0].size>5000000) {
			alert('파일 크기는 5MB이하로 해주세요.');
			return;
		}
		setImageFromFile(this, '.profile_view');
	});
	
	$('input[name*="profile_url_normal"]').change(function(){
		/* 파일 크기 제한 걸어야함 */
		if(this.files[0].size>5000000) {
			alert('파일 크기는 5MB이하로 해주세요.');
			return;
		}
		setImageFromFile(this, '.profile_view_normal');
	});
})

