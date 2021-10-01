let teamInfo = (tmCode) => {
	fetch("/popup/team-info?tmCode="+tmCode)
		.then(response =>{
			if(response.ok){
				return response.text()
			}else{
				throw new Error(response.status);
			}
		})
		.then(text => {
			if(text == 'disable'){
				alert('존재하지 않는 팀입니다.');
			}else{
				let tmInfos = text.split('[=dividing line=]');
				document.querySelector(".popup-teaminfo-wrap").style.display = 'block';
				document.querySelector(".popup-title h1").innerHTML = tmInfos[0];
				document.querySelector(".popup-team-img").style.background='url("'+tmInfos[4]+'") center center';
				document.querySelector(".popup-team-img").style.backgroundSize='cover';
				document.querySelector(".popup-tm-grade").innerHTML = '<span class="tit">실력</span>'+tmInfos[1];
				let tmGrade = (tmInfos[2]==='LC11'?'서울':tmInfos[2]==='LC31'?'경기':tmInfos[2]==='LC32'?'강원':tmInfos[2]==='LC33'?'충청':tmInfos[2]==='LC35'?'전라':tmInfos[2]==='LC39'?'제주':'경상');
				document.querySelector(".popup-tm-lc").innerHTML = '<span class="tit">활동지역</span>'+tmGrade;
				document.querySelector(".popup-tm-info").innerHTML = tmInfos[3];
			}
		})
		.catch(error=>{
			alert('ERROR');
		});
};

document.querySelector(".popup-close").addEventListener('click',() =>{
	document.querySelector(".popup-teaminfo-wrap").style.display = 'none';
});