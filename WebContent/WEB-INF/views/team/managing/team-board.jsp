<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam-form.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching-team.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					<%@ include file="/WEB-INF/views/team/include/team_tab.jsp" %>
					<div class="match-box use-myteam">
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
									<div class="profile-img"></div>
									<div class="profile-name">다인다색<span><i class="fas fa-search"></i>정보보기</span></div>
								</div>
								<div class="btn-appli">수정하기</div>
								<div class="btn-appli">삭제하기</div>
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
								<li><span class="tit">구장비</span>40,000원</li>
							</ul>
							<div class="txt">내용이 들어갑니다.</div>
						</div>
					</div>
					<div class="match-box use-myteam">
						<div class="tit-area">
							<div class="tit-info">
								<div class="state end">모집완료</div>
								<div class="tit">
									<strong>수원 화성 풋살파크 1구장 매치 모집</strong>
									별점 ★★★★★&nbsp;&nbsp;&nbsp;전적 10전 6승 4패
								</div>
							</div>
							<div class="profile_n_appli">
								<div class="profile">
									<div class="profile-img"></div>
									<div class="profile-name">다인다색<span><i class="fas fa-search"></i>정보보기</span></div>
								</div>
								<div class="btn-appli">매칭취소</div>
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
								<li><span class="tit">구장비</span>40,000원</li>
							</ul>
							<div class="txt">내용이 들어갑니다.</div>
						</div>
					</div>
					
					<form action="/myteam/modify-board" method="post">
						
					</form>
					
					<!-- 멤버 화면 -->
					<div class="match-box use-myteam">
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
									<div class="profile-img"></div>
									<div class="profile-name">다인다색<span><i class="fas fa-search"></i>정보보기</span></div>
								</div>
								
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
								<li><span class="tit">구장비</span>40,000원</li>
							</ul>
							<div class="txt">내용이 들어갑니다.</div>
						</div>
					</div>
					<div class="match-box use-myteam">
					<div class="tit-area">
						<div class="tit-info">
							<div class="state end">모집완료</div>
							<div class="tit">
								<strong>수원 화성 풋살파크 1구장 매치 모집</strong>
								별점 ★★★★★&nbsp;&nbsp;&nbsp;전적 10전 6승 4패
							</div>
						</div>
						<div class="profile_n_appli">
							<div class="profile">
								<div class="profile-name">0 명남음</div>
							</div>
							
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