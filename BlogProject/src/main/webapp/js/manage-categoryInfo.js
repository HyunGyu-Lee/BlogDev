function Category(item) {
	this.id = item.attr('key');
	this.category_order = item.attr('category_order');
}

Category.prototype = {
	getId : function(){
		return this.id;
	},
	getCategoryOrder : function(){
		return this.category_order;
	}
}

Category.prototype.construct = Category;

function isSelectCategory() {
	var cnt = 0;

	$('.category-item').each(function(){
		if($(this).hasClass('highlight')) {
			cnt = cnt + 1;
		}
	});
	
	if(cnt!=1)return false;
	
	return true;
}

function getItemType(item) {
	if(item.hasClass('main'))
	{
		return 'main';
	}
	else if(item.hasClass('sub'))
	{
		return 'sub';
	}
}

function getSelectedItemFromCategory(type) {
	var item;
	
	$('.category-item'+type).each(function(){
		if($(this).hasClass('highlight')) {
			item = $(this);
			return false;
		}
	});
	
	return item;
}

function getMaximum(type) {
	var cnt = 0;
	$('.category-item.'+type).each(function(){
		cnt = $(this).attr('category_order');
	});
	return cnt;
}

function getMin(type) {
	var min = 0;
	$('.category-item.'+type).each(function(){
		min = $(this).attr('category_order');
		return false;
	});
	return min;
}

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

		if(emptyFlag||!isSelectCategory()) {
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
	
	$(document).on('click','.order-up', function(){
		if(!isSelectCategory())
		{
			swal('','카테고리를 선택해주세요','warning');
			return;
		}
		
		var item = getSelectedItemFromCategory('');
		
		var switched;
		var findFlag = false;
		var type;
		
		if(item.hasClass('main'))
		{
			maximum = getMaximum('main');
			var temp = null;
			$('.category-item.main').each(function(){
				if(temp!=null)
				{
					if($(this).attr('key')==item.attr('key'))
					{
						switched = temp;
						return false;
					}
				}
				
				temp = $(this);
			});
			type = 'main';
		}
		else if(item.hasClass('sub'))
		{
			maximum = getMaximum('sub');
			var temp = null;
			$('.category-item.sub').each(function(){
				if(temp!=null)
				{
					if($(this).attr('key')==item.attr('key'))
					{
						switched = temp;
						return false;
					}
				}
				
				temp = $(this);
			});
			type = 'sub';
		}
		if(item.attr('category_order')==getMin(type))
		{
			swal('','가장 상단 카테고리입니다.','info');
			return;
		}
		
		var data = {
			targetId : item.attr('key'),
			switchedId : switched.attr('key'),
			type : type
		};
			
			$.ajax({
				url : 'editCategoryOrder',
				type : 'post',
				data : data,
				success : function() {
					location.href = 'categoryInfo?blogId='+$('#idRef').val();
				}
			});

	});
	
	$(document).on('click','.order-down', function(){
		if(!isSelectCategory())
		{
			swal('','카테고리를 선택해주세요','warning');
			return;
		}
		
 		var item = getSelectedItemFromCategory('');
		
		var switched;
		
		var type;
		
		var maximum = 0;
		var findFlag = false;
		if(item.hasClass('main'))
		{
			maximum = getMaximum('main');

			$('.category-item.main').each(function(){
				if($(this).attr('key')==item.attr('key'))
				{
					findFlag = true;
					return true;
				}
				
				if(findFlag)
				{
					switched = $(this);
					return false;
				}
			});
			type = 'main';
		}
		else if(item.hasClass('sub'))
		{
			maximum = getMaximum('sub');
			$('.category-item.sub').each(function(){
				if($(this).attr('key')==item.attr('key'))
				{
					findFlag = true;
					return true;
				}
				
				if(findFlag)
				{
					switched = $(this);
					return false;
				}
			});
			type = 'sub';
		}
		
		if(item.attr('category_order')==maximum)
		{
			swal('','가장 하단 카테고리입니다.','info');
			return;
		}

		var data = {
			targetId : item.attr('key'),
			switchedId : switched.attr('key'),
			type : type
		};
		
		$.ajax({
			url : 'editCategoryOrder',
			type : 'post',
			data : data,
			success : function() {
				location.href = 'categoryInfo?blogId='+$('#idRef').val();
			},
			error : function() {
				swal('','서버와 통신이 원할하지 않습니다.','error');
			}
		});		
	});
	
	$(document).on('click', '.category-delete', function(){
		if(!isSelectCategory())
		{
			swal('','카테고리를 선택해주세요','warning');
			return;
		}
		
		var item = getSelectedItemFromCategory('');
		
		swal({
			title : '카테고리 삭제',
			html : '<strong>'+item.html()+'</strong> 정말 삭제하시겠습니까?',
			type : 'info',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '삭제',
			cancelButtonText: '취소'
		}).then(function(){
			var type = getItemType(item);
			
			var data = {
				id : item.attr('key'),
				type : type
			};
			
			$.ajax({
				url : 'deleteCategory',
				type : 'post',
				data : data,
				success : function() {
					swal('','카테고리를 삭제했습니다.','success').then(function(){
						location.href = 'categoryInfo?blogId='+$('#idRef').val();
					});
				},
				error : function() {
					swal('','서버와 통신이 원할하지 않습니다.','error');
				}			
			});
		});
		
	});
	
	$(document).on('click','.category-main-add',function(){
		if(!isSelectCategory())
		{
			swal('','카테고리를 선택해주세요','warning');
			return;
		}
		
		var item = getSelectedItemFromCategory('');
		var type = getItemType(item);
		
		
		var findFlag = false;
		var nextItem;
		
		if(type=='main')
		{
			$('.category-item.main').each(function(){
				if($(this).attr('key')==item.attr('key'))
				{
					findFlag = true;
					return true;
				}
				
				if(findFlag)
				{
					nextItem = $(this);
					return false;
				}
			});
		}
		else
		{
			$('.category-item.sub').each(function(){
				if($(this).attr('key')==item.attr('key'))
				{
					findFlag = true;
					return true;
				}
				
				if(findFlag)
				{
					nextItem = $(this);
					return false;
				}
			});
		}

		var currentCategory = new Category(item);
		var nextCategory;
		try
		{
			nextCategory = new Category(nextItem);
		}
		catch(err)
		{
			nextCategory = null;
		}
		

		swal({
			title : '추가할 카테고리명을 입력하세요',
			input : 'text',
			showCancelButton: true,
			cancelButtonText: '취소',
			confirmButtonText: '추가',
			showLoaderOnConfirm: true,
			preConfirm : function(text) {
				return new Promise(function(resolve, reject){
					var data;
					
					if(nextCategory!=null)
					{
						data = {
								level : 'current',	
								id : currentCategory.getId(),
								next_category_id : nextCategory.getId(),
								type : type,
								name : text
							};
					}
					else
					{
						data = {
								level : 'current',	
								id : currentCategory.getId(),
								next_category_id : -999,
								type : type,
								name : text
							};
					}
					
					$.ajax({
						url : 'addCategory',
						type : 'post',
						data : data,
						success : function(){
							resolve();
						},
						error : function(){
							swal('','서버와 통신이 원할하지 않습니다.','error');
						}
					});
				})
			}
		}).then(function(){
			swal('','카테고리가 추가됐습니다.','success').then(function(){location.href='categoryInfo?blogId='+$('#idRef').val();});
		});		
	});
	
	$(document).on('click','.category-sub-add', function(){
		if(!isSelectCategory())
		{
			swal('','카테고리를 선택해주세요','warning');
			return;
		}
		
		var item = getSelectedItemFromCategory('');
		var category = new Category(item);
		var type = getItemType(item);
		
		if(type=='sub')
		{
			swal('','세부 카테고리는 2단계까지만 추가할 수 있습니다.','error');
			return;
		}
		
		swal({
			title : '추가할 카테고리명을 입력하세요',
			input : 'text',
			showCancelButton: true,
			cancelButtonText: '취소',
			confirmButtonText: '추가',
			showLoaderOnConfirm: true,
			preConfirm : function(text) {
				return new Promise(function(resolve, reject){
					var data = {
							id : category.getId(),
							name : text,
							level : 'inner'
						};
					
					$.ajax({
						url : 'addCategory',
						type : 'post',
						data : data,
						success : function(){
							resolve();
						},
						error : function(){
							swal('','서버와 통신이 원할하지 않습니다.','error');
						}
					});
				})
			}
		}).then(function(){
			swal('','카테고리가 추가됐습니다.','success').then(function(){location.href='categoryInfo?blogId='+$('#idRef').val();});
		});	
	});

})