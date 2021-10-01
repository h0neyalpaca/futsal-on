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
let updateWinner = (e, mgIdx, hostCode, rivalCode, tmCode) => {
	let score = e.parentNode.childNodes[1].childNodes[1].value;
	let winner = rivalCode;
	let loser = hostCode;
	if(tmCode == hostCode){
		if(score==='1'){
			winner = hostCode;
			loser = rivalCode;
		}
		console.dir(winner);
	} else{
		if(score==='2'){
			winner = hostCode;
			loser = rivalCode;
		}
	}
	drawQuestion('경기 결과를 저장하시겠습니까?','winnerFunc("'+mgIdx+'","'+winner+'","'+loser+'");');
}
let updateRslt = (e, mgIdx, hostCode, rivalCode, tmCode) => {
	let rating = e.parentNode.childNodes[1].childNodes[1].value;
	let target = '';
	if(tmCode == hostCode){
		target = 'rival';
	} else{
		target = 'host';
	}
	drawQuestion('상대팀 평가를 저장하시겠습니까?','rsltFunc("'+mgIdx+'","'+target+'","'+rating+'");');
}
let breakTeam = (tmCode) => {
	drawQuestion('정말로 팀을 해체하시겠습니까?','breakFunc("'+tmCode+'");');
}
let leaveTeam = (userId) => {
	drawQuestion('정말로 팀을 탈퇴하시겠습니까?','leaveFunc("'+userId+'");');
}
let drawQuestion = (txt,func) => {
	document.querySelector('.pop-msg-wrap.question').style.display='flex';
	document.querySelector('.pop-msg-wrap.question p').innerHTML='<i class="fas fa-question-circle"></i><br>'+txt;
	document.querySelector('.submit-btn').setAttribute('onClick',func);
	document.querySelector('.submit-btn').innerHTML='확인';
}
let grade = (userId, grade) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','manage-grade','');
	xhr.send('userId='+userId+'&grade='+grade);
}
let delegation = (userId) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','manage-delegation','');
	xhr.send('userId='+userId);
}
let winnerFunc = (mgIdx, winner,loser) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','update-winner','');
	xhr.send('mgIdx='+mgIdx+'&winner='+winner+'&loser='+loser);
}
let rsltFunc = (mgIdx, target, rating) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','update-rating','');
	xhr.send('mgIdx='+mgIdx+'&target='+target+'&rating='+rating);
}
let expulsion = (userId) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','manage-expulsion','');
	xhr.send('userId='+userId);
}
let breakFunc = (tmCode) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','break-team','/team/main');
	xhr.send('tmCode='+tmCode);
}
let leaveFunc = (userId) => {
	let xhr = new XMLHttpRequest();
	xhr = xmlRequest('POST','leave-team','/team/main');
	xhr.send('userId='+userId);
}
let xmlRequest = (method,rqstUrl,rtnUrl) => {
	let xhr = new XMLHttpRequest();
	xhr.open(method,'${request.contextPath}/team/managing/'+rqstUrl,true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onreadystatechange = () => {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			if (xhr.status == 200) {
				drawAnswer('<i class="fas fa-check-circle"></i><br>'+xhr.responseText,rtnUrl);
			} else if (xhr.status == 400) {
				drawAnswer('<i class="fas fa-times-circle"></i><br>'+xhr.responseText+'<br>오류코드:'+xhr.stateus,'');
			} else {
				drawAnswer('<i class="fas fa-times-circle"></i><br>'+xhr.responseText+'<br>오류코드:'+xhr.stateus,'');
			}
		}
	};
	return xhr;
}
let drawAnswer = (txt,url) => {
	document.querySelector('.pop-msg-wrap.question').style.display='none';
	document.querySelector('.pop-msg-wrap.answer').style.display='flex';
	document.querySelector('.pop-msg-wrap.answer p').innerHTML=txt;
	document.querySelector('.close-btn').setAttribute('onClick','location.href="'+url+'"');
}
let btnClose = () => {
	let msgWrap = document.querySelectorAll('.pop-msg-wrap');
	msgWrap.forEach(e=>{
		e.style.display='none';		
	});
}