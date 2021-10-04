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
					<ul class="board-cate">
						<li id="tmBrdBtn" class="selected">내가 작성한 팀 매치</li>
						<li id="mcBrdBtn">내가 작성한 용병 매치</li>
						<li id="appliBtn">내가 신청한 매치</li>
					</ul>
					<%-- tmBrds --%>
					<div id="tmBrds">
						<c:if test="${empty tmBoards}">
							<div style="padding:40px;text-align:center;">작성하신 글이 없습니다.</div>
						</c:if>
						<c:forEach items="${tmBoards}" var="tmBoards" varStatus="status">
							<div class="match-box use-myteam">
								<div class="tit-area">
									<div class="tit-info">
										<div class="state ${tmBoards.state eq 0 and tmBoards.matchSchedule-100 ge nowDate?'recruiting':'end'}">${tmBoards.state eq 0 and tmBoards.matchSchedule-100 ge nowDate?'모집중':'모집완료'}</div>
										<div class="tit">
											<strong>${tmBoards.title}</strong>
												별점 
												<c:forEach var="i" begin="1" end="${tmBoards.tmRating}">
													<i class="fas fa-star full-star" style="display:inline-block;margin-right:-4px;color:#aaa;"></i>
												</c:forEach>
												<c:if test="${tmBoards.tmRating%1!=0}">
													<i class="fas fa-star-half-alt" style="display:inline-block;margin-right:-4px;color:#aaa;"></i>
												</c:if>
												<c:forEach var="i" begin="1" end="${5-tmBoards.tmRating}">
													<i class="far fa-star" style="display:inline-block;margin-right:-3px;color:#aaa;"></i>
												</c:forEach>
												&nbsp;&nbsp;전적 ${tmBoards.gameCnt}전 ${tmBoards.tmWin}승  ${tmBoards.tmLose}패
										</div>
									</div>
									<div class="profile_n_appli">
										<div class="profile">
											<c:if test="${tmBoards.filePath != null}">
												<div class="profile-img" style="background:url('${request.contextPath}/img/team/${tmBoards.filePath}') center center;background-size:cover;"></div>
											</c:if>
											<c:if test="${tmBoards.filePath == null}">
												<div class="profile-img" style="background:url('${request.contextPath}/img/team/no-img.jpg') center center;background-size:cover;"></div>
											</c:if>
											<div class="profile-name" onclick="teamInfo('${tmBoards.tmCode}')">${tmBoards.tmName}<span><i class="fas fa-search"></i>정보보기</span></div>
										</div>
										<c:if test="${authentication.grade=='ME03'}">
											<c:if test="${tmBoards.state == 0 and tmBoards.matchSchedule ge nowDate}">
												<div class="btn-appli" onclick="teamMatchingModify('${tmBoards.mmIdx}')">수정하기</div>
												<div class="btn-appli" onclick="teamMatchingDel('${tmBoards.mmIdx}')">삭제하기</div>
											</c:if>
											<c:if test="${tmBoards.state == 1 and tmBoards.matchSchedule-400 ge nowDate}">
												<div class="btn-appli" style="background:#bbb;cursor:default;">수정불가</div>
												<div class="btn-appli" onclick="teamMatchingCancel('${tmBoards.mmIdx}')">취소하기</div>
											</c:if>
											<c:if test="${tmBoards.matchSchedule lt nowDate or (tmBoards.state == 1 and tmBoards.matchSchedule-400 lt nowDate)}">
												<div class="btn-appli" style="background:#bbb;cursor:default;">수정불가</div>
												<div class="btn-appli" style="background:#bbb;cursor:default;">취소불가</div>
											</c:if>
										</c:if>
									</div>
								</div>
								<div class="match-detail">
									<ul>
										<li><span class="tit">지역</span>[${tmBoards.localCode eq 'LC11'?'서울':tmBoards.localCode eq 'LC31'?'경기':tmBoards.localCode eq 'LC32'?'강원':tmBoards.localCode eq 'LC33'?'충청':tmBoards.localCode eq 'LC35'?'전라':tmBoards.localCode eq 'LC39'?'제주':'경상'}] ${tmBoards.address} 
											<a class="view-map" onclick="window.open('https://map.kakao.com/link/search/${tmBoards.address}', 'pop01', 'top=10, left=10, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no');"> 
												<i class="fas fa-map-marker-alt"></i> 지도보기
											</a>
										</li>
										<li><span class="tit">매치날짜</span>${tmBoards.matchDate} ${tmBoards.matchTime} </li>
									</ul>
									<ul>
										<li><span class="tit">매치방식</span>${tmBoards.tmMatch}:${tmBoards.tmMatch}</li>
										<li><span class="tit">실력</span>${tmBoards.grade eq 'high'?'상':tmBoards.grade eq 'middle'?'중':'하'}</li>
										<li><span class="tit">구장비</span>${tmBoards.expense}원</li>
									</ul>
									<div class="txt">${tmBoards.content}</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<%-- mcBrds --%>
					<div id="mcBrds" style="display:none;">
						<c:if test="${empty mcBoards}">
							<div style="padding:40px;text-align:center;">작성하신 글이 없습니다.</div>
						</c:if>
						<c:forEach items="${mcBoards}" var="mcBoards" varStatus="status">
							<div class="match-box use-myteam">
								<div class="tit-area">
									<div class="tit-info">
										<div class="state ${mcBoards.state eq 0 and mcBoards.matchSchedule-100 ge nowDate?'recruiting':'end'}">${mcBoards.state eq 0 and mcBoards.matchSchedule-100 ge nowDate?'모집중':'모집완료'}</div>
										<div class="tit">
											<strong>${mcBoards.title}</strong>
												별점 
												<c:forEach var="i" begin="1" end="${mcBoards.tmRating}">
													<i class="fas fa-star full-star" style="display:inline-block;margin-right:-4px;color:#aaa;"></i>
												</c:forEach>
												<c:if test="${mcBoards.tmRating%1!=0}">
													<i class="fas fa-star-half-alt" style="display:inline-block;margin-right:-4px;color:#aaa;"></i>
												</c:if>
												<c:forEach var="i" begin="1" end="${5-mcBoards.tmRating}">
													<i class="far fa-star" style="display:inline-block;margin-right:-3px;color:#aaa;"></i>
												</c:forEach>
												&nbsp;&nbsp;전적 ${mcBoards.gameCnt}전 ${mcBoards.tmWin}승  ${mcBoards.tmLose}패
										</div>
									</div>
									<div class="profile_n_appli">
										<div class="profile">
											<c:if test="${mcBoards.filePath != null}">
												<div class="profile-img" style="background:url('${request.contextPath}/img/team/${mcBoards.filePath}') center center;background-size:cover;"></div>
											</c:if>
											<c:if test="${mcBoards.filePath == null}">
												<div class="profile-img" style="background:url('${request.contextPath}/img/team/no-img.jpg') center center;background-size:cover;"></div>
											</c:if>
											<div class="profile-name" onclick="teamInfo('${mcBoards.tmCode}')">${mcBoards.tmName}<span><i class="fas fa-search"></i>정보보기</span></div>
										</div>
										<c:if test="${authentication.grade=='ME03'}">
											<c:if test="${mcBoards.state == 0 and mcBoards.matchSchedule ge nowDate}">
												<div class="btn-appli" onclick="mcMatchingModify('${mcBoards.mmIdx}')">수정하기</div>
												<div class="btn-appli" onclick="mcMatchingDel('${mcBoards.mmIdx}')">삭제하기</div>
											</c:if>
											<c:if test="${mcBoards.state == 1 and mcBoards.matchSchedule-400 ge nowDate}">
												<div class="btn-appli" style="background:#bbb;cursor:default;">수정불가</div>
												<div class="btn-appli" onclick="teamMatchingCancel('${mcBoards.mmIdx}')">취소하기</div>
											</c:if>
											<c:if test="${mcBoards.matchSchedule lt nowDate or (mcBoards.state == 1 and mcBoards.matchSchedule-400 lt nowDate)}">
												<div class="btn-appli" style="background:#bbb;cursor:default;">수정불가</div>
												<div class="btn-appli" style="background:#bbb;cursor:default;">취소불가</div>
											</c:if>
										</c:if>
									</div>
								</div>
								<div class="match-detail">
									<ul>
										<li><span class="tit">지역</span>[${mcBoards.localCode eq 'LC11'?'서울':mcBoards.localCode eq 'LC31'?'경기':mcBoards.localCode eq 'LC32'?'강원':mcBoards.localCode eq 'LC33'?'충청':mcBoards.localCode eq 'LC35'?'전라':mcBoards.localCode eq 'LC39'?'제주':'경상'}] ${mcBoards.address} 
											<a class="view-map" onclick="window.open('https://map.kakao.com/link/search/${mcBoards.address}', 'pop01', 'top=10, left=10, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no');"> 
												<i class="fas fa-map-marker-alt"></i> 지도보기
											</a>
										</li>
										<li><span class="tit">매치날짜</span>${mcBoards.matchDate} ${mcBoards.matchTime} </li>
									</ul>
									<ul>
										<li><span class="tit">매치방식</span>${mcBoards.tmMatch}:${mcBoards.tmMatch}</li>
										<li><span class="tit">실력</span>${mcBoards.grade eq 'high'?'상':mcBoards.grade eq 'middle'?'중':'하'}</li>
										<li><span class="tit">구장비</span>${mcBoards.expense}원</li>
										<li><span class="tit">모집인원</span>${mcBoards.matchNum}명 남음</li>
									</ul>
									<div class="txt">${mcBoards.content}</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<%-- appliBrds --%>
					<div id="appliBrds" style="display:none;">
						<c:if test="${empty appliBoards}">
							<div style="padding:40px;text-align:center;">작성하신 글이 없습니다.</div>
						</c:if>
						<c:forEach items="${appliBoards}" var="appliBoards" varStatus="status">
							<div class="match-box use-myteam">
								<div class="tit-area">
									<div class="tit-info">
										<div class="state ${appliBoards.state eq 0 and appliBoards.matchSchedule-100 ge nowDate?'recruiting':'end'}">${appliBoards.state eq 0 and appliBoards.matchSchedule-100 ge nowDate?'모집중':'모집완료'}</div>
										<div class="tit">
											<strong>${appliBoards.title}</strong>
												별점 
												<c:forEach var="i" begin="1" end="${appliBoards.tmRating}">
													<i class="fas fa-star full-star" style="display:inline-block;margin-right:-4px;color:#aaa;"></i>
												</c:forEach>
												<c:if test="${appliBoards.tmRating%1!=0}">
													<i class="fas fa-star-half-alt" style="display:inline-block;margin-right:-4px;color:#aaa;"></i>
												</c:if>
												<c:forEach var="i" begin="1" end="${5-appliBoards.tmRating}">
													<i class="far fa-star" style="display:inline-block;margin-right:-3px;color:#aaa;"></i>
												</c:forEach>
												&nbsp;&nbsp;전적 ${appliBoards.gameCnt}전 ${appliBoards.tmWin}승  ${appliBoards.tmLose}패
										</div>
									</div>
									<div class="profile_n_appli">
										<div class="profile">
											<c:if test="${appliBoards.filePath != null}">
												<div class="profile-img" style="background:url('${request.contextPath}/img/team/${appliBoards.filePath}') center center;background-size:cover;"></div>
											</c:if>
											<c:if test="${appliBoards.filePath == null}">
												<div class="profile-img" style="background:url('${request.contextPath}/img/team/no-img.jpg') center center;background-size:cover;"></div>
											</c:if>
											<div class="profile-name" onclick="teamInfo('${appliBoards.tmCode}')">${appliBoards.tmName}<span><i class="fas fa-search"></i>정보보기</span></div>
										</div>
										<c:if test="${authentication.grade=='ME03'}">
											<c:if test="${appliBoards.matchSchedule-400 ge nowDate}">
												<div class="btn-appli" onclick="teamMatchingCancel('${appliBoards.mmIdx}','${appliBoards.tmCode}','${team.tmCode}')">취소하기</div>
											</c:if>
											<c:if test="${appliBoards.matchSchedule-400 lt nowDate}">
												<div class="btn-appli" style="background:#bbb;cursor:default;">취소불가</div>
											</c:if>
										</c:if>
									</div>
								</div>
								<div class="match-detail">
									<ul>
										<li><span class="tit">지역</span>[${appliBoards.localCode eq 'LC11'?'서울':appliBoards.localCode eq 'LC31'?'경기':appliBoards.localCode eq 'LC32'?'강원':appliBoards.localCode eq 'LC33'?'충청':appliBoards.localCode eq 'LC35'?'전라':appliBoards.localCode eq 'LC39'?'제주':'경상'}] ${appliBoards.address} 
											<a class="view-map" onclick="window.open('https://map.kakao.com/link/search/${appliBoards.address}', 'pop01', 'top=10, left=10, width=1000, height=600, status=no, menubar=no, toolbar=no, resizable=no');"> 
												<i class="fas fa-map-marker-alt"></i> 지도보기
											</a>
										</li>
										<li><span class="tit">매치날짜</span>${appliBoards.matchDate} ${appliBoards.matchTime} </li>
									</ul>
									<ul>
										<li><span class="tit">매치방식</span>${appliBoards.tmMatch}:${appliBoards.tmMatch}</li>
										<li><span class="tit">실력</span>${appliBoards.grade eq 'high'?'상':appliBoards.grade eq 'middle'?'중':'하'}</li>
										<li><span class="tit">구장비</span>${appliBoards.expense}원</li>
									</ul>
									<div class="txt">${appliBoards.content}</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%@ include file="/WEB-INF/views/team/include/team-pop.jsp" %>
<%@ include file="/WEB-INF/views/pop/team-info-pop.jsp" %>

<script type="text/javascript" src="${request.contextPath}/resources/js/team/managing.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/js/popup/popup.js"></script>

<script type="text/javascript">
	document.querySelector('#tmBrdBtn').addEventListener('click',(e)=>{
		e.target.classList.add('selected');
		document.querySelector('#appliBtn').classList.remove('selected');
		document.querySelector('#mcBrdBtn').classList.remove('selected');
		document.querySelector('#tmBrds').style.display='block';
		document.querySelector('#appliBrds').style.display='none';
		document.querySelector('#mcBrds').style.display='none';
	});
	document.querySelector('#mcBrdBtn').addEventListener('click',(e)=>{
		e.target.classList.add('selected');
		document.querySelector('#appliBtn').classList.remove('selected');
		document.querySelector('#tmBrdBtn').classList.remove('selected');
		document.querySelector('#mcBrds').style.display='block';
		document.querySelector('#tmBrds').style.display='none';
		document.querySelector('#appliBrds').style.display='none';
	});
	document.querySelector('#appliBtn').addEventListener('click',(e)=>{
		e.target.classList.add('selected');
		document.querySelector('#mcBrdBtn').classList.remove('selected');
		document.querySelector('#tmBrdBtn').classList.remove('selected');
		document.querySelector('#appliBrds').style.display='block';
		document.querySelector('#tmBrds').style.display='none';
		document.querySelector('#mcBrds').style.display='none';
	});
	
	let teamMatchingModify = (idx) => {
		drawQuestion('팀 매치글을 수정하시겠습니까?','location.href="/matching/team/team-modify?matchIdx='+idx+'"');
	}
	
	let mcMatchingModify = (idx) => {
		drawQuestion('용병 매치글을 수정하시겠습니까?','location.href="/matching/mercenary/mercenary-modify?matchIdx='+idx+'"');
	}
	
	let teamMatchingCancel = (idx,hostCode,rivalCode) => {
		drawQuestion('매치글을 취소하시겠습니까?','location.href="/matching/team/team-modify-register?matchIdx='+idx+'&hostCode='+hostCode+'&rivalCode='+rivalCode+'&modify=취소"');
	}
	
	let teamMatchingDel = (idx,hostCode,rivalCode) => {
		drawQuestion('팀 매치글을 삭제하시겠습니까?','location.href="/matching/team/team-modify-register?matchIdx='+idx+'&modify=삭제"');
	}
	
	let mcMatchingDel = (idx) => {
		drawQuestion('용병 매치글을 삭제하시겠습니까?','location.href="/matching/team/mercenary-modify-register?matchIdx='+idx+'&modify=삭제"');
	}
</script>
</body>
</html>