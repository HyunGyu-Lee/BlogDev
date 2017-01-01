$(function(){
	$(".login").click(function(){
		location.href = "/blog/openLogin";
	});
	
	$(".register").click(function(){
		location.href = "/blog/openRegister";
	});
	
	$(".home").click(function(){
		location.href = "/blog/";
	});

	$('.username').click(function(){
		location.href = '/blog/userInfo';
	});
	
	$(document).on('click', '.openMyBlog', function(){
		var id = $('#idRef').val();
		location. href = '/blog/'+id;
	})

})