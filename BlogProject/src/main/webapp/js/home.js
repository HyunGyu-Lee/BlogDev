$(function(){
	$(".login").click(function(){
		location.href = "openLogin";
	});
	
	$(".register").click(function(){
		location.href = "openRegister";
	});
	
	$(".home").click(function(){
		location.href = "/blog/";
	});
	
	$('.logout').click(function(){
		$.ajax({
			url : 'ajax/logout',
			type : 'get',
			data : {id : $('#id').val()},
			success : function() {
				alert('로그아웃 되었습니다.');
				location.href = '/blog/';
			}
		});
	});
	
	$('.username').click(function(){
		location.href = 'userInfo';
	});
})