$(function(){
	$('.login').click(function(){
		location.href = $('#contextPath').val()+'/openLogin';
	});
	
	$('.register').click(function(){
		location.href = $('#contextPath').val()+'/openRegister';
	});
	
	$('.home').click(function(){
		location.href = $('#contextPath').val()+'/';
	});

	$('.username').click(function(){
		location.href = $('#contextPath').val()+'/userInfo';
	});
	
	$(document).on('click', '.openMyBlog', function(){
		var id = $('#idRef').val();
		location. href = $('#contextPath').val()+'/'+id;
	})

})