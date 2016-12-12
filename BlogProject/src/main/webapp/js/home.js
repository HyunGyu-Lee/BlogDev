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

	$('.username').click(function(){
		location.href = 'userInfo';
	});
	
	$(document).on('click', '.openMyBlog', function(){
		var id = $('#idRef').val();
		location. href = '/blog/'+id;
	})
})