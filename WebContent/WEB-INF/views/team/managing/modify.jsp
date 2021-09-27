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
				<form action="${request.contextPath}/team/managing/modify-team" method="post">
					<table class="team-create-form">
						<tr><th>팀 이름</th><td>${team.tmName}</td></tr>
						<c:if test="${authentication.grade=='ME03'}">
							<tr><th>팀 코드</th><td>${team.tmCode}</td></tr>
						</c:if>
						<tr>
							<th>팀 사진</th>
							<td>
								<input type="file" name="teamFile" id="teamFile" />
								<span class="msg">500MB 이하의 jpg, gif, png</span>
								<div class="team-file leader">
									<a class="view-file"><i class="fas fa-eye"></i> team_file_name.jpg</a>
									<a class="del-file"><i class="fas fa-trash-alt"></i> 팀 사진 삭제</a>
								</div>
							</td>
						</tr>
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
									${team.localCode}
								</c:if>
							</td>
						</tr>
						<tr>
							<th>소개글</th>
							<td>
								<c:if test="${authentication.grade=='ME03'}">
									<textarea placeholder="팀 소개 내용을 입력해주세요.">${team.tmInfo}</textarea>
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
</body>
</html>