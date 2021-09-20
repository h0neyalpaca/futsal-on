<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
	<div class="section">
		<div class="myteam-wrap">
			<h2><i class="far fa-futbol"></i> 팀 참가하기</h2>
			<div class="myteam-con">
				<p class="join-team-msg">
					<i class="fas fa-user-lock"></i>
					전달받으신 <strong>팀 참가 코드</strong>를 입력하세요.
				</p>
				<form action="/team/join-func" method="post">
					<div class="team-join-form">
						<input type="text" name="tmCode" id="tmCode" placeholder="팀 참가코드를 입력하세요." required />
						<input type="submit" value="팀 참가하기" />
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<c:if test="${not empty param.err}">
	<div class="pop-msg-wrap">
		<div class="pop-msg">
			<p><i class="fas fa-exclamation-triangle"></i><br>팀 코드가 일치하지 않습니다.<br>팀 다시 입력해주세요.</p>
			<button onclick="btnClose()">확인</button>
		</div>
	</div>
	<script type="text/javascript">
		let btnClose = () => {
			let msgWrap = document.querySelector('.pop-msg-wrap');
			msgWrap.style.display='none';
		}
	</script>
</c:if>
</body>
</html>