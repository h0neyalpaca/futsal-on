<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/myteam/myteam-form.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/matching/matching-team.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					<!-- 리더 화면 -->
					<ul class="team-tab-wrap">
						<li><a href="/myteam/team-info">팀 정보</a></li>
						<li><a href="/myteam/team-member">팀원 관리</a></li>
						<li><a href="/myteam/team-score">팀 전적</a></li>
						<li><a href="/myteam/team-board">작성글 관리</a></li>
						<li><a class="selected" href="/myteam/delete-team">팀 해체</a></li>
					</ul>
					<div class="team-img"></div>
					<p class="leave-msg">팀을 해체하시면 <strong>팀 정보, 팀 전적이 소멸되며 팀원도 전원 탈퇴됩니다.</strong><br>해체 후 되돌릴 수 없으니 신중하게 결정해주세요.</p>
					<a class="btn-leave-team">팀 해체하기</a>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>