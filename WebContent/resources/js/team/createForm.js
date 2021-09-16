/**
 * 
 */
 (()=>{
	  let confirmId = "";

	  document.querySelector('#btnTeamNameCheck').addEventListener('click', function(){
		  
		   let teamName = document.querySelector('#teamName').value;
		   
		   if(!teamName){
			   document.querySelector('#teamNameCheck').innerHTML = '팀명을 입력하지 않았습니다';
			   return;
		   }
		   
		   fetch("/member/teamName-check?teamName="+teamName)
		   .then(response =>{
				if(response.ok){
					return response.text()
				}else{
					throw new Error(response.status);
				}
			})
		   .then(text => {
			   if(text == 'available'){
				   confirmId = userId;
				   document.querySelector('#teamNameCheck').innerHTML = '사용 가능한 팀명 입니다';
			   }else{
				   document.querySelector('#teamNameCheck').innerHTML = '사용 불가능한 팀명 입니다';
			   }
		   })
			.catch(error=>{
				 document.querySelector('#teamNameCheck').innerHTML ='응답에 실패했습니다  상태코드 : '+error;
			})
	   });

	
	   document.querySelector('#frm_join').addEventListener('submit',e=>{
		   let teamName = document.querySelector('#teamName').value;
		  
		   if(confirmId != teamName){
			   document.querySelector('#teamNameCheck').innerHTML = '팀명 중복 검사를 하지 않았습니다';
			   document.querySelector('#teamName').focus();
			   e.preventDefault();
		   }
	   })
  })();