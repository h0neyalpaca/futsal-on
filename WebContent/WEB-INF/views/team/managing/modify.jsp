<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<section>
	<div class="section">
		<div class="myteam-wrap">
			<h2><i class="far fa-futbol"></i> 나의 팀</h2>
			<div class="myteam-con">
				<%@ include file="/WEB-INF/views/team/include/team_tab.jsp" %>
				<c:if test="${authentication.grade!='ME03'}">
					<div class="team-img" style="margin-bottom:30px;"></div>
				</c:if>
				<form id="frm_create-team" action="${request.contextPath}/team/managing/modify-func" method="post" enctype="multipart/form-data">
					<table class="team-create-form">
						<tr><th>팀 이름</th><td>${team.tmName}</td></tr>
						<c:if test="${authentication.grade=='ME03'}">
							<tr><th>팀 코드</th><td>${team.tmCode}</td></tr>
							<tr>
								<th>팀 사진</th>
								<td>
									<input type="file" name="teamFile" id="teamFile" onchange="fileCheck(this.files)">
									<span class="msg">JPG/GIF/PNG 파일만 업로드 가능</span>
									<c:if test="${file.tmCode != null}">
										<div class="team-file leader">
											<a class="view-file" target="_blank" href="/img/team/${file.savePath}${file.renameFileName}"><i class="fas fa-eye"></i> 현재 팀 이미지 :  ${file.originFileName}</a>
											<%--a class="del-file"><i class="fas fa-trash-alt"></i> 팀 사진 삭제</a --%>
										</div>
									</c:if>
								</td>
							</tr>
						</c:if>
						<tr><th>팀 전적</th><td>${team.gameCnt}전 ${team.tmWin}승 ${team.tmLose}패</td></tr>
						<tr><th>팀 평점</th><td>${team.tmScore}점</td></tr>
						<tr>
							<th>실력</th>
							<td>
								<c:if test="${authentication.grade=='ME03'}">
									<label><input type="radio" name="tmGrade" value="상" ${team.tmGrade eq '상'?'checked':''} /> 상</label>
									<label><input type="radio" name="tmGrade" value="중" ${team.tmGrade eq '중'?'checked':''} /> 중</label>
									<label><input type="radio" name="tmGrade" value="하" ${team.tmGrade eq '하'?'checked':''} /> 하</label>									
								</c:if>
								<c:if test="${authentication.grade!='ME03'}">
									${team.tmGrade}
								</c:if>
							</td>
						</tr>
						<tr>
							<th>활동지역</th>
							<td>
								<c:if test="${authentication.grade=='ME03'}">
									<label><input type="radio" name="localCode" value="LC11" ${team.localCode eq 'LC11'?'checked':''} /> 서울</label>
									<label><input type="radio" name="localCode" value="LC31" ${team.localCode eq 'LC31'?'checked':''} /> 경기</label>
									<label><input type="radio" name="localCode" value="LC32" ${team.localCode eq 'LC32'?'checked':''} /> 강원</label>
									<label><input type="radio" name="localCode" value="LC33" ${team.localCode eq 'LC33'?'checked':''} /> 충청</label>
									<label><input type="radio" name="localCode" value="LC35" ${team.localCode eq 'LC35'?'checked':''} /> 전라</label>
									<label><input type="radio" name="localCode" value="LC39" ${team.localCode eq 'LC39'?'checked':''} /> 제주</label>
									<label><input type="radio" name="localCode" value="LC37" ${team.localCode eq 'LC37'?'checked':''} /> 경상</label>
								</c:if>
								<c:if test="${authentication.grade!='ME03'}">
									${team.localCode eq 'LC11'?'서울':team.localCode eq 'LC31'?'경기':team.localCode eq 'LC32'?'강원':team.localCode eq 'LC33'?'충청':team.localCode eq 'LC35'?'전라':team.localCode eq 'LC39'?'제주':'경상'}
								</c:if>
							</td>
						</tr>
						<tr>
							<th>소개글</th>
							<td>
								<c:if test="${authentication.grade=='ME03'}">
									<textarea name="tmInfo" placeholder="팀 소개 내용을 입력해주세요.">${team.tmInfo}</textarea>
								</c:if>
								<c:if test="${authentication.grade!='ME03'}">
									${team.tmInfo}
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<c:if test="${authentication.grade=='ME03'}">
								<div class="btn-team-create">
									<input type="submit" value="팀 변경하기" />
								</div>
								</c:if>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%@ include file="/WEB-INF/views/team/include/team-pop.jsp" %>
<script type="text/javascript" src="${request.contextPath}/resources/js/team/modForm.js"></script>
<c:if test="${authentication.grade!='ME03'}">
<script type="text/javascript">
	let tmImg = document.querySelector('.team-img');
	tmImg.style.background='url("/img/team/no-img.jpg") center center';
	<c:if test="${file.tmCode != null}">
		tmImg.style.background='url("/img/team/${file.savePath}${file.renameFileName}") center center';
	</c:if>
	tmImg.style.backgroundSize='cover';
</script>
</c:if>
</body>
</html>