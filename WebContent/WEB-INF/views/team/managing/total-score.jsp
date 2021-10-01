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
						<p><strong>${team.tmName}</strong>팀 전적 ${team.gameCnt}전 ${team.tmWin}승 ${team.tmLose}패 / 평균 평점 ${team.tmScore}</p>
						<div class="our_rating star">
								<c:forEach var="i" begin="1" end="${team.tmScore}">
									<i class="fas fa-star full-star"></i> 
								</c:forEach>
							<c:if test="${team.tmScore%1!=0}">
								<i class="fas fa-star-half-alt"></i>
							</c:if>
						</div>
					</div>
					
					<!-- 리더 화면 -->
					<table class="team-member-form">
						<tr>
							<th>경기일자</th>
							<th>상대팀</th>
							<th>경기 결과</th>
							<th>상대팀 평가</th>
						<c:if test="${empty results}">
							<tr><td colspan="5"><br>완료된 매치가 없습니다.<br><br></td></tr>
						</c:if>
						<c:forEach items="${results}" var="results" varStatus="status">
							<tr>
								<td>${results.matchDate} ${results.matchTime}</td>
								<td>${results.rivalName eq team.tmName ? results.hostName : results.rivalName}</td>
								<td>
									<c:if test="${authentication.grade=='ME03'}">
										<c:choose>
											<c:when test="${empty results.winner and results.matchSchedule lt nowDate}">
												<div class="selectbox">
													<select>
														<option value="" disabled selected>= 결과 =</option>
														<option value="1">승</option>
														<option value="2">패</option>
													</select>
												</div>
												<button class="btn-change-grade" onclick="updateWinner(this, '${results.mgIdx}', '${results.hostCode}', '${results.rivalCode}', '${team.tmCode}');">저장</button>
											</c:when>
											<c:when test="${empty results.winner and results.matchSchedule ge nowDate}">
												경기예정
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
										<c:if test="${(results.rivalRating==0 and results.hostName eq team.tmName) or (results.hostRating==0 and results.rivalName eq team.tmName)}">
											<c:if test="${results.matchSchedule lt nowDate}">
												<div class="selectbox">
													<select name="${results.hostName eq team.tmName?'rivalRating':'hostRating'}">
														<option value="" disabled selected>= 평가 =</option>
														<option value="5">5</option>
														<option value="4">4</option>
														<option value="3">3</option>
														<option value="2">2</option>
														<option value="1">1</option>
													</select>
												</div>
												<button class="btn-change-grade" onclick="updateRslt(this, '${results.mgIdx}', '${results.hostCode}', '${results.rivalCode}', '${team.tmCode}');">저장</button>
											</c:if>
											<c:if test="${results.matchSchedule ge nowDate}">
												경기예정
											</c:if>
										</c:if>
									</c:if>
									<c:if test="${results.rivalRating!=0 and results.hostName eq team.tmName}">
											<c:forEach var="i" begin="1" end="${results.rivalRating}">
												<i class="fas fa-star full-star"></i>
											</c:forEach>
									</c:if>
									<c:if test="${results.hostRating!=0 and results.rivalName eq team.tmName}">
										<c:forEach var="i" begin="1" end="${results.hostRating}">
											<i class="fas fa-star full-star"></i>
										</c:forEach>
									</c:if>
									<c:if test="${authentication.grade!='ME03'}">
										<c:if test="${results.rivalRating==0 and results.hostName eq team.tmName}">미등록</c:if>
										<c:if test="${results.hostRating==0 and results.rivalName eq team.tmName}">미등록</c:if>
									</c:if>
								</td>
								
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%@ include file="/WEB-INF/views/team/include/team-pop.jsp" %>
<script type="text/javascript" src="${request.contextPath}/resources/js/team/managing.js"></script>
</body>
</html>