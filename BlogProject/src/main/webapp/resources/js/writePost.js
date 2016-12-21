$(document).ready(function(){
	$.snote.setOnImageUpload(function(files){
		var tempForm = new FormData();
		tempForm.append('image', files[0]);
		$.ajax({
			url : '/blog/ajax/send',
			type : 'post',
			data : tempForm,
			contentType : false,
			processData: false,
			success : function(response){
				console.log(response.save_url);
				var url = '/blog/ajax/temp/'+response.save_url;
				$.snote.image(url, function($image){
					$image.css('width', '100%');
					$image.css('height','auto');
				});
			}
		})
	});	
	$.snote.initalize('#editor');
});

$(document).on('click', '.postPreviewBtn', function(){
	var title = $('input[name*="title"]').val();

	var content = $('#editor').summernote('code');

	if(!isValidData(title,content))return;

	content = replaceAll(content,'"','&quot;');

	var form = $('<form action="postPreview" method="post" target="w"></form>');
	
	window.open('','w','width=1024,height=768');
	window.open('','w');
	
	var categoryItem = $('#category option:selected');
	
	appendForm(form, 'categoryName',categoryItem.val());
	appendForm(form,'title', title);
	appendForm(form,'content',content);
	appendForm(form,'blogId',$('#idRef').val());
	form.submit();
});

$(document).on('click','.postWriteBtn', function(){
	var user_id = $('#idRef').val();
	var title = $('input[name*="title"]').val();
	var content = $('#editor').summernote('code');
	
	if(!isValidData(title,content))return;
	
	var categoryItem = $('#category option:selected');
	var main_category_id;
	var sub_category_id;
	
	if(categoryItem.attr('type')=='main')
	{
		main_category_id = categoryItem.attr('key');
		sub_category_id = 0;
	}
	else
	{
		main_category_id = categoryItem.attr('main_category_id');
		sub_category_id = categoryItem.attr('key');
	}
	/* 미리보기는 쌍따옴표 치환이 필요하지만 DB저장시엔 필요없음.. */
	//content = replaceAll(content,'"','&quot;');
	console.log(content);
	var data = {
		user_id : user_id,
		title : title,
		content : content,
		main_category_id : main_category_id,
		sub_category_id : sub_category_id
	};
	
	swal({
		title : '포스트를 작성하시겠습니까?',
		showCancelButton: true,
		cancelButtonText: '취소',
		confirmButtonText: '추가',
		showLoaderOnConfirm: true,
		preConfirm : function(){
			return new Promise(function(resolve, reject){
				$.ajax({
					url : 'writePost',
					type : 'post',
					data : data,
					success : function(){
						resolve();
					},
					error : function() {
						swal('','서버와 통신이 원할하지 않습니다.','error');
					}
				});
			});
		}
	}).then(function(){
		swal('','포스트가 작성됐습니다.','success').then(function(){
			location.href = '/blog/'+user_id;
		});
	});
	
});

function isValidData(title, content) {
	title = title.trim();
	if(title.length==0)
	{
		swal('','제목을 입력해주세요!','error');
		return false;
	}
	
	if(content=='<p><br></p>')
	{
		swal('','본문을 입력해주세요','error');
		return false;
	}
	return true;
}

