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
					<%@ include file="/WEB-INF/views/team/managing/team_tab.jsp" %>
					<table class="team-member-form">
						<tr>
							<th>NO</th>
							<th>이름</th>
							<th>팀원 등급</th>
							<c:if test="${authentication.grade=='ME03'}">
								<th>팀장 위임</th>
								<th>팀원 추방</th>
							</c:if>
						</tr>
						<c:forEach items="${tmMembers}" var="tmMembers" varStatus="status">
						<tr>
							<td><c:out value="${status.count}" /></td>
							<td><c:out value="${tmMembers.userId}" /></td>
							<td>
								<c:if test="${authentication.grade=='ME03'&&tmMembers.grade!='ME03'}">
									<div class="selectbox">
										<select name="grdOpt">
											<option value="ME02" ${tmMembers.grade eq 'ME02'?'selected':''}>매니저</option>
											<option value="ME01" ${tmMembers.grade eq 'ME01'?'selected':''}>팀원</option>
										</select>
									</div>
									<button class="btn-change-grade" onclick="manageGrade(this, '${tmMembers.userId}');return false;">변경</button>
								</c:if>
								<c:if test="${authentication.grade!='ME03'||tmMembers.grade=='ME03'}">
									<c:out value="${tmMembers.grade eq 'ME03'?'팀장':'ME02'?'매니저':'팀원'}" />
								</c:if>
							</td>
							<c:if test="${authentication.grade=='ME03'}">
								<td><c:if test="${tmMembers.grade!='ME03'}"><button onclick="manageDelegation('${tmMembers.userId}');">팀장 위임</button></c:if></td>
								<td><c:if test="${tmMembers.grade!='ME03'}"><button onclick="manageExpulsion('${tmMembers.userId}');">추방</button></c:if></td>
							</c:if>
						</tr>
						</c:forEach>
					</table>
					
					<form name="modfrm" method="post">
						<input type="hidden" name="userId">
						<input type="hidden" name="grade">
					</form>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>

<div class="pop-msg-wrap question">
	<div class="pop-msg">
		<p></p>
		<button class="cancel-btn" onclick="btnClose();">취소</button>
		<button class="submit-btn" onclick=""></button>
	</div>
</div>
<div class="pop-msg-wrap answer">
	<div class="pop-msg">
		<p></p>
		<button class="close-btn" onclick='location.href="${request.contextPath}/team/managing/manage"'>확인</button>
	</div>
</div>
<script type="text/javascript">
let manageGrade = (e, userId) => {
	let grade = e.parentNode.childNodes[1].childNodes[1].value;
	drawQuestion(userId+'님의 등급을 변경하시겠습니까?','grade("'+userId+'","'+grade+'");');
}
let manageDelegation = (userId) => {
	drawQuestion(userId+'님에게 팀의 모든 권한을 위임하시겠습니까?','delegation("'+userId+'");');
}
let manageExpulsion = (userId) => {
	drawQuestion(userId+'님을 추방하시겠습니까?','expulsion("'+userId+'");');
}
let drawQuestion = (txt,func) => {
	document.querySelector('.pop-msg-wrap.question').style.display='flex';
	document.querySelector('.pop-msg-wrap.question p').innerHTML='<i class="fas fa-exclamation-triangle"></i><br>'+txt;
	document.querySelector('.submit-btn').setAttribute('onClick',func);
	document.querySelector('.submit-btn').innerHTML='확인';
}

let grade = (userId, grade) => {
	let xhr = new XMLHttpRequest();
	xhr.open('POST','${request.contextPath}/team/managing/manage-grade',true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send('userId='+userId+'&grade='+grade);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			if (xhr.status == 200) {
				drawAnswer(userId+'님의 등급을 변경하였습니다.');
			} else if (xhr.status == 400) {
				alert('There was an error 400');
			} else {
				alert('something else other than 200 was returned');
			}
		}
	};
}
let delegation = (userId) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','manage-delegation',userId);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			if (xhr.status == 200) {
				drawAnswer(userId+'님에게 팀장을 위임하였습니다.');
			} else if (xhr.status == 400) {
				alert('There was an error 400');
			} else {
				alert('something else other than 200 was returned');
			}
		}
	};
}
let expulsion = (userId) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','manage-expulsion',userId);
	xhr.onreadystatechange = () => {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			if (xhr.status == 200) {
				drawAnswer(userId+'님을 추방하였습니다.');
			} else if (xhr.status == 400) {
				alert('There was an error 400');
			} else {
				alert('something else other than 200 was returned');
			}
		}
	};
}
let xmlRequest = (method,url,userId) => {
	let xhr = new XMLHttpRequest();
	xhr.open(method,'${request.contextPath}/team/managing/'+url,true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send('userId='+userId);
	return xhr;
}
let drawAnswer = (txt) => {
	document.querySelector('.pop-msg-wrap.question').style.display='none';
	document.querySelector('.pop-msg-wrap.answer').style.display='flex';
	document.querySelector('.pop-msg-wrap.answer p').innerHTML='<i class="fas fa-check-circle"></i><br>'+txt;
}
let btnClose = () => {
	let msgWrap = document.querySelectorAll('.pop-msg-wrap');
	msgWrap.forEach(e=>{
		e.style.display='none';		
	});
}
</script>
</body>
</html>