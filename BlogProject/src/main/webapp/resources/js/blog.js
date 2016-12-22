$(document).on('click', '.editCategoryBtn', function(){
	var id = $('#idRef').val();
	location.href='/blog/manage/categoryInfo?blogId='+id;
})

$(document).on('click','.writePostBtn', function(){
	var id = $('#idRef').val();
	location.href = '/blog/openWritePost?blogId='+id;
});

$(document).on('click','.nextPostListBtn', function(){
	var qry_str = $(this).attr('query_string');
	var mci = valueFromQueryString(qry_str, 'main_category_id');
	var sci = valueFromQueryString(qry_str, 'sub_category_id');
	var cp = $(this).attr('next_page');
	
	var data = {
		main_category_id : mci,
		sub_category_id : sci,
		currentPage : $(this).attr('next_page')
	};
	
	console.log(data);
	$.ajax({
		url : '/blog/getPostList',
		type : 'post',
		data : data,
		success : function(response){
			
		},
		error : function(e) {
			console.log(e);
		}		
	});
	
	
});

$(document).on('click','.prevPostListBtn', function(){
	swal('이전');
});