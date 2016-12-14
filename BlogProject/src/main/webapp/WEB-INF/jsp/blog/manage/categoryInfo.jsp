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
								  category_order="${mainCategory.key.category_order}"><strong>${mainCategory.key.name}</strong></span>
							<ul>
								<c:forEach items="${mainCategory.value}" var="subCategory">
								<li>
									<span class="category-item sub"
										key="${subCategory.id}"
										category_order="${subCategory.category_order}"><strong>${subCategory.name}</strong></span>
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
						<td>
							카테고리 순서
						</td>
						<td>
							<span class="label label-info order-up">
								<span class="glyphicon glyphicon-arrow-up"></span>
								올리기
							</span> &nbsp&nbsp&nbsp&nbsp
							<span class="label label-warning order-down">
								<span class="glyphicon glyphicon-arrow-down"></span>
								내리기
							</span> &nbsp&nbsp&nbsp&nbsp
						</td>
						<td></td>
					</tr>
					<tr>
						<td>추가/삭제</td>
						<td>
							<span class="label label-success category-main-add">
								<span class="glyphicon glyphicon-plus"></span>
								선택한 레벨에 추가
							</span>&nbsp&nbsp&nbsp&nbsp
							<span class="label label-info category-sub-add">
								<span class="glyphicon glyphicon-plus"></span>
								하위 레벨에 추가
							</span>
						</td>
						<td>
							<span class="label label-danger category-delete">
								<span class="glyphicon glyphicon-remove"></span>
								삭제
							</span>
						</td>
					</tr>
				</table>
			</div>
  		</div>
	</div>

</div>