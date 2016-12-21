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
			swal('','아이디를 확인하세요','error');
			return;
		}
		if(isEmpty('password')) {
			swal('','비밀번호를 확인하세요','error');
			return;
		}
		if(isEmpty('nickname')) {
			swal('','닉네임을 확인하세요','error');
			return;
		}
		if(isEmpty('email')) {
			swal('','이메일을 확인하세요','error');
			return;
		}
		if(isEmpty('phone')) {
			swal('','전화번호를 확인하세요','error');
			return;
		}
		if(isEmpty('profile_url'))
		{
			swal('','이미지 파일을 꼭 지정해주세요.','error');
			return;
		}
		
		if(!duplicate_check) {
			swal('','아이디 중복확인을 해주세요','error');
			return;
		}
		if(!password_confirm_check) {
			swal('','비밀번호 확인을 완료해주세요!','error');
			return;
		}
		
		if(!emailValidate($('input[name*="email"]').val()))
		{
			swal('','올바른 이메일형식이 아닙니다.','error');
			return;
		}
		
		swal({
			title: '회원가입',
			text: "입력하신 정보로 회원 가입하시겠습니까?",
			type: 'question',
			showLoaderOnConfirm:true,
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '가입',
			preConfirm:function(){
				return new Promise(function(resolve, reject){
					console.log('통신시작');
					$('#register').ajaxSubmit({
						url : 'ajax/register',
						type : 'post',
						enctype : 'multipart/form-data',
						success : function(response) {
							console.log('통신성공');
							resolve();
						},
						error : function(error) {
							swal('','서버와 통신이 원할하지 않습니다.','error');
						}
					});
				})
			}
		}).then(function () {
			console.log('성공적');
			swal('회원가입 완료','회원가입이 완료됐습니다. 이메일 인증을 완료하시면 로그인 할 수 있습니다.','success').then(function(){
				location.href = '/blog/';
			})
		})

	});
	
	/* 아이디 중복체크 클릭시 */
	$('.duplicate-check').click(function() {
		var id = $('input[name*="id"').val();
		id = id.trim();
		
		if(id.length==0)
		{
			swal('','공백 제외 한 글자 이상 입력하세요.','warning');
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
					console.log('중복체크버튼클릭');
					swal('','사용할 수 있는 아이디입니다.','success');
					duplicate_check = true;
				}
				else
				{
					swal('','이미 사용중인 아이디입니다.','error');
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

