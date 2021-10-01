<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/myteam/myteam-form.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/matching/matching-team.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
	<div class="section">
		<div class="myteam-wrap">
			<h2><i class="far fa-futbol"></i> 나의 팀</h2>
			<div class="myteam-con">
				<%@ include file="/WEB-INF/views/team/include/team_tab.jsp" %>
				<div class="team-img"></div>
				
				<c:if test="${authentication.grade=='ME03'}">
					<p class="leave-msg">팀을 해체하시면 <strong>팀원은 자동으로 전원 탈퇴</strong>되며,<br>동일한 이름으로 팀 생성이 불가능하니 신중하게 결정해주세요.</p>
					<button class="btn-leave-team" onclick="breakTeam('${team.tmCode}')">팀 해체하기</button>
				</c:if>
				<c:if test="${authentication.grade!='ME03'}">
					<p class="leave-msg">팀을 탈퇴하시면 <strong>더이상 팀 정보에 노출되지 않습니다.</strong><br>신중하게 결정해주세요.</p>
					<button class="btn-leave-team" onclick="leaveTeam('${authentication.userId}');">팀 탈퇴하기</button>
				</c:if>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%@ include file="/WEB-INF/views/team/include/team-pop.jsp" %>
<script type="text/javascript" src="${request.contextPath}/resources/js/team/managing.js"></script>
<script type="text/javascript">
	let tmImg = document.querySelector('.team-img');
	tmImg.style.background='url("/img/team/no-img.jpg") center center';
	<c:if test="${file.tmCode != null}">
		tmImg.style.background='url("/img/team/${file.savePath}${file.renameFileName}") center center';
	</c:if>
	tmImg.style.backgroundSize='cover';
	
	let breakTeam = (tmCode) => {
		drawQuestion('정말로 팀을 해체하시겠습니까?','breakFunc("'+tmCode+'");');
	}
	let leaveTeam = (userId) => {
		drawQuestion('정말로 팀을 탈퇴하시겠습니까?','leaveFunc("'+userId+'");');
	}
	let breakFunc = (tmCode) => {
		let xhr = new XMLHttpRequest();
		xhr = xmlRequest('POST','break-team','/team/main');
		xhr.send('tmCode='+tmCode);
	}
	let leaveFunc = (userId) => {
		let xhr = new XMLHttpRequest();
		xhr = xmlRequest('POST','leave-team','/team/main');
		xhr.send('userId='+userId);
	}
</script> 
</body>
</html>