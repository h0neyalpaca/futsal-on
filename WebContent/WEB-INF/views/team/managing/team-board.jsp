<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam-form.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching-team.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					<%@ include file="/WEB-INF/views/team/include/team_tab.jsp" %>
					<ul class="board-cate">
						<li id="tmBrdBtn" class="selected">작성 글</li>
						<li id="appliBtn">신청 글</li>
					</ul>
					<div id="tmBrds">
						<c:if test="${empty tmBoards}">
							<div style="padding:40px;text-align:center;">작성하신 글이 없습니다.</div>
						</c:if>
						<c:forEach items="${tmBoards}" var="tmBoards" varStatus="status">
							<div class="match-box use-myteam">
								<div class="tit-area">
									<div class="tit-info">
										<div class="state ${tmBoards.state eq 0?'recruiting':'end'}">${tmBoards.state eq 0?'모집중':'모집완료'}</div>
										<div class="tit">
											<strong>${tmBoards.title}</strong>
												별점 
												<c:forEach var="i" begin="1" end="${tmBoards.tmRating}">
													<i class="fas fa-star full-star" style="display:inline-block;margin-right:-4px;color:#aaa;"></i>
												</c:forEach>
												<c:if test="${team.tmRating%1!=0}">
													<i class="fas fa-star-half-alt" style="display:inline-block;margin-right:-4px;color:#aaa;"></i>
												</c:if>
												<c:forEach var="i" begin="1" end="${5-tmBoards.tmRating}">
													<i class="far fa-star" style="display:inline-block;margin-right:-3px;color:#aaa;"></i>
												</c:forEach>
												&nbsp;&nbsp;전적 ${tmBoards.gameCnt}전 ${tmBoards.tmWin}승  ${tmBoards.tmLose}패
										</div>
									</div>
									<div class="profile_n_appli">
										<div class="profile">
											<c:if test="${tmBoards.filePath != null}">
												<div class="profile-img" style="background:url('${request.contextPath}/img/team/${tmBoards.filePath}') center center;background-size:cover;"></div>
											</c:if>
											<c:if test="${tmBoards.filePath == null}">
												<div class="profile-img" style="background:url('${request.contextPath}/img/team/no-img.jpg') center center;background-size:cover;"></div>
											</c:if>
											<div class="profile-name" onclick="teamInfo('${tmBoards.tmCode}')">${tmBoards.tmName}<span><i class="fas fa-search"></i>정보보기</span></div>
										</div>
										<div class="btn-appli" onclick="teamMatchingModify('${tmBoards.mmIdx}')">수정하기</div>
										<div class="btn-appli" onclick="teamMatchingDel('${tmBoards.mmIdx}')">삭제하기</div>
									</div>
								</div>
								<div class="match-detail">
									<ul>
										<li><span class="tit">지역</span>[${tmBoards.localCode eq 'LC11'?'서울':team.localCode eq 'LC31'?'경기':team.localCode eq 'LC32'?'강원':team.localCode eq 'LC33'?'충청':team.localCode eq 'LC35'?'전라':team.localCode eq 'LC39'?'제주':'경상'}] ${tmBoards.address} 
											<a class="view-map" onclick="window.open('https://map.kakao.com/link/search/${tmBoards.address}', 'pop01', 'top=10, left=10, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no');"> 
												<i class="fas fa-map-marker-alt"></i> 지도보기
											</a>
										</li>
										<li><span class="tit">매치날짜</span>${tmBoards.matchDate} ${tmBoards.matchTime} </li>
									</ul>
									<ul>
										<li><span class="tit">매치방식</span>${tmBoards.tmMatch}:${tmBoards.tmMatch}</li>
										<li><span class="tit">실력</span>${tmBoards.grade}</li>
										<li><span class="tit">구장비</span>${tmBoards.expense}원</li>
									</ul>
									<div class="txt">${tmBoards.content}</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div id="appliBrds" style="display:none;">
				
						<div style="padding:40px;text-align:center;">신청하신 글이 없습니다.</div>
					
					</div>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%@ include file="/WEB-INF/views/pop/team-info-pop.jsp" %>
<script type="text/javascript" src="${request.contextPath}/resources/js/popup/popup.js"></script>
<script type="text/javascript">
document.querySelector('#tmBrdBtn').addEventListener('click',(e)=>{
	e.target.style.backgroundColor='#483D8B';
	e.target.style.color='#fff';
	document.querySelector('#appliBtn').style.backgroundColor='#eee';
	document.querySelector('#appliBtn').style.color="#333";
	document.querySelector('#appliBrds').style.display="none";
	document.querySelector('#tmBrds').style.display="block";
});
document.querySelector('#appliBtn').addEventListener('click',(e)=>{
	e.target.style.backgroundColor='#483D8B';
	e.target.style.color='#fff';
	document.querySelector('#tmBrdBtn').style.backgroundColor='#eee';
	document.querySelector('#tmBrdBtn').style.color="#333";
	document.querySelector('#tmBrds').style.display="none";
	document.querySelector('#appliBrds').style.display="block";
});

let teamMatchingModify = (idx) => {
	if (window.confirm("매치글을 수정하시겠습니까?")) {
		console.dir(idx);
		location.href="/matching/team/team-modify?matchIdx="+idx;
	}
	
	// +"&tmCode="+tmCode+"&userId="+user+"&matchDate="+date
}
let teamMatchingDel = (idx) => {
	
	if (window.confirm("매치글을 수정하시겠습니까?")) {
		location.href="/matching/team/team-modify-register?matchIdx="+idx+"&modify=삭제";
	}
	
}


</script>
</body>
</html>