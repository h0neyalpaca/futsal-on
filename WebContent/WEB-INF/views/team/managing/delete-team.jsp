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
				<div class="team-img"><img src="/img/team/2021/9/26/2fd4cfd7-b3b2-42b6-ac10-4ad0bd785bb1" /></div>
				
				<c:if test="${authentication.grade=='ME03'}">
					<p class="leave-msg">팀을 해체하시면 <strong>팀원은 자동으로 전원 탈퇴되며, 해체 후 7일간 팀 생성 및 팀 참가가 불가</strong>합니다.<br>또한 같은 이름으로 팀 생성이 불가능하니 신중하게 결정해주세요.</p>
					<button class="btn-leave-team" onclick="breakTeam('${team.tmCode}')">팀 해체하기</button>
				</c:if>
				<c:if test="${authentication.grade!='ME03'}">
					<p class="leave-msg">팀을 탈퇴하시면 <strong>7일간 팀 생성 및 팀 참가가 불가</strong>합니다.<br>신중하게 결정해주세요.</p>
					<button class="btn-leave-team" onclick="leaveTeam('${authentication.userId}');">팀 탈퇴하기</button>
				</c:if>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%@ include file="/WEB-INF/views/team/include/team-pop.jsp" %>
<script type="text/javascript" src="${request.contextPath}/resources/js/team/managing.js"></script> 
</body>
</html>