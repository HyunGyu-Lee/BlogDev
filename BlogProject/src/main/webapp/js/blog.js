$(document).on('click', '.editCategoryBtn', function(){
	var id = $('#idRef').val();
	location.href='manage/categoryInfo?blogId='+id;
})

$(document).on('click','.writePostBtn', function(){
	var id = $('#idRef').val();
	location.href = 'openWritePost?blogId='+id;
});
