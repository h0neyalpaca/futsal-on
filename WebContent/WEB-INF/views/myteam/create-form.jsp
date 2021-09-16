<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="../../resources/css/myteam/myteam-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀 생성</h2>
				<div class="myteam-con">
					<form action="/myteam/create-team" method="post">
						<table class="team-create-form">
							<tr>
								<th>팀 이름</th>
								<td>
									<input type="text" name="teamName" id="teamName" size="8" placeholder="팀 이름을 입력하세요." required />
									<button type="button" id="btnIdCheck">중복확인</button>
									<span class="msg">팀 이름 최대 8자</span>
								</td>
							</tr>
							<tr>
								<th>팀 사진</th>
								<td>
									<input type="file" name="teamFile" id="teamFile" />
									<span class="msg">500MB 이하의 jpg, gif, png</span>
								</td>
							</tr>
							<tr>
								<th>실력</th>
								<td>
									<label><input type="radio" name="teamGrade" id="teamGrade" value="" /> 상</label>
									<label><input type="radio" name="teamGrade" id="teamGrade" value="" /> 중</label>
									<label><input type="radio" name="teamGrade" id="teamGrade" value="" /> 하</label>
								</td>
							</tr>
							<tr>
								<th>활동지역</th>
								<td>
									<label><input type="checkbox"> 서울</label>
									<label><input type="checkbox"> 경기</label>
									<label><input type="checkbox"> 강원</label>
									<label><input type="checkbox"> 충청</label>
									<label><input type="checkbox"> 전라</label>
									<label><input type="checkbox"> 제주</label>
									<label><input type="checkbox"> 경상</label>
								</td>
							</tr>
							<tr>
								<th>소개글</th>
								<td>
									<textarea placeholder="팀 소개 내용을 입력해주세요."></textarea>
									<span id="txtCounter" class="txt-counter">(0 / 최대 200자)</span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="btn-team-create">
										<input type="submit" value="팀 생성하기" />
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>