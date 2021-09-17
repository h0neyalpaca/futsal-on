<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="../../resources/css/myteam/myteam-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 팀 참가하기</h2>
				<div class="myteam-con">
					<p>전달받으신 <strong>팀 참가 코드</strong>를 입력하세요.</p>
					<form action="/myteam/join-team" method="post">
						<div class="team-join-form">
							<input type="text" name="teamCode" id="teamCode" placeholder="팀 참가코드를 입력하세요." required />
							<input type="submit" value="팀 참가하기" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>