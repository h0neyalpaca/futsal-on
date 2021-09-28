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
			<div class="login-wrap">
				<h2><i class="fas fa-user-check"></i> 로그인</h2>
				<div class="login-con">
					<form action="/member/login" method="post">
						<table class="login-form">
							<tr>
								<th><i class="fas fa-user-alt"></i></th>
								<td><input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요."></td>
							</tr>
							<tr class="no-border"><td colspan="2"></td></tr>
							<tr>
								<th><i class="fas fa-key"></i></th>
								<td><input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요."></td>
							</tr>
							<tr class="no-border"><td colspan="2"></td></tr>
							<tr class="no-border">
								<td class="btn-login" colspan="2">
									<input type="submit" value="로그인" />
								</td>
							</tr>
						</table>
					</form>
					<c:if test="${not empty param.err}">
							<div class="error_message" style="padding-left:15px; ">
                                                  아이디 또는 비밀번호가 잘못 입력 되었습니다.<br>
                            <strong>아이디</strong>와 <strong>비밀번호</strong>를 정확히 입력해 주세요.
                            </div>
                            </c:if>
					<div class="login-info">
						<a class="btn-search-id" href="/member/login/lost-id">아이디 찾기</a>
						<a class="btn-search-pw" href="/member/login/lost-password">비밀번호 찾기</a>
						<a class="btn-kakao-login" href="javascript:kakaoLogin();"><i class="fas fa-comment"></i> 카카오 로그인</a>
						<a class="btn-join" href="/member/join-form">회원가입</a>
						
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="/resources/js/kakaoLogin/kakaoLogin.js"></script> 
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>