<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/mypage/mypage.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/mypage/mypage-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="mypage-wrap">
				<h2><i class="fas fa-user-cog"></i> 마이페이지</h2>
				<div class="mypage-con">
					<%@ include file="/WEB-INF/views/mypage/mypage_tab.jsp" %>
					<table class="mypage-notice-form">
						<tr>
							<th>문의유형</th>
							<th>상태</th>
							<th style="width:50%">제목</th>
							<th>날짜</th>
							<th>관리</th>
						</tr>
						<tr>
							<td>${supportList[0].type}</td>
							<td>답변대기</td>
							<td style="text-align:left;">
								<a href="/mypage/support/support-detail">${supportList[0].title}</a>
							</td>
							<td>${supportList[0].regdate}<br>16:30</td>
							<td class="btn-inq">
								<button class="modify" onclick="location.href='/mypage/support/support-modify';">수정</button>
								<button class="delete">삭제</button>
							</td>
						</tr>
						<tr>
							<td>${supportList[1].type}</td>
							<td>답변대기</td>
							<td style="text-align:left;">
								<a href="#">${supportList[1].title}</a>
							</td>
							<td>${supportList[1].regdate}<br>16:30</td>
							<td class="btn-inq">
								<button class="modify">수정</button>
								<button class="delete">삭제</button>
							</td>
						</tr>
						<tr>
							<td>${supportList[2].type}</td>
							<td>답변대기</td>
							<td style="text-align:left;">
								<a href="#">${supportList[2].title}</a>
							</td>
							<td>${supportList[2].regdate}<br>16:30</td>
							<td class="btn-inq">
								<button class="modify">수정</button>
								<button class="delete">삭제</button>
							</td>
						</tr>
					</table>
					<ul class="pagenation">
						<li><i class="far fa-arrow-alt-circle-left"></i></li>
						<li class="selected">1</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li><i class="far fa-arrow-alt-circle-right"></i></li>
					</ul>
				</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>