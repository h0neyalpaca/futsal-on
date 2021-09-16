<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="main-visual">
				공지사항을 확인하세요.
			</div>
			<div class="new-match-wrap">
				<div class="search-wrap">
					<form>
						<dl>
							<dt>경기지역</dt>
							<dd>
								<label class="selected"><input type="checkbox" checked>서울</label>
								<label><input type="checkbox">경기</label>
								<label><input type="checkbox">강원</label>
								<label><input type="checkbox">충청</label>
								<label><input type="checkbox">전라</label>
								<label><input type="checkbox">제주</label>
								<label><input type="checkbox">경상</label>
							</dd>
						</dl>
						<dl>
							<dt>기간</dt>
							<dd><input type="date"></dd>
						</dl>
						<dl>
							<dt>상대팀 실력</dt>
							<dd>
								<label><input type="radio">상</label>
								<label class="selected"><input type="radio">중</label>
								<label><input type="radio">하</label>
							</dd>
						</dl>
						<input type="submit" value="검색">
					</form>
				</div>
				<h2>새로 모집중인 매치</h2>
				
				<div class="match-box">
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
							<div class="btn-appli">신청하기</div>
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
				
				<div class="match-box">
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
							<div class="btn-appli">신청하기</div>
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
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>