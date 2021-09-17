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
					
					<div class="rating_area">
						<p>다인다색 팀의 평균 평점</p>
						<div class="our_rating star">
							<i class="fas fa-star full-star"></i><i class="fas fa-star full-star"></i><i class="fas fa-star full-star"></i><i class="far fa-star"></i><i class="far fa-star"></i>
						</div>
					</div>
					
					<!-- 리더 화면 -->
					<table class="team-member-form">
						<tr>
							<th>경기일자</th>
							<th>상대팀</th>
							<th>경기 결과</th>
							<th>경기 만족도</th>
							<th></th>
						</tr>
						<tr>
							<td>2021-09-16</td>
							<td>지구방위대</td>
							<td>
								<div class="selectbox">
									<select>
										<option value="" disabled selected>= 결과 =</option>
										<option value="">승</option>
										<option value="">패</option>
									</select>
								</div>
							</td>
							<td class="star"><i class="fas fa-star full-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></td>
							<td><button class="btn-change-grade">저장</button></td>
						</tr>
						<tr>
							<td>2021-09-10</td>
							<td>K축구</td>
							<td>
								승
							</td>
							<td class="star"><i class="fas fa-star full-star"></i><i class="fas fa-star full-star"></i><i class="fas fa-star full-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></td>
						</tr>
					</table>
					
					<form action="/myteam/modify-score" method="post">
						
					</form>
					
					<!-- 멤버 화면 -->
					<table class="team-member-form">
						<tr>
							<th>경기일자</th>
							<th>상대팀</th>
							<th>경기 결과</th>
							<th>경기 만족도</th>
							<th></th>
						</tr>
						<tr>
							<td>2021-09-10</td>
							<td>K축구</td>
							<td>
								승
							</td>
							<td class="star"><i class="fas fa-star full-star"></i><i class="fas fa-star full-star"></i><i class="fas fa-star full-star"></i><i class="far fa-star"></i><i class="far fa-star"></i></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>