<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${request.contextPath}/resources/css/matching/matching-team.css" />
</head>
<body>
	<c:if test="${not empty param.err}">
		<script type="text/javascript">
				alert("같은 팀을 상대로 신청하실 수 없습니다.");
		</script>
	</c:if>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<section>
		
		<div class="section">
			<div class="new-match-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<a href="/matching/team/team-list">팀매칭</a> <a
							href="/matching/mercenary/mercenary-list">용병매칭</a>
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
							<dd>
								<input type="date" name="date">
							</dd>
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
				</div>
				<!-- End search wrap -->
				<!-- <h2>새로 모집중인 매치</h2> -->
				<!-- 하나의 매치 박스 -->
				<div class="search-role-wrap">
					<div class="search-role">
						<a href="#">최신순</a> <a href="#">별점순</a>
					</div>
				</div>
				
				<c:forEach var="matchBox" items="${matchList}">
				<%@ include file="/WEB-INF/views/pop/team-info.jsp"%>
					<div class="match-box">
						<div class="tit-area">
							<div class="tit-info">
								<c:choose>
									<c:when test="${matchBox.getState() == 1}">
										<div class="state end">모집완료</div>
									</c:when>
									<c:when test="${matchBox.getState() == 0}">
										<div class="state recruiting">모집중</div>
									</c:when>
								</c:choose>
								<div class="tit">
									<strong>${matchBox.getTitle()}</strong>
									<c:choose>
										<c:when test="${matchBox.getTmScore() == 5}">
										별점 ★★★★★&nbsp;&nbsp;&nbsp;
										</c:when>
										<c:when test="${matchBox.getTmScore() == 4}">
								      	 별점 ★★★★ &nbsp;&nbsp;&nbsp;&nbsp;
								         </c:when>
										<c:when test="${matchBox.getTmScore() == 3}">
								               별점 ★★★&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								         </c:when>
										<c:when test="${matchBox.getTmScore() == 2}">
								              별점 ★★&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								         </c:when>
										<c:when test="${matchBox.getTmScore() == 1}">
								       	별점 ★&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								         </c:when>
									</c:choose>
									<c:set var="lose"
										value="${matchBox.getGameCnt() - matchBox.getTmWin()}"></c:set>
									전적 ${matchBox.getGameCnt()}전${matchBox.getTmWin()}승<c:out value="${lose}"></c:out>패

								</div>
							</div>
							<!-- 팝업창 -->

							<!-- 팝업창 끝 -->
							<div class="profile_n_appli">
								<div class="profile">
									<div class="profile-img"></div>
									<div class="profile-name">
									${matchBox.getTmName()}
									<span>
										<i class="fas fa-search"></i>정보보기
									</span>
									</div>
								</div>
								<c:choose>
									<c:when test="${matchBox.getState() == 1}">
										<div class="btn-appli" onclick="expiration()">신청하기</div>
									</c:when>
									<c:when test="${matchBox.getState() == 0}">
										<div class="btn-appli" onclick="matchRequset(${matchBox.getMmIdx()},'${matchBox.getTmCode()}','${authentication.userId}','${matchBox.getMatchDate()}')">신청하기</div>
									</c:when>
								</c:choose>
								
								
							</div>
						</div>
						<div class="match-detail">
							<ul>
								<li><span class="tit">지역</span>[${matchBox.getLocalCode()}]
									${matchBox.getAddress()} <a class="view-map"
									onclick="window.open('https://map.kakao.com/link/search/${matchBox.getAddress()}', 'pop01', 'top=10, left=10, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no');">
										<i class="fas fa-map-marker-alt"></i> 지도보기
								</a></li>
								<li><span class="tit">매치날짜</span>${matchBox.getMatchDate()}
									${matchBox.getMatchTime()}</li>
							</ul>
							<ul>
								<li><span class="tit">매치방식</span>${matchBox.getTmMatch()}:${matchBox.getTmMatch()}</li>
								<li><span class="tit">실력</span> <c:choose>
										<c:when test="${matchBox.getGrade() eq 'high'}">
										상
										</c:when>
										<c:when test="${matchBox.getGrade() eq 'middle'}">
								      	중
								         </c:when>
										<c:when test="${matchBox.getGrade() eq 'low'}">
								               하
								         </c:when>
										<c:otherwise>${matchBox.getGrade()}</c:otherwise>
									</c:choose></li>
								<li><span class="tit">구장비</span>${matchBox.getExpense()}원</li>
							</ul>
							<div class="txt">${matchBox.getContent()}</div>
						</div>
					</div>
					<!-- End 매치 박스 -->

				</c:forEach>
				<!-- 하나의 매치 박스 -->
				
				<!-- End 매치 박스 -->
			</div>
		</div>
	</section>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<script type="text/javascript">
	let matchRequset = (idx,tmCode,user,date) => {
		if (window.confirm("매치를 신청하시겠습니까?")) {
			console.dir(tmCode);
			location.href="/matching/team/subscription?matchIdx="+idx+"&tmCode="+tmCode+"&userId="+user+"&matchDate="+date;
		}
	}
	
	let expiration = () =>{
		alert("모집이 완료된 매치입니다.");
	}
	
	
	</script>
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