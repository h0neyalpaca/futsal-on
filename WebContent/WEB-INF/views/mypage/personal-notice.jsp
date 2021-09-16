<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/mypage/mypage.css" />
<link rel="stylesheet" type="text/css" href="../../resources/css/mypage/mypage-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="mypage-wrap">
				<h2><i class="fas fa-user-cog"></i> 마이페이지</h2>
				<div class="mypage-con">
					<ul class="mypage-tab-wrap">
						<li><a class="selected" href="/myteam/team-info">알림내역</a></li>
						<li><a href="/myteam/team-member">용병신청내역</a></li>
						<li><a href="/myteam/team-score">문의내역</a></li>
						<li><a href="/myteam/team-board">회원정보수정</a></li>
					</ul>
					<table class="mypage-notice-form">
						<tr>
							<th>상태</th>
							<th>구분</th>
							<th style="width:60%">알림내용</th>
							<th>날짜</th>
						</tr>
						<tr>
							<td>읽음</td>
							<td>용병</td>
							<td style="text-align:left;">
								<a href="#">작성하신 용병 게시글에 신청자가 있습니다.</a>
							</td>
							<td>2021-08-25<br>16:30</td>
						</tr>
						<tr>
							<td>읽음</td>
							<td>용병</td>
							<td style="text-align:left;">
								<a href="#">작성하신 용병 게시글에 신청자가 있습니다.</a>
							</td>
							<td>2021-08-25<br>16:30</td>
						</tr>
						<tr>
							<td>읽음</td>
							<td>용병</td>
							<td style="text-align:left;">
								<a href="#">작성하신 용병 게시글에 신청자가 있습니다.</a>
							</td>
							<td>2021-08-25<br>16:30</td>
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
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>