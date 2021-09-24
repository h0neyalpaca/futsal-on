<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<button class="close-btn">확인</button>
	</div>
</div>

<c:if test="${not empty param.err}">
	<div class="pop-msg-wrap" style="display:flex;">
		<div class="pop-msg">
			<p><i class="fas fa-exclamation-triangle"></i><br>팀이 존재하지 않거나 이미 삭제된 팀입니다.</p>
			<button onclick="btnClose();">확인</button>
		</div>
	</div>
</c:if>

<c:if test="${not empty param.result}">
	<div class="pop-msg-wrap" style="display:flex;">
		<div class="pop-msg">
			<p>
				<i class="fas fa-check-circle"></i><br>
				<c:if test="${param.result==1}">팀 가입이 성공적으로 완료되었습니다.<br>${team.tmName} 팀의 팀원이 되신 것을 축하드립니다!</c:if>
				<c:if test="${param.result==2}">팀이 성공적으로 생성되었습니다.<br>${team.tmName} 팀에서 많은 활동 부탁드릴게요!</c:if>
			</p>
			<button onclick="btnClose();">확인</button>
		</div>
	</div>
</c:if>
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
	let breakTeam = (userId) => {
		drawQuestion('정말로 팀을 해체하시겠습니까?','breakFunc();');
	}
	let leaveTeam = (userId) => {
		drawQuestion('정말로 팀을 탈퇴하시겠습니까?','leaveFunc();');
	}
	let drawQuestion = (txt,func) => {
		document.querySelector('.pop-msg-wrap.question').style.display='flex';
		document.querySelector('.pop-msg-wrap.question p').innerHTML='<i class="fas fa-exclamation-triangle"></i><br>'+txt;
		document.querySelector('.submit-btn').setAttribute('onClick',func);
		document.querySelector('.submit-btn').innerHTML='확인';
	}
	
	//팀원 등급 변경
	let grade = (userId, grade) => {
		let xhr = new XMLHttpRequest();
		xhr = xmlRequest('POST','manage-grade','');
		xhr.send('userId='+userId+'&grade='+grade);
	}
	//팀 위임
	let delegation = (userId) => {
		let xhr = new XMLHttpRequest();
		xhr = xmlRequest('POST','manage-delegation','');
		xhr.send('userId='+userId);
	}
	//팀 방출
	let expulsion = (userId) => {
		let xhr = new XMLHttpRequest();
		xhr = xmlRequest('POST','manage-expulsion','');
		xhr.send('userId='+userId);
	}
	//팀 해체
	let breakFunc = () => {
		let xhr = new XMLHttpRequest();
		xhr = xmlRequest('POST','break-team','${request.contextPath}/team/main');
		xhr.send('tmCode='+'${team.tmCode}');
	}
	//팀 탈퇴
	let leaveFunc = () => {
		let xhr = new XMLHttpRequest();
		xhr = xmlRequest('POST','leave-team','${request.contextPath}/team/main');
		xhr.send('userId='+'${authentication.userId}');
	}
	//메서드방식,리퀘스트URL,완료후URL
	let xmlRequest = (method,rqstUrl,rtnUrl) => {
		let xhr = new XMLHttpRequest();
		xhr.open(method,'${request.contextPath}/team/managing/'+rqstUrl,true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = () => {
			if (xhr.readyState == XMLHttpRequest.DONE) {
				if (xhr.status == 200) {
					drawAnswer(xhr.responseText,rtnUrl);
				} else if (xhr.status == 400) {
					drawAnswer(xhr.responseText,'');
				} else {
					drawAnswer(xhr.responseText,'');
				}
			}
		};
		return xhr;
	}
	let drawAnswer = (txt,url) => {
		document.querySelector('.pop-msg-wrap.question').style.display='none';
		document.querySelector('.pop-msg-wrap.answer').style.display='flex';
		document.querySelector('.pop-msg-wrap.answer p').innerHTML='<i class="fas fa-check-circle"></i><br>'+txt;
		document.querySelector('.close-btn').setAttribute('onClick','location.href="'+url+'"');
	}
	
	let btnClose = () => {
		let msgWrap = document.querySelectorAll('.pop-msg-wrap');
		msgWrap.forEach(e=>{
			e.style.display='none';		
		});
	}
</script>