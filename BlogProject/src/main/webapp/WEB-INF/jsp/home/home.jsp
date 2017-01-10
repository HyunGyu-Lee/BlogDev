<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Blog - 메인</title>
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
													<div class="pull-right" style="height: 100%; vertical-align: middle;">
														<c:if test="${empty sessionScope.user.id or (not empty sessionScope.user.id and sessionScope.user.id ne blog.user_id)}">
														<button type="button" class="btn btn-primary"><strong>+ 이웃</strong></button>
														</c:if>
													</div>
												</div>
												<div style="margin-bottom: 10px;">
													<h4><strong>${blog.nickname} (${blog.user_id})</strong></h4>											
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
					
					</c:when>
					<c:when test="${search_by eq 'nickname'}">
					
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
		
		<script type="text/javascript">
		
			$(document).ready(function(){
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
				
				$('.post_preview_content').each(function(){
					var content = $(this).html();
					content = content.replace(/&nbsp;/ig, ' ');
					content = content.replace(/(<([^>]+)>)/gi, '');
					$(this).html(cut(content, 380));
				});
				
				$('.post_preview_date').each(function(){
					$(this).html(new Date($(this).html()).format('yyyy.MM.dd a/p hh:mm'));
				});
			})	
		</script>
	</body>
</html>