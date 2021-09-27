<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching-team.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<%@ include file="/WEB-INF/views/pop/team-info.jsp" %>
		<div class="section">
			<div class="new-match-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<a href="/matching/team/team-list">팀매칭</a>
						<a href="/matching/mercenary/mercenary-list">용병매칭</a>
					</div>
				</div>
				<div class="matching-write">
					<a href="team-match-form"><i class="fas fa-pencil-alt"></i>글쓰기</a>
				</div>
				<div class="search-wrap">
					<form action="/matching/team/team-match-search" method="post">
						<dl>
							<dt>경기지역</dt>
							<dd class="local">
								<label><input type="radio" value="LC11" name="localCode">서울</label>
								<label><input type="radio" value="LC31" name="localCode">경기</label>
								<label><input type="radio" value="LC32" name="localCode">강원</label>
								<label><input type="radio" value="LC33" name="localCode">충청</label>
								<label><input type="radio" value="LC35" name="localCode">전라</label>
								<label><input type="radio" value="LC39" name="localCode">제주</label>
								<label><input type="radio" value="LC37" name="localCode">경상</label>
							</dd>
						</dl>
						<dl>
							<dt>기간</dt>
							<dd><input type="date" name="date"></dd>
						</dl>
						<dl>
							<dt>상대팀 실력</dt>
							<dd class="level-dd">
								<label><input type="radio" name="level" value="high">상</label>
								<label><input type="radio" name="level" value="middle">중</label>
								<label><input type="radio" name="level" value="low">하</label>
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
				<c:forEach var="matchBox" items="${matchList}">
				<div class="match-box">
					<div class="tit-area">	
						<div class="tit-info">
							<div class="state recruiting">모집중</div>
							<div class="tit">
								<strong>${matchBox.getTitle()}</strong>
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
							<li><span class="tit">지역</span>[${matchBox.getLocalCode()}] ${matchBox.getAddress()} 
								<a class="view-map" onclick="window.open('https://map.kakao.com/link/search/${matchBox.getAddress()}', 'pop01', 'top=10, left=10, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no');"> 
									<i class="fas fa-map-marker-alt"></i> 지도보기
								</a>
							</li>
							<li><span class="tit">매치날짜</span>${matchBox.getMatchDate()} ${matchBox.getMatchTime()} </li>
						</ul>
						<ul>
							<li><span class="tit">매치방식</span>${matchBox.getTmMatch()}:${matchBox.getTmMatch()}</li>
							<li><span class="tit">실력</span>${matchBox.getGrade()}</li>
							<li><span class="tit">구장비</span>${matchBox.getExpense()}원</li>
						</ul>
						<div class="txt">${matchBox.getContent()}</div>
					</div>
				</div><!-- End 매치 박스 -->
				
				</c:forEach>
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
				</div><!-- End 매치 박스 -->
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<script>

(() => {
	document.querySelector('.local').addEventListener('click',e=>{
		let checkLocal = document.getElementsByName('localCode');
		
		checkLocal.forEach(check => {
			if (check.checked) {
				check.parentNode.className = 'selected';
			}else{
				check.parentNode.className = '';
			}
			
		})
	})
	
	document.querySelector('.level-dd').addEventListener('click',e => {
		let checkLevel = document.getElementsByName('level');
		checkLevel.forEach(check => {
			if (check.checked) {
				check.parentNode.className = 'selected';
			}else{
				check.parentNode.className = '';
			}
			
		})
	})
	
	
	let popup = document.querySelectorAll(".profile-name");

	popup.forEach(element => {
		element.addEventListener('click', () => {
			document.querySelector(".popup-teaminfo-wrap").style.display = 'block';
		})
	});

	document.querySelector(".popup-close").addEventListener('click',() =>{
		document.querySelector(".popup-teaminfo-wrap").style.display = 'none';
	})
	
})(); 
</script>
</body>
</html>