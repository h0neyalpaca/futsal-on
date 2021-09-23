/**
 * 
 */
 (()=>{
	  let confirmId = "";

	  document.querySelector('#btnPwCheck').addEventListener('click', function(){
		  
		   let password = document.querySelector('#password').value;
		   
		   if(!password){
			   document.querySelector('#password').innerHTML = '비밀번호를 입력하지 않았습니다';
			   return;
		   }
		   
		   fetch("/mypage/pw-check?password="+password)
		   .then(response =>{
				if(response.ok){
					return response.text()
				}else{
					throw new Error(response.status);
				}
			})
		   .then(text => {
			   if(text == 'available'){
				   confirmId = password;
				   document.querySelector('#pwCheck').innerHTML = '비밀번호가 일치합니다';
			   }else{
				   document.querySelector('#pwCheck').innerHTML = '비밀번호가 일치하지 않습니다';
			   }
		   })
			.catch(error=>{
				 document.querySelector('#idCheck').innerHTML ='응답에 실패했습니다  상태코드 : '+error;
			})
	   });

	document.querySelector('#btnNickCheck').addEventListener('click', function(){
		  
		   let nickName = document.querySelector('#nickName').value;
		   
		   if(!nickName){
			   document.querySelector('#nickCheck').innerHTML = '닉네임을 입력하지 않았습니다';
			   return;
		   }
		   fetch("/mypage/nick-check?nickName="+nickName)
		   .then(response =>{
				if(response.ok){
					return response.text()
				}else{
					throw new Error(response.status);
				}
			})
		   .then(text => {
				console.dir(text);
			   if(text == 'available'){
				   confirmId = userId;
				   document.querySelector('#nickCheck').innerHTML = '사용 가능한 닉네임 입니다';
			   }else{
				   document.querySelector('#nickCheck').innerHTML = '사용 불가능한 닉네임 입니다';
			   }
		   })
			.catch(error=>{
				 document.querySelector('#nickCheck').innerHTML ='응답에 실패했습니다  상태코드 : '+error;
			})
	   });
	   
	   document.querySelector('#frm_modify').addEventListener('submit',e=>{
		   let nickName = document.querySelector('#nickName').value;
		  
		   if(confirmId != userId){
			   document.querySelector('#nickCheck').innerHTML = '닉네임 중복 검사를 하지 않았습니다';
			   document.querySelector('#nickName').focus();
			   e.preventDefault();
		   }
		   /*
		   if(!pwReg.test(password)){
			   document.querySelector('#pwCheck').innerHTML = '비밀번호는 숫자,영문,특수문자 조합의 8글자 이상인 문자열입니다'; 
			   e.preventDefault();
		   }
		   
		   if(!tellReg.test(tell)){
			   document.querySelector('#tellCheck').innerHTML = '전화번호는 9~11자리의 숫자입니다';
			   e.preventDefault();
		   }  */
	   })
  })();