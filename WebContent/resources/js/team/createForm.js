(()=>{
	let cfmTmName = "";

	document.querySelector('#tmNameCheck').addEventListener('click',()=>{
		let tmName = document.querySelector('#tmName').value;   
		let tmNameReg = /^[a-zA-Zㄱ-힣0-9]{2,8}$/;
		
		if(!tmName){
			drawMsg('<i class="fas fa-exclamation-circle"></i><br>팀명을 입력해주세요.');
			return;
		}
		if(!tmNameReg.test(tmName)){
			drawMsg('<i class="fas fa-exclamation-circle"></i><br>팀명은 2~8자리 한/영/숫자만 입력 가능합니다.');
			return;
		}
		fetch("${request.contextPath}/team/tmName-check?tmName="+tmName)
		.then(response =>{
			if(response.ok){
					return response.text();
			}else{
				throw new Error(response.status);
			}
		})
		.then(text => {
			if(text == 'available'){
				cfmTmName = tmName;
				drawMsg('<i class="fas fa-check-circle"></i><br>사용 가능한 팀명입니다.');
			}else{
				drawMsg('<i class="fas fa-times-circle"></i><br>사용 불가능한 팀명입니다.');
			}
		})
		.catch(error=>{
			drawMsg('<i class="fas fa-times-circle"></i><br>응답에 실패했습니다.<br>상태코드 : '+error); ;
		})
	});

	document.querySelector('#frm_create-team').addEventListener('submit',e=>{
		let tmName = document.querySelector('#tmName').value;
		if(cfmTmName != tmName){
			drawMsg('<i class="fas fa-exclamation-circle"></i><br>팀명 중복 확인을 해주세요.');
			e.preventDefault();
		};
	});

})();

let fileCheck = (e) => {
	let file = e[0].name.split('.');
	let filetype = file[file.length-1].toLowerCase();
	if(filetype == 'jpg' || filetype == 'gif' || filetype == 'jpeg' || filetype=='png'){
		
	} else{
		drawMsg('<i class="fas fa-times-circle"></i><br>JPG/GIF/PNG 파일만 업로드 가능합니다.');
		let files = document.getElementsByName('teamFile');
		console.dir(files);
		files[0].value='';
		return false;
	}
}

let drawMsg = (msg) => {
	document.querySelector('.pop-msg-wrap.question').style.display='none';
	document.querySelector('.pop-msg-wrap.answer').style.display='none';
	document.querySelector('.pop-msg-wrap.msg').style.display='flex';
	document.querySelector('.pop-msg-wrap.msg p').innerHTML=msg;
}

let btnClose = () => {
	let msgWrap = document.querySelectorAll('.pop-msg-wrap');
	msgWrap.forEach(e=>{
		e.style.display='none';		
	});
}