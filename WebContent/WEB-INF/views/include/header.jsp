<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="wrap">
	<header>
		<div class="header">
			<c:if test="${empty authentication}">
			<ul class="util-wrap">
				<li><a href="/member/login/login-form">· 로그인</a></li>
				<li><a href="/member/join-form">· 회원가입</a></li>
				<li><a href="/mypage/support/support-form">· 문의하기</a></li>
			</ul>
			</c:if>
			<c:if test="${not empty authentication}">
			<ul class="util-wrap">
				<li><strong style="padding-right:3px">${authentication.userId}</strong>님 </li>
				<li><a href="/member/logout">· 로그아웃</a></li>
				<li><a href="/mypage/support/support-form">· 문의하기</a></li>
			</ul>
			</c:if>
			
			<div class=nav-wrap>
				<h1><img onclick="location.href='/index';" src="/resources/img/common/logo_pc.png"></h1>
				<nav>
					<ul>
						<li><a href="/notice/notice-list">공지사항</a></li>
						<li><a href="/matching/team/team-list">매칭하기</a></li>
						<li><a href="/team/main">나의 팀관리</a></li>
						<li><a href="/mypage/personal-notice">마이페이지</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>