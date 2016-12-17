$(function(){
	$(document).on('click', '.editCategoryBtn', function(){
		var id = $('#idRef').val();
		location.href='manage/categoryInfo?blogId='+id;
	})
	
	$(document).on('click','.writePostBtn', function(){
		var id = $('#idRef').val();
		location.href = 'openWritePost?blogId='+id;
	});
	
	$(document).ready(function(){
		var toolbar = [
			['style', ['bold', 'italic', 'underline', 'clear']],
		    ['font', ['strikethrough', 'superscript', 'subscript']],
		    ['fontsize', ['fontsize']],
		    ['color', ['color']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['table', ['table']],
		    ['insert', ['link', 'picture', 'hr']],
		    ['view', ['fullscreen', 'codeview']]
		];
		
		var setting = {
			height : 300,
			minHeight: null,
			maxHeight: null,
			focus : true,
			lang : 'ko-KR',	
			toolbar : toolbar
		};
		
		$('#editor').summernote(setting);
	});
})