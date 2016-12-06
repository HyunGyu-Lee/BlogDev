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
	
});