$(function(){
	$(document).on('click', '.editCategoryBtn', function(){
		var id = $('#idRef').val();
		location.href='manage/categoryInfo?blogId='+id;
	})
})