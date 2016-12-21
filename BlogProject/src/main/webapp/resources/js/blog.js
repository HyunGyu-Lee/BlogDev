$(document).on('click', '.editCategoryBtn', function(){
	var id = $('#idRef').val();
	location.href='/blog/manage/categoryInfo?blogId='+id;
})

$(document).on('click','.writePostBtn', function(){
	var id = $('#idRef').val();
	location.href = '/blog/openWritePost?blogId='+id;
});
