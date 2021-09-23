<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching-mercenary.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="new-match-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<a href="/matching/team/team-list">팀매칭</a>
						<a href="/matching/mercenary/mercenary-list">용병매칭</a>
					</div>
				</div>
				<div class="matching-write">
					<a href="mercenary-match-form"><i class="fas fa-pencil-alt"></i>글쓰기</a>
				</div>
				<div class="search-wrap">
					<form>
						<dl>
							<dt>경기지역</dt>
							<dd>
								<label class="selected"><input type="checkbox" selected>서울</label>
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
				</div> <!-- End search wrap -->
				<!-- <h2>새로 모집중인 매치</h2> -->
				<!-- 하나의 매치 박스 -->
				<div class="search-role-wrap">
					<div class="search-role">
						<a href="#">최신순</a>
						<a href="#">별점순</a>
					</div>
				</div>
				<div class="match-box">
					<div class="tit-area">
						<div class="tit-info">
							<div class="state recruiting">모집중</div>
							<div class="tit">
								<strong>수원 화성 풋살파크 1구장 용병 1명 모집</strong>
								별점 ★★★★★&nbsp;&nbsp;&nbsp;전적 10전 6승 4패
							</div>
						</div>
						<div class="profile_n_appli">
							<div class="profile">
								<div class="profile-name"><a onclick="">1 명남음</a></div>
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
							<li><span class="tit">용병비</span>10,000원</li>
						</ul>
						<div class="txt">내용이 들어갑니다.</div>
					</div>
				</div><!-- End 매치 박스 -->
				<!-- 하나의 매치 박스 -->
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
								<div class="profile-name"><a onclick="">0 명남음</a></div>
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
							<li><span class="tit">용병비</span>10,000원</li>
						</ul>
						<div class="txt">내용이 들어갑니다.</div>
					</div>
				</div><!-- End 매치 박스 -->
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<script>
	let popup = document.querySelectorAll(".profile-name");

	popup.forEach(element => {
		element.addEventListener('click', () => {
			document.querySelector(".popup-teaminfo-wrap").style.display = 'block';
		})
	});

	document.querySelector(".popup-close").addEventListener('click',() =>{
		document.querySelector(".popup-teaminfo-wrap").style.display = 'none';
	})
</script>
</body>
</html>