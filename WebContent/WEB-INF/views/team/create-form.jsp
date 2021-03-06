<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀 생성</h2>
				<div class="myteam-con">
					<form id="frm_create-team" action="${request.contextPath}/team/create" method="post" enctype="multipart/form-data">
						<table class="team-create-form">
							<tr>
								<th>팀 이름</th>
								<td>
									<input type="text" name="tmName" id="tmName" size="8" placeholder="팀 이름을 입력하세요." required />
									<button type="button" id="tmNameCheck">중복확인</button>
									<span class="msg">2~8자리 한/영/숫자만 입력</span>
								</td>
							</tr>
							<tr>
								<th>팀 사진</th>
								<td>
									<input type="file" name="teamFile" id="teamFile" onchange="fileCheck(this.files)">
									<span class="msg">JPG/GIF/PNG 파일만 업로드 가능</span>
								</td>
							</tr>
							<tr>
								<th>실력</th>
								<td>
									<label><input type="radio" name="tmGrade" value="상" required /> 상</label>
									<label><input type="radio" name="tmGrade" value="중" /> 중</label>
									<label><input type="radio" name="tmGrade" value="하" /> 하</label>
								</td>
							</tr>
							<tr>
								<th>활동지역</th>
								<td>
									<label><input type="radio" name="localCode" value="LC11" required /> 서울</label>
									<label><input type="radio" name="localCode" value="LC31" /> 경기</label>
									<label><input type="radio" name="localCode" value="LC32" /> 강원</label>
									<label><input type="radio" name="localCode" value="LC33" /> 충청</label>
									<label><input type="radio" name="localCode" value="LC35" /> 전라</label>
									<label><input type="radio" name="localCode" value="LC39" /> 제주</label>
									<label><input type="radio" name="localCode" value="LC37" /> 경상</label>
								</td>
							</tr>
							<tr>
								<th>소개글</th>
								<td>
									<textarea name="tmInfo" placeholder="팀 소개 내용을 입력해주세요."></textarea>
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
<%@ include file="/WEB-INF/views/team/include/team-pop.jsp" %>
<script type="text/javascript" src="${request.contextPath}/resources/js/team/createForm.js"></script> 
</body>
</html>