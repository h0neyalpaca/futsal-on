
 (()=>{
	  let confirmId = "";
	  let confirmNick = "";

	  document.querySelector('#btnIdCheck').addEventListener('click', function(){
		  
		   let userId = document.querySelector('#userId').value;
		   let idReg = /^[a-z]+[a-z0-9]{3,19}$/g;	   

		   if(!userId){
			   alert("아이디를 입력하지 않았습니다")
			  /* document.querySelector('#idCheck').innerHTML = '아이디를 입력하지 않았습니다';*/
			   return;
		   }
		   
		   if(!idReg.test(userId)) {
		   	   document.querySelector('#idCheck').innerHTML = '<i class="fas fa-exclamation-circle"></i> 아이디는 영문자로 시작하는 4~20자 영문자 또는 숫자이어야 합니다.';
			   return;
           } else {
		   	   document.querySelector('#idCheck').innerHTML = '';
 		   }
		   
		   
		   fetch("/member/id-check?userId="+userId)
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
				   alert("사용 가능한 아이디 입니다")
			   }else{
				   alert("사용 불가능한 아이디 입니다")
			   }
		   })
			.catch(error=>{
				 document.querySelector('#idCheck').innerHTML ='응답에 실패했습니다  상태코드 : '+error;
			})
			
	   });

	document.querySelector('#btnNickCheck').addEventListener('click', function(){
		  
		   let userNick = document.querySelector('#userNick').value;
		   let idReg = /(?=.*[가-힝0-9])(?=.{2,10})/;
		   let spaceCheck = /\s/g;

		   if(!userNick){
 			   alert("닉네임을 입력하지 않았습니다")
			   /*document.querySelector('#nickNameCheck').innerHTML = '닉네임을 입력하지 않았습니다';*/
			   return;
		   }

		   if(!idReg.test(userNick)) {
		   	   document.querySelector('#nickCheck').innerHTML = '<i class="fas fa-exclamation-circle"></i> 닉네임은 한글 또는 숫자 2~10자로 설정해야합니다.';
			   return;
           } else {
		   	   document.querySelector('#nickCheck').innerHTML = '';
 		   }

		   if(spaceCheck.test(userNick)) {
		   	   document.querySelector('#nickCheck').innerHTML = '<i class="fas fa-exclamation-circle"></i> 공백으로 입력하셨습니다.';
			   return;
           } else {
		   	   document.querySelector('#nickCheck').innerHTML = '';
 		   }		      

		   fetch("/member/userNick-check?userNick="+userNick)
		   .then(response =>{
				if(response.ok){
					return response.text()
				}else{
					throw new Error(response.status);
				}
			})
		   .then(text => {
				if(text == 'available'){
				   confirmNick = userNick;
				   alert("사용 가능한 닉네임 입니다")
			   }else{
				   alert("사용 불가능한 아이디 입니다") 
			   }
		   })
			.catch(error=>{
				 document.querySelector('#nickNameCheck').innerHTML ='응답에 실패했습니다  상태코드 : '+error;
			})
	   });
	   
	   document.querySelector('#frm_join').addEventListener('submit',e=>{
		   let userId = document.querySelector('#userId').value;
		   let userNick = document.querySelector('#userNick').value;
		   let password = document.querySelector('#password').value;
		   let passwordCheck = document.querySelector('#passwordCheck').value;
		   let tell = document.querySelector('#tell').value;
		   let serviceCheck = document.querySelector('#serviceCheck');
		   let privacyCheck = document.querySelector('#privacyCheck');
		   let userName = document.querySelector('#userName').value;

		   let pwReg = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9])(?=.{8,})/;
		   let tellReg = /^\d{9,11}$/;
		   let regName = /(?=.*[a-zA-Zㄱ-힣0-9])(?=.{2,10})/;
		  
		   if(!(serviceCheck.checked && privacyCheck.checked)){
			  alert('필수약관에 동의하지 않았습니다');
	 		  e.preventDefault();
		   }

		   if(!pwReg.test(password)){
			  document.querySelector('#passwordReg').innerHTML = '<i class="fas fa-exclamation-circle"></i> 비밀번호는 숫자,영문,특수문자 조합의 8글자 이상인 문자열입니다'; 
		      document.querySelector('#password').focus();
			  e.preventDefault();
		   } else {
			  document.querySelector('#passwordReg').innerHTML = '';
		   }

		   if(password != passwordCheck) {
			  document.querySelector('#passwordDif').innerHTML = '<i class="fas fa-exclamation-circle"></i> 비밀번호가 일치하지 않습니다.';
			  document.querySelector('#passwordCheck').focus();
			  e.preventDefault();
		   } else {
			  document.querySelector('#passwordDif').innerHTML = '';
		   }
	
		   if(!regName.test(userName)){
			   document.querySelector('#NameDif').innerHTML = '<i class="fas fa-exclamation-circle"></i> 이름은 한글 또는 영문 2~10글자인 문자열입니다';
			   document.querySelector('#userName').focus();
			   e.preventDefault();
		   } else {
			  document.querySelector('#NameDif').innerHTML = '';
		   }
	
		   if(!tellReg.test(tell)){
			   document.querySelector('#tellReg').innerHTML = '<i class="fas fa-exclamation-circle"></i> 전화번호는 9~11자리의 숫자입니다';
			   document.querySelector('#tell').focus();
			   e.preventDefault();
		   } else {
			  document.querySelector('#tellReg').innerHTML = '';
		   }

		   if(confirmId != userId){
			   alert('아이디 중복 검사를 하지 않았습니다');
			   /*document.querySelector('#passwordDif').innerHTML = '아이디 중복 검사를 하지 않았습니다';*/
			   document.querySelector('#userId').focus();
			   e.preventDefault();
		   }

 		   if(confirmNick != userNick){
			   alert('닉네임 중복 검사를 하지 않았습니다');
			   /*document.querySelector('#passwordDif').innerHTML = '아이디 중복 검사를 하지 않았습니다';*/
			   document.querySelector('#userId').focus();
			   e.preventDefault();
		   }

	   })
  })();