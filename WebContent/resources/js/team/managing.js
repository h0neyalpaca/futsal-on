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
let drawQuestion = (txt,func) => {
	document.querySelector('.pop-msg-wrap.question').style.display='flex';
	document.querySelector('.pop-msg-wrap.question p').innerHTML='<i class="fas fa-question-circle"></i><br>'+txt;
	document.querySelector('.submit-btn').setAttribute('onClick',func);
	document.querySelector('.submit-btn').innerHTML='확인';
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