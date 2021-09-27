(()=>{
	document.querySelector('#tmCodeCheck').addEventListener('click',e=>{
		let tmCode = document.querySelector('#tmCode').value;
		
		fetch("${request.contextPath}/team/tmCode-check?tmCode="+tmCode)
		.then(response =>{
			if(response.ok){
					return response.text();
			}else{
				throw new Error(response.status);
			}
		})
		.then(text => {
			if(text == 'available'){
				location.href="${request.contextPath}/team/join?tmCode="+tmCode;
			} else{
				drawMsg('<i class="fas fa-times-circle"></i><br>팀이 존재하지 않거나 이미 삭제된 팀입니다.');
			}
		})
		.catch(error=>{
			drawMsg('<i class="fas fa-times-circle"></i><br>응답에 실패했습니다.<br>상태코드 : '+error); ;
		})
	});
	
	let drawMsg = (msg) => {
		document.querySelector('.pop-msg-wrap.question').style.display='none';
		document.querySelector('.pop-msg-wrap.answer').style.display='none';
		document.querySelector('.pop-msg-wrap.msg').style.display='flex';
		document.querySelector('.pop-msg-wrap.msg p').innerHTML=msg;
	}
})();

let btnClose = () => {
	let msgWrap = document.querySelectorAll('.pop-msg-wrap');
	msgWrap.forEach(e=>{
		e.style.display='none';		
	});
}