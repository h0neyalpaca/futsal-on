<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					<p>${authentication.userName} 회원님은 아직 팀에 소속되어 있지 않습니다.<br />
					아래 메뉴에서 <strong>직접 팀을 생성하시거나, 기존의 팀에 참가</strong>하실 수 있습니다.</p>
					<div class="btn-team-area">
						<div class="btn-team create">
							<i class="far fa-plus-square"></i>
							나만의 팀을 만듭니다.
							<button onclick="chckPenalty();">팀 생성</button>
						</div>
						<div class="btn-team join">
							<i class="fas fa-sign-in-alt"></i>
							기존 팀에 참가합니다.
							<button onclick="location.href='${request.contextPath}/team/join-team';">팀 참가</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<div class="pop-msg-wrap msg">
	<div class="pop-msg">
		<p></p>
		<button onclick="btnClose();">확인</button>
	</div>
</div>
<script type="text/javascript">
let chckPenalty = () => {
	fetch('${request.contextPath}/team/chck-penalty')
	.then(response =>{
			if(response.ok){
					return response.text();
			}else{
				throw new Error(response.status);
			}
		})
		.then(text => {
			if(text == 'available'){
				location.href='${request.contextPath}/team/create-form';
			}else{
				drawMsg('<i class="fas fa-times-circle"></i><br>팀 해체 후 7일이 이후부터 다시 팀을 생성할 수 있습니다.');
			}
		})
		.catch(error=>{
			drawMsg('<i class="fas fa-times-circle"></i><br>응답에 실패했습니다.<br>상태코드 : '+error); ;
		})
}

let drawMsg = (msg) => {
	document.querySelector('.pop-msg-wrap.msg').style.display='flex';
	document.querySelector('.pop-msg-wrap.msg p').innerHTML=msg;
}

let btnClose = () => {
	let msgWrap = document.querySelectorAll('.pop-msg-wrap');
	msgWrap.forEach(e=>{
		e.style.display='none';		
	});
}
</script>
</body>
</html>