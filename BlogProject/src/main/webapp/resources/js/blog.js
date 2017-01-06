$(document).on('click','.categoryToggle',function(){
	var content = $('.category-box');
	var visible = content.is(':visible');
	
	if(visible)
	{
		content.hide();
	}
	else
	{
		content.show();
	}
})

$(document).on('click','.deleteComment',function(){
	var comment_id = $(this).parent().parent().parent().parent().attr('comment_id');
	var post_id = $(this).attr('post_id');
	swal({
		title : '댓글 삭제',
		text : '정말 댓글을 삭제하시겠습니까?',
		type : 'question',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '삭제',
		cancelButtonText: '취소',
		preConfirm : function(){
			return new Promise(function(resolve, reject) {
				$.ajax({
					url : $('#contextPath').val()+'/deleteComment',
					type : 'post',
					data : {id : comment_id},
					success : function(){resolve();}
				});
			});
		}
	}).then(function(){
		swal('','댓글이 삭제됐습니다.','success').then(function(){
			location.href = $('#contextPath').val()+'/postview/'+$('#user_id').val()+'/'+post_id;
		});
	}, function(){});
});

$(document).on('click', '.doEdit', function(){
	var comment_id = $(this).attr('comment_id');
	var content = $('#comment-edit-'+comment_id).val();
	
	var data = {
		id : comment_id,
		content : content
	};
	
	$.ajax({
		url : $('#contextPath').val()+'/editComment',
		type : 'post',
		data : data,
		success : function(response) {
			if(response.result==1)
			{
				swal('','댓글이 수정됐습니다.','success').then(function(){
					$('#comment-edit-'+comment_id).parent().html(content);
				});
			}
			else
			{
				
			}
		}
	})
	
	console.log(data);
});

$(document).on('click', '.editComment', function(){
	var w = $(this).parent().parent().parent().parent().width()-400;
	var comment_id = $(this).parent().parent().parent().parent().attr('comment_id');
	var content_area = $(this).parent().parent().parent().find('div').eq(2);
	var content = content_area.html();
	console.log('길이 : '+w);
	var editForm = '<textarea class="form-control" style="width: '+w+'px; margin-right: 10px;" id="comment-edit-'+comment_id+'">'+content+'</textarea>'+
				   '<input type="button" class="doEdit btn btn-primary" comment_id="'+comment_id+'" value="Edit"/>';
	
	content_area.html(editForm);	
});

$(document).on('click', '.addComment', function(){
	var btn = $(this);
	var area = $(this).parent().parent();
	console.log(area);
	var data = {
		content :  $(this).prev().val(),	
		user_id : $('#session_user_id').val(),
		post_id : $(this).attr('post_id'),
		currentPage : $('#totalPage').val()
	};
	$(this).prev().val('');
	$.ajax({
		url : $('#contextPath').val()+'/addComment',
		type : 'post',
		data : data,
		dataType : 'json',
		success : function(response) {
			if(response.status==-1)
			{
				location.href = $('#contextPath').val()+'/openLogin';
			}
			else
			{
				var comments = response.comments;
				var page = response.page;
				var post_id = btn.attr('post_id');

				area.find('div').eq(0).html('');
				
				$.each(comments, function(i, comment){
					appendComment(comment, area);
				});
				
				var paging = '<input type="hidden" id="totalPage" value="'+page.totalPage+'"/>';
				
				if(page.prevPage==page.currentPage)
				{
					paging += '<a class="disable">이전</a>';
				}
				else
				{
					paging += '<a style="cursor : pointer;" class="otherPost" prevpage="'+page.prevPage+'" action="prev" post_id="'+post_id+'">이전</a>';				
				}
				
				paging += ' | ';
				
				for(var i=page.firstPage;i<=page.lastPage;i++)
				{
					if(page.currentPage==i)
					{
						paging += '<a style="cursor : pointer;" class="otherPost" post_id="'+post_id+'" currentpage="'+i+'" post_id="'+post_id+'"><b><u>'+i+'</u></b></a> | ';						
					}
					else
					{
						paging += '<a style="cursor : pointer;" class="otherPost" post_id="'+post_id+'" currentpage="'+i+'" post_id="'+post_id+'">'+i+'</a> | ';
					}
				}
				
				if(page.nextPage==page.currentPage)
				{
					paging += '<a class="disable">다음</a>';
				}
				else
				{
					paging += '<a style="cursor : pointer;" class="otherPost" nextpage="'+page.nextPage+'" action="next" post_id="'+post_id+'">다음</a>';				
				}
				
				area.children('.comment-paging-area').html(paging);
			}
		}
	});
})

$(document).on('click', '.viewCommentBtn.openToggle', function(){
	var icon = 'glyphicon glyphicon-triangle-';
	$(this).removeClass('openToggle');
	$(this).addClass('closeToggle');
	$(this).children(0).removeClass(icon+'top');
	$(this).children(0).addClass(icon+'bottom');	
	$(this).parent().parent().next().hide();
})

function appendComment(comment, area) {
	var uid = comment.user_id;
	var suid = $('#session_user_id').val();
	var controlBtns = '<span class="label label-default clickable addSubComment">답글</span>';
	if(uid==suid)
	{
		controlBtns += ' | <span class="label label-default clickable editComment">수정</span> | <span class="label label-default clickable deleteComment" post_id="'+comment.post_id+'">삭제</span>';
	}
	
	
	var aWithImage = '<div class="comment-row" comment_id="'+comment.id+'">'+
						'<div class="pull-left" style="margin-right:15px;">'+
						'<a><img src="" style="width: 45px; height: 45px;"/></a>'+
						'</div>'+
						'<div class="clearfix comment-desc">'+
							'<div class="pull-left">'+
								'<div>'+
									'<span class="comment-nickname">작성자명</span>'+
									'<span class="comment-createAt">작성시간</span>'+
								'</div>'+
								'<div class="comment-content">'+
									'내용'+
								'</div>'+					
							'</div>'+
							'<div class="pull-right">'+
							'<span class="pull-right comment-control">'+controlBtns+'</span>'+	
							'</div>'+
						'</div>'+	
						'<hr/></div>';
	
	var item = $($.parseHTML(aWithImage));
	var nicknameView = $(item.find('div').eq(1).find('span').eq(0));
	var createAtView = $(item.find('div').eq(1).find('span').eq(1));
	var contentView = $(item.find('div').eq(4));
	$(item.find('a').eq(0)).attr('href',$('#contextPath').val()+'/'+comment.user_id);
	$(item.find('img').eq(0)).attr('src',$('#contextPath').val()+'/ajax/profileImage/'+comment.user_id);
	nicknameView.html('<a href="${contextPath}/'+comment.user_id+'"><strong>'+comment.nickname+'</strong></a>');
	createAtView.html(new Date(comment.create_at).format('yyyy.MM.dd a/p hh:mm'));				
	contentView.html(comment.content);
	
	area.find('div').eq(0).append(item);
}

function generateComment(post_id, area, currentPage) {
	var post_id = post_id
	var area = area;
	
	area.find('div').eq(0).html('');
	$.ajax({
		url : $('#contextPath').val()+'/postComment',
		type : 'post',
		data : {post_id : post_id, currentPage : currentPage},
		success : function(response) {
			var comments = response.comments;
			var page = response.page;
			
			$.each(comments, function(i, comment){
				appendComment(comment, area);
			});
			
			var paging = '<input type="hidden" id="totalPage" value="'+page.totalPage+'"/>';
			
			if(page.prevPage==currentPage)
			{
				paging += '<a class="disable">이전</a>';
			}
			else
			{
				paging += '<a style="cursor : pointer;" class="otherPost" prevpage="'+page.prevPage+'" action="prev" post_id="'+post_id+'">이전</a>';				
			}
			
			paging += ' | ';
			
			for(var i=page.firstPage;i<=page.lastPage;i++)
			{
				if(page.currentPage==i)
				{
					paging += '<a style="cursor : pointer;" class="otherPost" post_id="'+post_id+'" currentpage="'+i+'" post_id="'+post_id+'"><b><u>'+i+'</u></b></a> | ';						
				}
				else
				{
					paging += '<a style="cursor : pointer;" class="otherPost" post_id="'+post_id+'" currentpage="'+i+'" post_id="'+post_id+'">'+i+'</a> | ';
				}
			}
			
			if(page.nextPage==currentPage)
			{
				paging += '<a class="disable">다음</a>';
			}
			else
			{
				paging += '<a style="cursor : pointer;" class="otherPost" nextpage="'+page.nextPage+'" action="next" post_id="'+post_id+'">다음</a>';				
			}
			area.children('.comment-paging-area').html(paging)
		}
	});
}

$(document).on('click', '.otherPost', function(){
	var post_id = $(this).attr('post_id');
	var area = $(this).parent().parent();
	
	var currentPage = $(this).attr('currentpage');
	var prevPage = $(this).attr('prevpage');
	var nextPage = $(this).attr('nextpage');
	
	if($(this).attr('action')=='next')
	{
		generateComment(post_id, area, nextPage);
	}
	else if($(this).attr('action')=='prev')
	{
		generateComment(post_id, area, prevPage);		
	}
	else
	{
		generateComment(post_id, area, currentPage);		
	}
});

$(document).on('click', '.viewCommentBtn.closeToggle',function(){
	var post_id = $(this).attr('post_id');
	var area = $(this).parent().parent().next();
	
	generateComment(post_id, area, 1);
	
	var icon = 'glyphicon glyphicon-triangle-';
	$(this).removeClass('closeToggle');
	$(this).addClass('openToggle');
	$(this).children(0).removeClass(icon+'bottom');
	$(this).children(0).addClass(icon+'top');	
	area.show();
});

$(document).on('click','.postEditBtn',function(){
	var post_id = $(this).attr('post_id');
	var frmObj = new FormObject($('#contextPath').val()+'/openUpdatePost','post');
	frmObj.append('post_id', post_id);
	frmObj.append('user_id', $('#user_id').val());
	frmObj.submit();
});

$(document).on('click','.postDeleteBtn',function(){
	var post_id = $(this).attr('post_id');

	var mci = $(this).attr('main_category_id');
	var sci = $(this).attr('sub_category_id');
	
	var data = {
		user_id : $('#idRef').val(),
		post_id : post_id,
		main_category_id : mci,
		sub_category_id : sci
	}
	
	$.ajax({
		url:$('#contextPath').val()+'/deletePost',
		type:'post',
		data : data,
		success : function(){
			swal('','포스트가 삭제됐습니다.','success').then(function(){
				location.href = $('#contextPath').val()+'/'+$('#idRef').val()+'?main_category_id='+mci+'&sub_category_id='+sci;
			});
		}
	});
});

$(document).on('click', '.editCategoryBtn', function(){
	var id = $('#idRef').val();
	location.href=$('#contextPath').val()+'/manage/categoryInfo?blogId='+id;
})

$(document).on('click','.writePostBtn', function(){
	var id = $('#idRef').val();
	location.href = $('#contextPath').val()+'/openWritePost?blogId='+id;
});

$(document).on('click','.nextPostListBtn', function(){
	var qry_str = $(this).attr('query_string');
	var mci = valueFromQueryString(qry_str, 'main_category_id');
	var sci = valueFromQueryString(qry_str, 'sub_category_id');
	var cp = $(this).attr('next_page');
	var user_id = $('#idRef').val();
	
	var data = {
		main_category_id : mci,
		sub_category_id : sci,
		currentPage : cp,
		user_id : user_id
	};
	
	$.ajax({
		url : $('#contextPath').val()+'/ajax/getPostList',
		type : 'post',
		data : data,
		dataType: 'json',
		success : function(response) {			
			var posts = response.posts;
			var page = response.page;
			var search = response.search;
			
			var tab = $('.near-post-area tr');
			tab.remove();
			tab = $('.near-post-area');
			

			/* 인접 포스트 리스팅 */
			$.each(posts, function(index, post){
				/* Jquery로 작업하기 위한 HTML 템플릿 정의 */
				var row_template = '<tr><td></td><td></td></tr>';
				/* Jquery객체로 파싱 */
				var item = $.parseHTML(row_template);
				var titleView = $(item).find('td').eq(0);
				var dateView = $(item).find('td').eq(1);
				
				/* 좌 우 정렬시키기 */
				titleView.attr('align','left');
				dateView.attr('align','right');
				
				/* 제목에 링크 부착 */
				var icon = '';
				/* 지금 출력중인 row가 현재 게시글인 경우 */
				if(post.id==$('#post_id').val())
				{
					icon = '<span class="glyphicon glyphicon-ok"></span>';
					$(item).addClass('success');
				}
				
				titleView.html(icon+' <a href="${contextPath}/postview/'+user_id+'/'+post.id+
								'?main_category_id='+post.main_category_id+
								'&sub_category_id='+post.sub_category_id+
								'&currentPage='+page.currentPage+'">'+post.title+'</a>');
				
				/* 작성시간 Fomatting */
				var creat = new Date(post.create_at);
				dateView.text(creat.getFullYear()+'.'+(creat.getMonth()+1)+'.'+creat.getDate());
				
				tab.append($(item));
			});
			
			/* 하단 이전, 다음 버튼 처리 */
			var prevBtn = $('.prevPostListBtn');
			var nextBtn = $('.nextPostListBtn');
			
			prevBtn.removeClass('disabled');
			nextBtn.removeClass('disabled');
			
			/* 현재 페이지가 마지막 페이지인 경우 */
			if(page.currentPage==page.totalPage)
			{
				nextBtn.attr('next_page', page.currentPage);
				nextBtn.addClass('disabled');
				
				prevBtn.attr('prev_page', (page.currentPage-1));
			}
			/* 현재 페이지가 첫 페이지인 경우 */
			else if(page.currentPage==1)
			{
				nextBtn.attr('next_page', (page.currentPage+1));

				prevBtn.attr('prev_page', page.currentPage);
				prevBtn.addClass('disabled');
			}
			else
			{
				prevBtn.attr('prev_page', (page.currentPage-1));
				nextBtn.attr('next_page', (page.currentPage+1));
			}
			
		}
	});		
});

$(document).on('click','.prevPostListBtn', function(){
	var qry_str = $(this).attr('query_string');
	var mci = valueFromQueryString(qry_str, 'main_category_id');
	var sci = valueFromQueryString(qry_str, 'sub_category_id');
	var cp = $(this).attr('prev_page');
	var user_id = $('#idRef').val();
	
	var data = {
		main_category_id : mci,
		sub_category_id : sci,
		currentPage : cp,
		user_id : user_id
	};
	
	$.ajax({
		url : $('#contextPath').val()+'/ajax/getPostList',
		type : 'post',
		data : data,
		dataType: 'json',
		success : function(response) {			
			var posts = response.posts;
			var page = response.page;
			var search = response.search;
			
			var tab = $('.near-post-area tr');
			tab.remove();
			tab = $('.near-post-area');
			

			/* 인접 포스트 리스팅 */
			$.each(posts, function(index, post){
				/* Jquery로 작업하기 위한 HTML 템플릿 정의 */
				var row_template = '<tr><td></td><td></td></tr>';
				/* Jquery객체로 파싱 */
				var item = $.parseHTML(row_template);
				var titleView = $(item).find('td').eq(0);
				var dateView = $(item).find('td').eq(1);
				
				/* 좌 우 정렬시키기 */
				titleView.attr('align','left');
				dateView.attr('align','right');
				
				/* 제목에 링크 부착 */
				var icon = '';
				/* 지금 출력중인 row가 현재 게시글인 경우 */
				if(post.id==$('#post_id').val())
				{
					icon = '<span class="glyphicon glyphicon-ok"></span>';
					$(item).addClass('success');
				}

				titleView.html(icon+' <a href="${contextPath}/postview/'+user_id+'/'+post.id+
								'?main_category_id='+post.main_category_id+
								'&sub_category_id='+post.sub_category_id+
								'&currentPage='+page.currentPage+'">'+post.title+'</a>');
				
				/* 작성시간 Fomatting */
				var creat = new Date(post.create_at);
				dateView.text(creat.getFullYear()+'.'+(creat.getMonth()+1)+'.'+creat.getDate());
				
				tab.append($(item));
			});
			
			/* 하단 이전, 다음 버튼 처리 */
			var prevBtn = $('.prevPostListBtn');
			var nextBtn = $('.nextPostListBtn');

			prevBtn.removeClass('disabled');
			nextBtn.removeClass('disabled');
			
			/* 현재 페이지가 마지막 페이지인 경우 */
			if(page.currentPage==page.totalPage)
			{
				nextBtn.attr('next_page', page.currentPage);
				nextBtn.addClass('disabled');
				
				prevBtn.attr('prev_page', (page.currentPage-1));
			}
			/* 현재 페이지가 첫 페이지인 경우 */
			else if(page.currentPage==1)
			{
				nextBtn.attr('next_page', (page.currentPage+1));

				prevBtn.attr('prev_page', page.currentPage);
				prevBtn.addClass('disabled');
			}
			else
			{
				prevBtn.attr('prev_page', (page.currentPage-1));
				nextBtn.attr('next_page', (page.currentPage+1));
			}
			
		}
	});
});