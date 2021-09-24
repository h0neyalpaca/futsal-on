<%@page import="com.kh.futsal.notice.model.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/notice/notice.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<section>
	<div class="section">
		<div class="join-wrap">
			<h2><i class="fas fa-book-reader"></i> 공지사항</h2>
			<div class="join-con">	
				<form action="/notice" method="post">
					<div class="search">
						<div class="search-inner">
							<div class="search-input"><input type="text" ></div>
							<div class="search-button"><button><i class="fas fa-search"></i></button></div>
						</div>
					</div>
					<table class="join-form">
						<colgroup>
							<col style="width: 88px;">
							<col>
							<col style="width: 118px;">
							<col style="width: 68px;">
						</colgroup>
						<thead>
							<tr class="Table-title">
								<th></th>
								<th scope="col"><span class="article-title">제목</span></th>
								<th scope="col" class="th-name">작성일</th>
								<th scope="col" class="th-name">조회</th>
							</tr>
						</thead>
						
						<c:forEach var ="notice" items="${noticeList}" > 
						<tbody class="top-notice">
							<tr>
							
							 <c:if test = "${notice.nwMain==0}"> 
								<td>	
									<div class="board-tag">
										<strong class="board-tag-txt">
											<span class="inner">
												공지
											</span>
										</strong>
									</div>
								</td>
							 
							
								<td class="td_article">
									<div class="board-wrap">
									
										<div class="board-list">
											<div class="inner-list top-notice-list">
												<a href="/notice/notice-detail">${notice.nwTitle}</a>
											</div>
										</div>
									</div>
								</td>
							
								<td class="td-date">
									${notice.regDate}
								</td>
								<td class="td-view">
									${notice.views}
								</td>
								</c:if> 
							</tr>
							</c:forEach> 
						</tbody>
					</table>
					
					<table class="join-form">
						<colgroup>
							<col style="width: 88px;">
							<col>
							<col style="width: 118px;">
							<col style="width: 68px;">
						</colgroup>
						<c:forEach var ="notice" items="${noticeList}" > 
						<tbody class="notice">
							<tr>
							
								<td class="num"><span>${notice.nwIdx}</span></td>
								<td class="td_article">
									<div class="board-wrap">
										<div class="board-list">
											<div class="inner-list">
												<a href="">${notice.nwTitle}</a>
											</div>
										</div>
									</div>
								</td>
								<td class="td-date">
									${notice.regDate}
								</td>
								<td class="td-view">
									${notice.views}
								</td>
								 
							</tr>
							</c:forEach>

							<tr>
								
					</table>
				</form>
				<c:forEach var="i" begin="1" end="${totalPage}">
				<div class="page-button">
					<div class="page-num"><a href="notice-list?page=${i}">${i}</a></div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>