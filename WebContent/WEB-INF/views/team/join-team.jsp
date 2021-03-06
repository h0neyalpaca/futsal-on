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
			<h2><i class="far fa-futbol"></i> 팀 참가하기</h2>
			<div class="myteam-con">
				<p class="join-team-msg">
					<i class="fas fa-user-lock"></i>
					전달받으신 <strong>팀 참가 코드</strong>를 입력하세요.
				</p>
				<div class="team-join-form">
					<input type="text" name="tmCode" id="tmCode" placeholder="팀 참가코드를 입력하세요." required />
					<button id="tmCodeCheck">팀 참가하기</button>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%@ include file="/WEB-INF/views/team/include/team-pop.jsp" %>
<script type="text/javascript" src="${request.contextPath}/resources/js/team/joinForm.js"></script> 
</body>
</html>