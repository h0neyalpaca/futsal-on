<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/login/login.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="login-wrap search-page">
				<h2><i class="fas fa-user-shield"></i> 아이디 찾기</h2>
				<div class="login-con">
					<p>회원가입시 입력하신 이름과 이메일을 입력하세요.</p>
					<form action="/login/search-id" method="post">
						<table class="login-search-form">
							<tr>
								<th>이름</th>
								<td><input type="text" name="userName" id="userName" size="8" required /></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									<input type="email" name="email" required />
								</td>
							</tr>
							<tr class="no-border">
								<td class="btn-search" colspan="2">
									<input type="submit" value="아이디 찾기" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>