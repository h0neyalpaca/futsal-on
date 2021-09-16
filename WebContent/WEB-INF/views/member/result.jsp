<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/join/join.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="join-wrap">
				<h2><i class="fas fa-user-edit"></i> 회원가입 완료</h2>
				<div class="join-con">
					<p class="greeting-msg">
						<i class="fas fa-grin-hearts"></i>
						<strong>알파카</strong> 회원님 반갑습니다.<br>풋볼ON 웹사이트의 회원이 되신 것을 환영합니다.</p>
					<a class="btn-back-login" href="login/login-form">로그인 하러가기</a>
				</div>
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>