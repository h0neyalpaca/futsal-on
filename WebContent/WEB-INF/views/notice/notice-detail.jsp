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
							<p>회원가입 관련 공지 내용입니다.</p>
						</div>
					</div>
					<div class="notice-list-button">
						<a href="/notice/notice-list?page=${curPage}">목록</a>
					</div>

					
				</div>
				<div class="join-con list-wrap">
					<ul class="prev-next-list">
						<li><div class="prev"><a href=""><span>이전글</span><span>이용방법 안내</span></a></div></li>
						<li><div class="next"><a href=""><span>다음글</span><span>공공장소 이용방법 안내</span></div></a></li>
					</ul>
				</div>
			</div>
		</div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>