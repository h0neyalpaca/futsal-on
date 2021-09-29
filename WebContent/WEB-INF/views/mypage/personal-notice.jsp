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
							<th>상태</th>
							<th style="width:60%">알림내용</th>
							<th>날짜</th>
						</tr>
						<c:forEach items="${alarms}" var="alarm">
							<c:if test="${alarm.isStart == 1}">
								<tr>
									<td>${alarm.state}</td>
									<td style="text-align:center;">
										<a href="/mypage/my-application">${alarm.content}</a>
									</td>
									<td>${alarm.ntDate}</td>
								</tr>
							</c:if>
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
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>