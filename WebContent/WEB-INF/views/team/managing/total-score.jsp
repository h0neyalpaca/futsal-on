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
					<%@ include file="/WEB-INF/views/team/include/team_tab.jsp" %>
					
					<div class="rating_area">
						<p><strong>${team.tmName}</strong> 팀 전적 ${team.gameCnt}전 ${team.tmWin}승 4패</p>
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
							<th>등록</th>
						<c:if test="${empty results}">
							<tr><td colspan="5"><br>완료된 매치가 없습니다.<br><br></td></tr>
						</c:if>
						<c:forEach items="${results}" var="results" varStatus="status">
							<tr>
								<td>${results.matchDate} ${results.matchTime}</td>
								<td>${results.applicantName eq team.tmName ? results.tmName : results.applicantName}</td>
								<td>
									<c:if test="${authentication.grade=='ME03'}">
										<c:choose>
											<c:when test="${empty results.winner and results.tmName eq team.tmName}">
												<div class="selectbox">
													<select>
														<option value="" disabled selected>= 결과 =</option>
														<option value="">승</option>
														<option value="">패</option>
													</select>
												</div>
											</c:when>
											<c:when test="${empty results.winner and results.applicantName eq team.tmName}">
												미등록
											</c:when>
											<c:when test="${not empty results.winner}">
												${results.winner eq team.tmCode?'승':'패'}
											</c:when>
										</c:choose>
									</c:if>
									<c:if test="${authentication.grade!='ME03'}">
										${empty results.winner?'미등록':results.winner eq team.tmCode?'승':'패'}
									</c:if>
								</td>
								<td class="star">
									<c:if test="${authentication.grade=='ME03'}">
										<c:choose>
											<c:when test="${results.rivalRating==0 and results.tmName eq team.tmName}">
												<div class="selectbox">
													<select name="rivalRating">
														<option value="" disabled selected>= 만족도 =</option>
														<option value="5">5</option>
														<option value="4">4</option>
														<option value="3">3</option>
														<option value="2">2</option>
														<option value="1">1</option>
													</select>
												</div>
											</c:when>
											<c:when test="${results.hostRating==0 and results.applicantName eq team.tmName}">
												<div class="selectbox">
													<select name="hostRating">
														<option value="" disabled selected>= 만족도 =</option>
														<option value="5">5</option>
														<option value="4">4</option>
														<option value="3">3</option>
														<option value="2">2</option>
														<option value="1">1</option>
													</select>
												</div>
											</c:when>
										</c:choose>
									</c:if>
									<c:if test="${results.rivalRating!=0 and results.tmName eq team.tmName}">
											<c:forEach var="i" begin="1" end="5">
												<c:if test="${i<=results.rivalRating}"><i class="fas fa-star full-star"></i></c:if>
												<c:if test="${i>results.rivalRating}"><i class="far fa-star"></i></c:if>
											</c:forEach>
									</c:if>
									<c:if test="${results.hostRating!=0 and results.applicantName eq team.tmName}">
										<c:forEach var="i" begin="1" end="5">
											<c:if test="${i<=results.hostRating}"><i class="fas fa-star full-star"></i></c:if>
											<c:if test="${i>results.hostRating}"><i class="far fa-star"></i></c:if>
										</c:forEach>
									</c:if>
									<c:if test="${authentication.grade!='ME03'}">
										<c:if test="${results.rivalRating==0 and results.tmName eq team.tmName}">미등록</c:if>
										<c:if test="${results.hostRating==0 and results.applicantName eq team.tmName}">미등록</c:if>
									</c:if>
								</td>
								<td>
									<c:if test="${authentication.grade=='ME03'}">
										<c:if test="${(results.tmName eq team.tmName and results.rivalRating==0) or (results.tmName eq team.tmName and empty results.winner) or (results.applicantName eq team.tmName and results.hostRating==0)}">
											<button class="btn-change-grade">저장</button>
										</c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
					
					<form action="/myteam/modify-score" method="post">
						
					</form>
					
					<!-- 멤버 화면 -->
					<table class="team-member-form" style="display:none;">
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