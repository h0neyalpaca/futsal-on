let formCheck = () => {

	
	let localCode = document.getElementsByName('localCode');
	
	console.dir(localCode);
	let value = null;
	localCode.forEach((e) => {
		if (e.checked) {
			value = e.value;
			console.dir(e.checked);
		}
	})
	
	
	
	
	if (value == null) {
		
		document.querySelector('.validMsg').style.color = 'red';
		document.querySelector('#checkRegion').innerHTML = '<br>&nbsp;&nbsp;&nbsp;지역을 선택해주세요.';	
		return false;
	}
	
	let detailAddress = document.querySelector('.keyword').value;
	
	
	
	if (detailAddress == "") {
		document.querySelector('#checkAddress').style.color = 'red';
		document.querySelector('#checkAddress').innerHTML = '<br>&nbsp;&nbsp;상세주소를 입력해주세요.';	
		return false;
	}
	
	
	let size = document.getElementsByName('size');
	let checkSize = null;
	
	size.forEach((e) => {
		if (e.checked) {
			checkSize = e.value;
			
		}
	})

	if (checkSize == null) {

		document.querySelector('#checkMatchStyle').style.color = 'red';
		document.querySelector('#checkMatchStyle').innerHTML = '<br>&nbsp;&nbsp;매치방식을 입력하세요.';	
		return false;
	}
	
	let date = document.querySelector('#currentDate').value;	
	let time = document.querySelector('#currentTime').value;
	
	if(date == "" || time == ""){
		document.querySelector('#checkMatchDate').style.color = 'red';
		document.querySelector('#checkMatchDate').innerHTML = '<br>&nbsp;&nbsp;매치날짜를 입력하세요.';	
		return false;
	}
	let pay = document.querySelector('#pay').value;	
	
	
	if(pay == ""){
		
		document.querySelector('#checkMatchPay').style.color = 'red';
		document.querySelector('#checkMatchPay').innerHTML = '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;구장비를 입력하세요.';	
		return false;	
	}
	
	let level = document.getElementsByName('level');
	let checkLevel = null;
	
	level.forEach((e) => {
		if (e.checked) {
			checkLevel = e.value;
			
		}
	})
	console.dir(checkLevel);
	
	if (checkLevel == null) {

		document.querySelector('#checkMatchLevel').style.color = 'red';
		document.querySelector('#checkMatchLevel').innerHTML = '<br>&nbsp;&nbsp;&nbsp;실력을 선택하세요.';	
		return false;
	}
	
	
	let content = document.querySelector('#contentId').value;
	
	if(content == ""){
		document.querySelector('#checkContent').style.color = 'red';
		document.querySelector('#checkContent').innerHTML = '내용을 입력해주세요!';	
		return false;
	}
	
	return true;
	
}