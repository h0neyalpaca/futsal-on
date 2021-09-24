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
						
						<c:forEach items="${supportList}" var="support">
							<tr>
								<c:choose>
									<c:when test="${support.type == 1}">
										<td>불편사항</td>
									</c:when>
									<c:when test="${support.type == 2}">
										<td>신고</td>
									</c:when>
									<c:when test="${support.type == 3}">
										<td>기타</td>
									</c:when>
								</c:choose>
								<td>
									<c:if test="${support.isAnswer == 0}">
										<span>답변대기</span>
									</c:if>
									<c:if test="${support.isAnswer == 1}">
										<span>답변완료</span>
									</c:if>
								</td>
								<td style="text-align:left;">
									<a href="/mypage/support/support-detail?bdIdx=${support.bdIdx}">${support.title}</a>
								</td>
								<td>${support.regDate}<br>16:30</td>
								<td class="btn-inq">
									<button class="modify" onclick="location.href='/mypage/support/support-modify?bdIdx=${support.bdIdx}';">수정</button>
									<button class="delete" onclick="location.href='/mypage/support/support-delete?bdIdx=${support.bdIdx}';">삭제</button>
								</td>
							</tr>
						</c:forEach>
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