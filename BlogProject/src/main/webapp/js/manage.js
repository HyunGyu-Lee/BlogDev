$(function(){
	
	$(document).on('click','.category-item',function(){
		$('.category-item').removeClass('highlight');
		$(this).addClass('highlight');
	})
	
	$(document).on('click','.category-item.main',function(){
		$('input[name*="name"]').val($(this).text());
		$('input[name*="id"]').val($(this).attr('key'));
		$('input[name*="type"]').val('main');
	});
	
	$(document).on('click','.category-item.sub',function(){
		$('input[name*="name"]').val($(this).text());
		$('input[name*="id"]').val($(this).attr('key'));
		$('input[name*="type"]').val('sub');
	});
	
	$(document).on('click', '.categoryEditBtn', function(){
		var dataForm = $('.category-data');
		var emptyFlag = false;
		
		dataForm.find('input[type!="hidden"]').each(function(){
			if(!$(this).val())emptyFlag = true;
		});

		var cnt = 0;
		$('.category-item').each(function(){
			if($(this).hasClass('highlight')) {
				cnt = cnt + 1;
			}
		});
		
		if(emptyFlag||cnt!=1) {
			swal('','카테고리를 선택해주세요!','warning');
			return;
		}
		
		var data = dataForm.serialize();

		$.ajax({
			url : 'editCategoryName',
			type : 'post',
			data : data,
			success : function() {
				swal('','변경되었습니다.','success').then(function(){
					location.href = 'categoryInfo?blogId='+$('#idRef').val();
				});
			}
		});	
	});
})