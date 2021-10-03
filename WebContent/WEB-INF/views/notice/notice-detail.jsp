<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/notice/notice-posting.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
	<div class="section">
			<div class="join-wrap">
				<h2><i class="fas fa-book-reader"></i> 공지사항</h2>
				<div class="join-con">
					<div class="article-header">
						<div class="article-title">
							<div class="title-area">
								<h2>${noticeDetail.nwTitle}</h2>
							</div>
						</div>
						<div class="article-info">
							<div class="profile-area">
								<p>관리자</p>
								<span>${noticeDetail.regDate}</span>
								<span>조회 ${noticeDetail.views}</span>
							</div>
						</div>
					</div>
					<div class="article-container">
						<div class="article-inner">
							<p>${noticeDetail.nwContent}</p>
						</div>
					</div>
					<div class="notice-list-button">
					<c:if test="${empty searchContent}">
						<a href="/notice/notice-list?curPage=${curPage}">목록</a>
					</c:if>
					<c:if test="${not empty searchContent}">
						<a href="/notice/notice-list?curPage=${curPage}&searchNotice=${searchContent}">목록</a>
					</c:if>
					</div>

					
				</div>
				<div class="join-con list-wrap">
					<ul class="prev-next-list">
					
					<c:if test="${not empty prevDetail}">
						<li><div class="prev"><a href="/notice/notice-detail?curPage=${curPage}&noticeNo=${prevDetail.nwIdx}"><span>이전글</span><span>${prevDetail.nwTitle}</span></a></div></li>
					</c:if>	
					<c:if test="${empty prevDetail}">
					<li><div class="prev">이전 글이 존재하지 않습니다.</div></li>
					</c:if>
					<c:if test="${not empty nextDetail}">
						<li><div class="next"><a href="/notice/notice-detail?curPage=${curPage}&noticeNo=${nextDetail.nwIdx}"><span>다음글</span><span>${nextDetail.nwTitle}</span></a></div></li>
					</c:if>	
					<c:if test="${empty nextDetail}">
					<li><div class="next">다음 글이 존재하지 않습니다.</div></li>
					</c:if>
					</ul>
				</div>
			</div>
		</div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>