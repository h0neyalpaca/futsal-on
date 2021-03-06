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
			<h2><a href="/notice/notice-list"><i class="fas fa-book-reader"></i> 공지사항</a></h2>
			<div class="join-con">	
				<form action="/notice/notice-list" >
					<div class="search">
						<div class="search-inner">
							<div class="search-input"><input type="text" name ="searchNotice" value="${searchContent }" ></div>
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
						<!-- 메인 공지 -->
						<c:forEach var ="mainNotice" items="${mainNoticeList}" > 
						<tbody class="top-notice">
							<tr>
							
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

												<a href="/notice/notice-detail?curPage=${page.curPage}&noticeNo=${mainNotice.nwIdx}">${mainNotice.nwTitle}</a>

											</div>
										</div>
									</div>
								</td>
							
								<td class="td-date">
									${mainNotice.regDate}
								</td>
								<td class="td-view">
									${mainNotice.views}
								</td>
						
							</tr>
							</c:forEach> 
						</tbody>
					</table>
					
					<!-- 일반 공지 -->
					<!-- 검색 했을 때 -->
					<c:if test="${not empty searchContent}">
					<table class="join-form">
						<colgroup>
							<col style="width: 88px;">
							<col>
							<col style="width: 118px;">
							<col style="width: 68px;">
						</colgroup>
						<c:set var="num" value="${totalNoticeCnt - ((page.curPage-1) * page.boardSize) }"/>
						<c:forEach var ="notice" items="${noticeSearchList}" > 
						<tbody class="notice">
							<tr>
							
								<td class="num"><span>${num}</span></td>
								<td class="td_article">
									<div class="board-wrap">
										<div class="board-list">
											<div class="inner-list">
												<a href="/notice/notice-detail?curPage=${page.curPage}&noticeNo=${notice.nwIdx}&searchNotice=${searchContent}">${notice.nwTitle}</a>
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
							 <c:set var="num" value="${num-1 }"></c:set>
							</c:forEach>

							<tr>
								
					</table>
					<ul class="page-button">
				
					<c:if test="${page.curPage> page.pageSize }"> 
							<li><a class="prev-noti" href="/notice/notice-list?curPage=${page.startPage-page.pageSize}&searchNotice=${searchContent}"><i class="far fa-arrow-alt-circle-left"></i></a></li>
					</c:if>
				
					<c:forEach var="pageNum" begin="${page.startPage}" end="${page.endPage}">
							<c:choose>
							<c:when test="${page.curPage eq pageNum }">	
								<li class="page-num active"><a href="notice-list?curPage=${pageNum}&searchNotice=${searchContent}">${pageNum}</a></li>
							</c:when>
							<c:otherwise>	
								<li class="page-num"><a href="notice-list?curPage=${pageNum}&searchNotice=${searchContent}">${pageNum}</a></li>
							</c:otherwise>
							</c:choose>
					</c:forEach>
						
					<c:if test="${page.endPage<page.totalPage }">
						<li><a class="next-noti" href="/notice/notice-list?curPage=${page.endPage+1}&searchNotice=${searchContent}"><i class="far fa-arrow-alt-circle-right"></i></a></li>
					</c:if>
				</ul>
				</c:if>
				
				<!-- 검색 안 했을 때 -->
				<c:if test="${empty searchContent}">
				<table class="join-form">
						<colgroup>
							<col style="width: 88px;">
							<col>
							<col style="width: 118px;">
							<col style="width: 68px;">
						</colgroup>
						<c:set var="num" value="${totalNoticeCnt - ((page.curPage-1) * page.boardSize) }"/>
						<c:forEach var ="notice" items="${noticeList}" > 
						<tbody class="notice">
							<tr>
							
								<td class="num"><span>${num}</span></td>
								<td class="td_article">
									<div class="board-wrap">
										<div class="board-list">
											<div class="inner-list">
												<a href="/notice/notice-detail?curPage=${page.curPage}&noticeNo=${notice.nwIdx}">${notice.nwTitle}</a>
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
							 <c:set var="num" value="${num-1 }"></c:set>
							</c:forEach>
						<tr>
					</table>
					<ul class="page-button">
				
					<c:if test="${page.curPage> page.pageSize }"> 
							<li><a class="prev-noti" href="/notice/notice-list?curPage=${page.startPage-page.pageSize}"><i class="far fa-arrow-alt-circle-left"></i></a></li>
					</c:if>
				
					<c:forEach var="pageNum" begin="${page.startPage}" end="${page.endPage}">
							<c:choose>
							<c:when test="${page.curPage eq pageNum }">	
								<li class="page-num active"><a href="notice-list?curPage=${pageNum}">${pageNum}</a></li>
							</c:when>
							<c:otherwise>	
								<li class="page-num"><a href="notice-list?curPage=${pageNum}">${pageNum}</a></li>
							</c:otherwise>
							</c:choose>
					</c:forEach>
						
					<c:if test="${page.endPage<page.totalPage }">
						<li><a class="next-noti" href="/notice/notice-list?curPage=${page.endPage+1}"><i class="far fa-arrow-alt-circle-right"></i></a></li>
					</c:if>
				</ul>
				</c:if>
								
				
				</form>
				</div>
				
			</div>
		</div>

</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>