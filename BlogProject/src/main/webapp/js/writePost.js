

$(document).on('click', '.postPreviewBtn', function(){
	document.charset = 'utf-8';
	
	var title = $('input[name*="title"]').val();

	var content = $('#editor').summernote('code');
	content = replaceAll(content,'"','&quot;');
	
	var form = $('<form action="postPreview" method="post" target="w"></form>');
	
	//window.open('','w','width=1024,height=768');
	window.open('','w');
	
	var categoryItem = $('#category option:selected');
	
	console.log(categoryItem.attr('type')+' - '+categoryItem.attr('key')+' - '+categoryItem.val());
	appendForm(form, 'categoryName',categoryItem.val());
	appendForm(form,'title', title);
	appendForm(form,'content',content);
	appendForm(form,'blogId',$('#idRef').val());
	form.submit();
});

