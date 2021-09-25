/**
 * 
 */
 (()=>{
	  let cofirmPw = "";
	  let cofirmNick = document.querySelector('#nickName').value;

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
				   cofirmPw = password;
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
		   let nickReg = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/;

		   if(!nickName){
			   document.querySelector('#nickCheck').innerHTML = '닉네임을 입력하지 않았습니다';
			   return;
		   }
		   
		   if(nickName == cofirmNick){
			   document.querySelector('#nickCheck').innerHTML = '원래 닉네임 입니다';
			   return;
		   }

		   if(!nickReg.test(nickName)) {
		   	   document.querySelector('#nickCheck').innerHTML = '닉네임은 4~10자로 설정해야합니다.';
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
			   if(text == 'available'){
				   cofirmNick = nickName;
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
		   let password = document.querySelector('#password').value;
		   let nickName = document.querySelector('#nickName').value;
			console.dir(password);
		   
			if(cofirmNick != nickName){
			   alert('닉네임 중복 검사를 하지 않았습니다');
			   document.querySelector('#nickName').focus();
			   e.preventDefault();
		   }

		   if(cofirmPw != password){
			   alert('비밀번호 일치 검사를 하지 않습니다');
			   document.querySelector('#password').focus();
			   e.preventDefault();
		   }
	   })
  })();