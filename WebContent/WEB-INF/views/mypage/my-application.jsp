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
									${match.matchNum}명남음&nbsp;&nbsp;&nbsp;
									별점(${datas.teamList[status.index].tmRating})&nbsp;&nbsp;&nbsp;전적  ${datas.teamList[status.index].gameCnt}전  ${datas.teamList[status.index].tmWin}승 ${datas.teamList[status.index].gameCnt - datas.teamList[status.index].tmWin}패
								</div>
							</div>
							<div class="popup-teaminfo-wrap">
								<div class="popup-close"><i class="fas fa-times"></i></div>
								<div class="popup-teaminfo">
									<div class="popup-title">
										<h1>${datas.teamList[status.index].tmName}</h1>
									</div>
									<div class="popup-team-img">
										<img src="" alt="">
									</div>
									<div class="popup-team-info">
										<p><span class="tit">실력</span> ${datas.teamList[status.index].getTmGrade()}</p>
										<p><span class="tit">활동지역</span><c:choose>
																					<c:when test="${datas.teamList[status.index].localCode == 'LC11'}">서울</c:when>
																					<c:when test="${datas.teamList[status.index].localCode == 'LC31'}">경기</c:when>
																					<c:when test="${datas.teamList[status.index].localCode == 'LC32'}">강원</c:when>
																					<c:when test="${datas.teamList[status.index].localCode == 'LC33'}">충청</c:when>
																					<c:when test="${datas.teamList[status.index].localCode == 'LC35'}">전라</c:when>
																					<c:when test="${datas.teamList[status.index].localCode == 'LC39'}">제주</c:when>
																					<c:when test="${datas.teamList[status.index].localCode == 'LC37'}">경상</c:when>
																				</c:choose></p>
										<p class="intro">${datas.teamList[status.index].getTmInfo()}</p>
									</div>
								</div>
							</div>
							<div class="profile_n_appli">
									<div class="profile-img"></div>
										<div class="profile-name">
										${datas.teamList[status.index].tmName}
										<span>
											<i class="fas fa-search"></i>정보보기
										</span>
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
								 [${match.localCode}] ${match.address} <a class="view-map" onclick="window.open('https://map.kakao.com/link/search/${match.address}', 'pop01', 'top=10, left=10, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no');"> 
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
<script type="text/javascript">

(() => {
	let popup = document.querySelectorAll(".profile-name");

 	popup.forEach(element => {
		element.addEventListener('click', () => {
			document.querySelector(".popup-teaminfo-wrap").style.display = 'block';
		})
	}); 
 	
	document.querySelector(".popup-close").addEventListener('click',() =>{
		document.querySelector(".popup-teaminfo-wrap").style.display = 'none';
	})
	
})(); 
</script>
</body>
</html>