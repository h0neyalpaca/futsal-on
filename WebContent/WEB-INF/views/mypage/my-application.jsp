<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/mypage/mypage.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/mypage/mypage-form.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching-mercenary.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="mypage-wrap">
				<h2><i class="fas fa-user-cog"></i> 마이페이지</h2>
				<div class="mypage-con">
					<%@ include file="/WEB-INF/views/mypage/mypage_tab.jsp" %>
					
					<c:forEach items="${datas.matchList}" var="match" varStatus="status">
						<div class="match-box use-mypage">
						<div class="tit-area">
							<div class="tit-info">
								<div class="state recruiting">모집중</div>
								<div class="tit">
									<strong>${match.title}</strong>
									별점<c:choose>
										<c:when test="${datas.teamList[status.index].tmScore == 0}">☆☆☆☆☆</c:when>
										<c:when test="${datas.teamList[status.index].tmScore == 1}">★☆☆☆☆</c:when>
										<c:when test="${datas.teamList[status.index].tmScore == 2}">★★☆☆☆</c:when>
										<c:when test="${datas.teamList[status.index].tmScore == 3}">★★★☆☆</c:when>
										<c:when test="${datas.teamList[status.index].tmScore == 4}">★★★★☆</c:when>
										<c:when test="${datas.teamList[status.index].tmScore == 5}">★★★★★</c:when>
									</c:choose>&nbsp;&nbsp;&nbsp;전적  ${datas.teamList[status.index].gameCnt}전  ${datas.teamList[status.index].tmWin}승 ${datas.teamList[status.index].gameCnt - datas.teamList[status.index].tmWin}패
								</div>
							</div>
							<div class="profile_n_appli">
								<div class="profile">
									<div class="profile-name">${match.matchNum}명남음</div>
								</div>
								<div class="btn-appli">신청취소</div>
							</div>
						</div>
						<div class="match-detail">
							<ul>
								<li><span class="tit">지역</span>
								<c:choose>
									<c:when test="${match.localCode == 'LC11'}">[서울]</c:when>
									<c:when test="${match.localCode == 'LC21'}">[부산]</c:when>
									<c:when test="${match.localCode == 'LC22'}">[대구]</c:when>
									<c:when test="${match.localCode == 'LC23'}">[인천]</c:when>
									<c:when test="${match.localCode == 'LC24'}">[광주]</c:when>
									<c:when test="${match.localCode == 'LC25'}">[대전]</c:when>
									<c:when test="${match.localCode == 'LC26'}">[울산]</c:when>
									<c:when test="${match.localCode == 'LC29'}">[세종]</c:when>
									<c:when test="${match.localCode == 'LC31'}">[경기]</c:when>
									<c:when test="${match.localCode == 'LC32'}">[강원]</c:when>
									<c:when test="${match.localCode == 'LC33'}">[충북]</c:when>
									<c:when test="${match.localCode == 'LC34'}">[충남]</c:when>
									<c:when test="${match.localCode == 'LC35'}">[전북]</c:when>
									<c:when test="${match.localCode == 'LC36'}">[전남]</c:when>
									<c:when test="${match.localCode == 'LC37'}">[경북]</c:when>
									<c:when test="${match.localCode == 'LC38'}">[경남]</c:when>
									<c:when test="${match.localCode == 'LC39'}">[제주]</c:when>
								</c:choose> ${match.address} <a class="view-map"><i class="fas fa-map-marker-alt"></i> 지도보기</a></li>
								<li><span class="tit">매치날짜</span>${match.matchTime}</li>
							</ul>
							<ul>
								<li><span class="tit">매치방식</span>${match.tmMatch}:${match.tmMatch}</li>
								<li><span class="tit">실력</span>${match.grade}</li>
								<li><span class="tit">용병비</span>${match.expense}</li>
							</ul>
							<div class="txt">${match.content}</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>