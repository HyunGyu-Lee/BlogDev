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
	var user_id = $('#idRef').val();
	
	var data = {
		main_category_id : mci,
		sub_category_id : sci,
		currentPage : cp,
		user_id : user_id
	};
	
	$.ajax({
		url : '/blog/ajax/getPostList',
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
				
				titleView.html(icon+' <a href="/blog/postview/'+user_id+'/'+post.id+
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
		url : '/blog/ajax/getPostList',
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

				titleView.html(icon+' <a href="/blog/postview/'+user_id+'/'+post.id+
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