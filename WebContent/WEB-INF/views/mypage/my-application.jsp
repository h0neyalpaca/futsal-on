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
								<c:choose>
									<c:when test="${match.state == 0}"><div class="state recruiting">모집중</div></c:when>
									<c:when test="${match.state == 1}"><div class="state recruiting" style="background-color: gray;">게임완료</div></c:when>
								</c:choose>
								<div class="tit">
									<strong>${match.title}</strong>
									${datas.teamList[status.index].tmName}&nbsp;&nbsp;&nbsp;
									별점(${datas.teamList[status.index].tmScore})&nbsp;&nbsp;&nbsp;전적  ${datas.teamList[status.index].gameCnt}전  ${datas.teamList[status.index].tmWin}승 ${datas.teamList[status.index].gameCnt - datas.teamList[status.index].tmWin}패
								</div>
							</div>
							<div class="profile_n_appli">
								<div class="profile">
									<div class="profile-name">${match.matchNum}명남음</div><br>
								</div>
								<c:choose>
									<c:when test="${match.state == 0}"><div class="btn-appli" onclick="location.href='/mypage/my-application-delete?mgIdx=${datas.mgList[status.index].mgIdx}';">신청취소</div></c:when>
									<c:when test="${match.state == 1}"><div  class="btn-appli">게임 종료</div></c:when>
								</c:choose>
							</div>
						</div>
						<div class="match-detail">
							<ul>
								<li><span class="tit">지역</span>
								 ${match.localCode} ${match.address} <a class="view-map" onclick="window.open('https://map.kakao.com/link/search/${match.address}', 'pop01', 'top=10, left=10, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no');"> 
									<i class="fas fa-map-marker-alt"></i> 지도보기
								</a></li>
								<li><span class="tit">매치날짜</span>${match.matchDate} <br>&nbsp;&nbsp;${match.matchTime}</li>
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