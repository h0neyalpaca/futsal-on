<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/mypage/mypage.css" />
<link rel="stylesheet" type="text/css" href="../../resources/css/mypage/mypage-form.css" />
<link rel="stylesheet" type="text/css" href="../../resources/css/matching/matching-mercenary.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="section">
			<div class="mypage-wrap">
				<h2><i class="fas fa-user-cog"></i> 마이페이지</h2>
				<div class="mypage-con">
					<ul class="mypage-tab-wrap">
						<li><a href="/myteam/team-info">알림내역</a></li>
						<li><a class="selected" href="/myteam/team-member">용병신청내역</a></li>
						<li><a href="/myteam/team-score">문의내역</a></li>
						<li><a href="/myteam/team-board">회원정보수정</a></li>
					</ul>
					<div class="match-box use-mypage">
					<div class="tit-area">
						<div class="tit-info">
							<div class="state recruiting">모집중</div>
							<div class="tit">
								<strong>수원 화성 풋살파크 1구장 매치 모집</strong>
								별점 ★★★★★&nbsp;&nbsp;&nbsp;전적 10전 6승 4패
							</div>
						</div>
						<div class="profile_n_appli">
							<div class="profile">
								<div class="profile-name">0 명남음</div>
							</div>
							<div class="btn-appli">신청취소</div>
						</div>
					</div>
					<div class="match-detail">
						<ul>
							<li><span class="tit">지역</span>[경기] 수원 화성 풀살파크 1구장 <a class="view-map"><i class="fas fa-map-marker-alt"></i> 지도보기</a></li>
							<li><span class="tit">매치날짜</span>2021-09-11 13:00</li>
						</ul>
						<ul>
							<li><span class="tit">매치방식</span>6:6</li>
							<li><span class="tit">실력</span>상</li>
							<li><span class="tit">용병비</span>10,000원</li>
						</ul>
						<div class="txt">내용이 들어갑니다.</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</section>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>