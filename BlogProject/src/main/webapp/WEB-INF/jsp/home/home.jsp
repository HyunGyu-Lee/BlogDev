<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="host" value="http://www.publicblog.xyz"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Blog - 메인</title>
		<meta property="og:type" content="website">
		<meta property="og:title" content="Public Blog">
		<meta property="og:description" content="${empty metaDesc ? '세상과 소통하는 모두의 블로그, Public Blog' : metaDesc}">
		<meta property="og:image" content="${host}${contextPath}/resources/image/logo.png">
	</head>
	<body>
		<div>
			<%@ include file="/WEB-INF/jsp/home/home-headerSection.jsp" %>
		</div>
		
		<%@ include file="/WEB-INF/jsp/home/home-carousel.jsp" %>
		
		
		
		<c:choose>
			<c:when test="${empty type or type eq 'home'}">
				<div class="row">
					<c:choose>
						<c:when test="${not empty features}">
							<c:forEach items="${features}" var="blog">
								<c:if test="${not empty blog.bgimg}">
									<div class="col-sm-6 col-md-4">
										<div class="thumbnail" style="height: 390px;">
											<a href="${contextPath}/${blog.user_id}"><img src="${contextPath}/blogBgImage/${blog.user_id}" style="width: 100%; height: 200px; border-radius: 5px;"></a>
											<div class="caption">
												<div class="clearfix" style="margin-top : 15px; margin-bottom: 10px;">
													<div class="pull-left">
														<img src="${contextPath}/ajax/profileImage/${blog.user_id}" style="width: 56px; height: 56px; border-radius: 50em;">
													</div>
												</div>
												<div style="margin-bottom: 10px;">
													<h4>
													<span data-container="body"
														 data-html="true"
														 data-toggle="popover-user-info"
														 data-placement="top"
														 rel_user_id="${blog.user_id}" >
														<strong>${blog.nickname} (${blog.user_id})</strong>
													</span>
													</h4>
													<div></div>
												</div>
								        		<p style="color: #888" class="blog_description">${blog.description}</p>
								      		</div>
								    	</div>
							    	</div>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div align="center">
							해당 주제의 블로그가 없습니다ㅠㅠ
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${type eq 'search'}">
				<c:choose>
					<c:when test="${search_by eq 'post'}">
						<strong>포스트 검색결과 <span style="color: #ff4326;">${page.totalRecord}건</span></strong>
						<hr>
						<c:forEach items="${features}" var="item">
						<div style="height: 100px;">
							<div class="pull-left post_thumbnail" style="height: 100%;">

							</div>
							<div class="pull-left" style="width: 87%;">
								<div style="margin-bottom: 10px;">
									<a href="${contextPath}/postview/${item.user_id}/${item.post_id}">${item.post_title}</a><br/>
									<span style="font-size: 13px;"><a href="${contextPath}/${item.user_id}"><strong>${item.nickname}(${item.user_id})</strong></a> | <span class="post_preview_date">${item.create_at}</span></span>
								</div>
								<div class="post_preview_content">
									${item.content}
								</div>
							</div>
						</div>
						<hr>
						</c:forEach>
					</c:when>
					<c:when test="${search_by eq 'blog'}">
						<c:forEach items="${features}" var="item">
						<div class="jumbotron" style="background-image: url('${contextPath}/blogBgImage/${item.user_id}'); color : white;">
							<h1>${item.title}</h1>
							<p>${item.description}</p>
							<p><a class="btn btn-primary btn-lg" href="${contextPath}/${item.user_id}" role="button">구경하기</a></p>
						</div>
						</c:forEach>
					</c:when>
					<c:when test="${search_by eq 'nickname'}">
						<strong>닉네임 검색결과 <span style="color: #ff4326;">${page.totalRecord}건</span></strong>
						<table class="table">
						<c:forEach items="${features}" var="item">
						<tr class="blog-summary">
							<td style="width: 30%;"><img src="${contextPath}/ajax/profileImage/${item.user_id}" style="width: 50px; height: 50px;"></td>
							<td>${item.nickname}</td>
							<td><a href="${contextPath}/${item.user_id}">${item.title}</a></td>
						</tr>
						<tr class="blog-info" style="display: none;">
							<td><img src="${contextPath}/blogBgImage/${item.user_id}" style="width: 256px; height: 256px;"></td>
							<td colspan="2">
								<h4>● 관심분야</h4>
								<p>${item.subject_name}</p>
								<h4>● 소개글</h4>
								<p>${item.description}</p>
							</td>
						</tr>
						</c:forEach>
						</table>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
		
		<div align="center">
			<ul class="pagination">
				<c:if test="${page.prevPage == page.currentPage}">
	    		<li class="disabled">		    	
	    		</c:if>
	    		<c:if test="${page.prevPage != page.currentPage}">
    			<li>
	    		</c:if>
	    		<a href="${contextPath}${qryString}&type=${type}" aria-label="Previous">
	       			<span aria-hidden="true">&laquo;</span>
	      		</a>
		    	</li>
		    <c:forEach begin="${page.firstPage}" end="${page.lastPage}" step="1" var="i">
			    <c:if test="${page.currentPage == i}">
					<li class="active"><a href="${contextPath}${qryString}&type=${type}&currentPage=${i}">${i}</a></li>
			    </c:if>
			    <c:if test="${page.currentPage != i}">
					<li><a href="${contextPath}${qryString}&type=${type}&currentPage=${i}">${i}</a></li>
			    </c:if>
		    </c:forEach>
		    
		    <c:if test="${page.currentPage == page.totalPage}">
		    <li class="disabled">		    
		    </c:if>
			<c:if test="${page.currentPage != page.totalPage}">
			<li>
			</c:if>
		      <a href="${contextPath}${qryString}&type=${type}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</div>
		<div id="USER_INFO_POPOVER_TEMPLATE" style="display: none;">
			<div class="clearfix" style="width: 400px; margin-left: 10px; margin-right: 10px; margin-top: 10px;">
				<div class="pull-left" style="margin-right: 10px; margin-bottom: 3px;">
					<img class="NIMAGE" src="" style="width: 64px; height: 64px;">
				</div>
				<div class="pull-left">
					<p class="NCONTENT">본문</p>
				</div>
			</div>
			<div align="right" style="margin-left: 10px; margin-right: 10px; margin-bottom: 10px;">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-default NREL" style="width: 80px; font-size: 11px;">이웃</button>
					<button type="button" class="btn btn-default NBLOG" style="width: 80px; font-size: 11px;">블로그</button>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		
			$(document).ready(function(){
				$(document).on('click','.NBLOG',function(){
					location.href='${contextPath}/'+$(this).attr('r_u_i');
				});
				
				$(document).on('click','.applyNeighbor',function(){
					var user_id = $(this).attr('user_id');
					var rel_user_id = $(this).attr('rel_user_id');
					$.ajax({
						url : '${contextPath}/getFeature',
						type : 'post',
						data : {user_id:rel_user_id},
						success : function(response){
							var feature = response.feature;
							console.dir(feature);
							var typo = $('<div>').addClass('clearfix');
							var left = $('<div>').addClass('pull-left').append('<img src="${contextPath}/ajax/profileImage/'+feature.user_id+'" style="width:70px;height:70px;"/>').css('width','20%');
							var right = $('<div>').addClass('pull-left').append('관심분야 : '+feature.subject_name).append('<h5>'+feature.description+'</h5>');
							typo.append(left);
							typo.append(right);
							typo = $('<div>').append(typo).append('<br/><h4><strong>'+feature.nickname+'</strong>님께 인사말을 건네세요!</h4>');
							swal({
								title : feature.title,
								html : typo,
								input : 'textarea',
								inputValue :'우리 서로 이웃해요~',
								imageUrl : '${contextPath}/blogBgImage/'+feature.user_id,
								confirmButtonText : '신청',
								showCloseButton : true,
								showLoaderOnConfirm:true,
								preConfirm : function(textarea) {
									return new Promise(function(resolve, reject){
										$.ajax({
											url : '/apply',
											type : 'post',
											data : {user_id:user_id,rel_user_id:rel_user_id,apply_msg:textarea},
											success : function(response){resolve(response.code)}
										});
									});
								}
							}).then(function(code){
								if(code==1)swal('','이웃신청이 완료됐습니다.','success');
								else swal('','이미 이웃입니다.','info');
							},function(){});
						}
					})
						
					var template;
				});
				
				$('[data-toggle="popover-user-info"]').on({
					mouseenter : function(){
						$('[data-toggle="popover-user-info"]').popover('destroy');
						var popover = $(this);
						var getInfo = function(rel_user_id) {
							new Promise(function(resolve, reject){
								$.ajax({
									url : '/getUserDetail',
									type:'get',
									data:{rel_user_id:rel_user_id},
									success:function(response){
										resolve(response);
									}
								});
							}).then(function(response){
								/* Ajax통신을 통해 user와 rel_user의 관계를 확인 */
								/* 관계가 없다면 X 
								      관계가 1이라면 수락대기중
								      관계가 2이라면 x아이콘 거절 빨간배경
								      관계가 3이라면 v아이콘 이웃 초록배경
								*/
								popover.popover({
									html:true,
									title:'<div class="clearfix"><div class="pull-left"><strong>'+response.feature.nickname+'</strong>님 정보</div><div class="pull-right clickable"><span class="glyphicon glyphicon-remove" onclick="$(&quot;.popover&quot).popover(&quot;hide&quot;);"></span></div></div>',
									trigger:'manual',
									container:'body',
									delay: { "show": 1500, "hide": 1000 },
									content:function(){
										var content = $('#USER_INFO_POPOVER_TEMPLATE');
										content.find('.NCONTENT').html(response.feature.description);
										content.find('.NIMAGE').attr('src','${contextPath}/ajax/profileImage/'+response.feature.user_id);
										content.find('.NBLOG').attr('r_u_i',response.feature.user_id);

										var item = content.find('.NREL');
										item.removeClass('btn-default');
										item.removeClass('btn-info');
										item.removeClass('btn-warning');
										item.removeClass('btn-danger');
										item.removeClass('btn-success');
										item.removeClass('btn-primary');
										if(response.rel_state==0)
										{
											content.find('.NREL').html('<span class="glyphicon glyphicon-plus"></span> 이웃추가');
											item.attr('user_id','${sessionScope.user.id}');
											item.attr('rel_user_id',response.feature.user_id);
											item.addClass('btn-info');
											item.addClass('applyNeighbor');
										}
										else if(response.rel_state==1)
										{
											item.html('<span class="glyphicon glyphicon-option-horizontal"></span> 수락대기');
											item.addClass('btn-warning');
										}
										else if(response.rel_state==2)
										{
											content.find('.NREL').html('<span class="glyphicon glyphicon-remove"></span> 거절됨');
											item.addClass('btn-danger');
										}
										else if(response.rel_state==3)
										{
											content.find('.NREL').html('<span class="glyphicon glyphicon-ok"></span> 이웃');
											item.addClass('btn-success');
										}
										return content.html();
									}
								});
								
								popover.popover('show');
							});
						};
						getInfo($(this).attr('rel_user_id'));
					}
				});
				
				$('.blog_description').each(function(){
					$(this).html(cut($(this).html(),106));					
				});
				
				$('.post_thumbnail').each(function(){
					var area = $(this);
					var content = $(this).parent().find('.post_preview_content');
					
					content.find('img').each(function(){
						area.append('<img src="'+$(this).attr('src')+'" style="width:100px; height:100%;  margin-right: 15px;">');
						return false;
					});
							
				});
				
				$('[data-toggle="popover"]').popover({
					html : true,
					container : 'body',
					content : function(){
						return $('#login-control').html();
					}
				});
				
				$('.post_preview_content').each(function(){
					var content = $(this).html();
					content = content.replace(/&nbsp;/ig, ' ');
					content = content.replace(/(<([^>]+)>)/gi, '');
					$(this).html(cut(content, 380));
				});
				
				$('.post_preview_date').each(function(){
					$(this).html(new Date($(this).html()).format('yyyy.MM.dd a/p hh:mm'));
				});

				$('.blog-summary').on({
					mouseenter : function(){
						$(this).next().show();
					},
					mouseleave : function(){
						$(this).next().hide();
					}
				});
				
				$('.blog-info').on({
					mouseenter : function(){
						$(this).show();
					},
					mouseleave : function(){
						$(this).hide();
					}
				});
			})	
		</script>
	</body>
</html>