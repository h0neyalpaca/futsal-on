<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					<p>알파카 회원님은 아직 팀에 소속되어 있지 않습니다.<br />
					아래 메뉴에서 <strong>직접 팀을 생성하시거나, 기존의 팀에 참가</strong>하실 수 있습니다.</p>
					<div class="btn-team-area">
						<div class="btn-team create">
							<i class="far fa-plus-square"></i>
							나만의 팀을 만듭니다.
							<button onclick="location.href='/team/create-form';">팀 생성</button>
						</div>
						<div class="btn-team join">
							<i class="fas fa-sign-in-alt"></i>
							기존 팀에 참가합니다.
							<button onclick="location.href='/team/join-team';">팀 참가</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>