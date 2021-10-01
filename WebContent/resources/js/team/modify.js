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