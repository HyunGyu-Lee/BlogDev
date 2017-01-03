<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4><strong>블로그 정보</strong></h4>

<table class="table">
	<tr>
		<td>제목</td>
		<td><input type="text" class="form-control" value="${feature.title}" style="font-size: 12px;"/></td>
		<td><small>25자 이내</small> </td>
	</tr>
	<tr>
		<td>소개글</td>
		<td style="width: 40%;"><textarea class="form-control" style="font-size: 12px;">${feature.description}</textarea></td>
		<td style="color : gray;"><small>블로그 프로필 영역의<br/> 프로필사진 아래에 반영됩니다.</small></td>
	</tr>
	<tr>
		<td>블로그 커버 사진</td>
		<td align="center">
			<img src="/blog/blogBgImage/${feature.user_id}" style="width: 100%; height: auto;"/>
		</td>
		<td><small>
			블로그스킨에 따라 이미지가 축소/확대되어 적용됩니다.<br/>
			gif, png 이미지 사용을 권장합니다. jpg는 화질이 저하될 수 있습니다.</small>
		</td>
	</tr>
	<tr>
		<td>내 블로그 주제</td>
		<td>
			<c:forEach items="${subject}" var="item">
				<div class="radio">
					<label>
					<input type="radio" name="subject" value="${item.id}">${item.name}
					</label>
				</div>
			</c:forEach>
		</td>
		<td><small>관심 포스트 추천, 주제별 블로그 탐색 등 여러 기능에 활용됩니다.</small></td>
	</tr>
	<tr>
		<td colspan="3" align="right">
			<input type="button" class="btn btn-primary" value="저장"/>
		</td>
	</tr>
</table>