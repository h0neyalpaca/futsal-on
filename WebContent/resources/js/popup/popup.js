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
			if(text == 'available'){
				document.querySelector(".popup-teaminfo-wrap").style.display = 'block';
			}else{
				//
			}
		})
		.catch(error=>{
			//
		});
};

document.querySelector(".popup-close").addEventListener('click',() =>{
	sessionStorage.removeItem('filePopup');
	document.querySelector(".popup-teaminfo-wrap").style.display = 'none';
});