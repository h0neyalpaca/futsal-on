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
						<c:forEach items="${alarmsPage}" var="alarm" varStatus="status">
							<c:if test="${alarm.isStart == 1}">
								<tr>
									<td><c:if test="${alarm.state == 1}">읽음</c:if><c:if test="${alarm.state == 0}">안읽음</c:if></td>
									<td style="text-align:center;">
										<a href="/mypage/alarm-check?ntIdx=${alarm.ntIdx}">${alarm.content}</a>
									</td>
									<td>${alarm.ntDate}<br>${times[status.index]} </td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
					<ul class="pagenation">
						<c:if test="${page.curPage> page.pageSize }"> 
							<li><a class="prev-noti" href="/mypage/personal-notice?curPage=${page.startPage-page.pageSize}"><i class="far fa-arrow-alt-circle-left"></i></a></li>
						</c:if>
				
					<c:forEach var="pageNum" begin="${page.startPage}" end="${page.endPage}">
							<c:choose>
							<c:when test="${page.curPage eq pageNum }">	
								<li class="page-num active"><a href="personal-notice?curPage=${pageNum}">${pageNum}</a></li>
							</c:when>
							<c:otherwise>	
								<li class="page-num"><a href="personal-notice?curPage=${pageNum}">${pageNum}</a></li>
							</c:otherwise>
							</c:choose>
					</c:forEach>
						
					<c:if test="${page.endPage<page.totalPage }">
						<li><a class="next-noti" href="/mypage/personal-notice?curPage=${page.endPage+1}"><i class="far fa-arrow-alt-circle-right"></i></a></li>
					</c:if>
					</ul>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>