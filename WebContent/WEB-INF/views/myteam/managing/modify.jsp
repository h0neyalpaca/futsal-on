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
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					<!-- 리더 화면 -->
					<ul class="team-tab-wrap">
						<li><a href="/myteam/team-info">팀 정보</a></li>
						<li><a class="selected" href="/myteam/team-member">팀원 관리</a></li>
						<li><a href="/myteam/team-score">팀 전적</a></li>
						<li><a href="/myteam/team-board">글 관리</a></li>
						<li><a href="/myteam/delete-team">팀 해체</a></li>
					</ul>
					<table class="team-member-form">
						<tr>
							<th>NO</th>
							<th>이름</th>
							<th>팀원 등급</th>
							<th>팀원 추방</th>
						</tr>
						<tr>
							<td>1</td>
							<td>손흥민</td>
							<td>
								<div class="selectbox">
									<select>
										<option value="" selected>회장</option>
										<option value="">매니저</option>
										<option value="">팀원</option>
									</select>
								</div>
								<button class="btn-change-grade">변경</button>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>2</td>
							<td>박지성</td>
							<td>
								<div class="selectbox">
									<select>
										<option value="">회장</option>
										<option value="" selected>매니저</option>
										<option value="">팀원</option>
									</select>
								</div>
								<button class="btn-change-grade">변경</button>
							</td>
							<td><button class="btn-out-member">추방</button></td>
						</tr>
						<tr>
							<td>3</td>
							<td>조현우</td>
							<td>
								<div class="selectbox">
									<select>
										<option value="">회장</option>
										<option value="">매니저</option>
										<option value="" selected>팀원</option>
									</select>
								</div>
								<button class="btn-change-grade">변경</button>
							</td>
							<td><button class="btn-out-member">추방</button></td>
						</tr>
						<tr>
							<td>4</td>
							<td>황의조</td>
							<td>
								<div class="selectbox">
									<select>
										<option value="">회장</option>
										<option value="">매니저</option>
										<option value="" selected>팀원</option>
									</select>
								</div>
								<button class="btn-change-grade">변경</button>
							</td>
							<td><button class="btn-out-member">추방</button></td>
						</tr>
						<tr>
							<td>5</td>
							<td>정우영</td>
							<td>
								<div class="selectbox">
									<select>
										<option value="">회장</option>
										<option value="">매니저</option>
										<option value="" selected>팀원</option>
									</select>
								</div>
								<button class="btn-change-grade">변경</button>
							</td>
							<td><button class="btn-out-member">추방</button></td>
						</tr>
						
					</table>
					
					<form action="/myteam/modify-member" method="post">
						
					</form>
					
					<!-- 멤버 화면 -->
					<ul class="team-tab-wrap" style="display:none;">
						<li><a class="selected" href="/myteam/team-info">팀 정보</a></li>
						<li><a href="/myteam/team-member">팀원 보기</a></li>
						<li><a href="/myteam/team-score">팀 전적</a></li>
						<li><a href="/myteam/team-board">작성 게시글</a></li>
						<li><a href="/myteam/leave-team">팀 탈퇴</a></li>
					</ul>
					<table class="team-create-form" style="display:none;">
							<tr>
								<th>팀 이름</th>
								<td>
									다인다색
								</td>
							</tr>
							<tr>
								<th>팀 사진</th>
								<td>
									<div class="team-file">
										<a class="view-file"><i class="fas fa-eye"></i> team_file_name.jpg</a>
									</div>
								</td>
							</tr>
							<tr>
								<th>실력</th>
								<td>
									상
								</td>
							</tr>
							<tr>
								<th>활동지역</th>
								<td>
									서울, 경기
								</td>
							</tr>
							<tr>
								<th>소개글</th>
								<td>
									안녕하세요. 다인다색입니다.
								</td>
							</tr>
							<tr>
								<td colspan="2">
								</td>
							</tr>
						</table>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>