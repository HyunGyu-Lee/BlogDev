<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="category-box">
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3> 카테고리 관리</h3>
		</div>
		<div class="panel-body">
			<div class="pull-left">
				<ul>		
					<c:forEach items="${category}" var="mainCategory">
						<li>
							<span class="category-item main"
								  key="${mainCategory.key.id}"
								  category_order="${mainCategory.key.category_order}">${mainCategory.key.name}</span>
							<ul>
								<c:forEach items="${mainCategory.value}" var="subCategory">
								<li>
									<span class="category-item sub" key="${subCategory.id}">${subCategory.name}</span>
								</li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="pull-left category-control-box" align="center">
				<table class="table">
					<tr>
						<td>카테고리명</td>
						<td>
							<form class="category-data">
								<input class="form-control" type="text" name="name" value=""/>
								<input type="hidden" name="type" value=""/>
								<input type="hidden" name="id" value=""/>
							</form>
						</td>
						<td>
							<span class="label label-primary categoryEditBtn">변경</span>
						</td>
					</tr>
					<tr>
						<td>순서</td>
						<td align="center">
							<span class="label label-info">
								<span class="glyphicon glyphicon-menu-up"></span>
								올리기
							</span> &nbsp&nbsp&nbsp&nbsp
							<span class="label label-warning">
								<span class="glyphicon glyphicon-menu-down"></span>
								내리기
							</span>
						</td>						
						<td></td>
					</tr>
				</table>
			</div>
  		</div>
	</div>

</div>