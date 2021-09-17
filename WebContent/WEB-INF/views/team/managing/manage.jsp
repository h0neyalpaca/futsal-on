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
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					<%@ include file="/WEB-INF/views/team/managing/team_tab.jsp" %>
					<!-- 리더화면 -->
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
					<table class="team-member-form">
						<tr>
							<th>NO</th>
							<th>이름</th>
							<th>팀원 등급</th>
						</tr>
						<tr>
							<td>1</td>
							<td>손흥민</td>
							<td>
								회장
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>박지성</td>
							<td>
								매니저
							</td>
						</tr>
						<tr>
							<td>3</td>
							<td>조현우</td>
							<td>
								회원
							</td>
						</tr>
						<tr>
							<td>4</td>
							<td>황의조</td>
							<td>
								회원
							</td>
						</tr>
						<tr>
							<td>5</td>
							<td>정우영</td>
							<td>
								회원
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