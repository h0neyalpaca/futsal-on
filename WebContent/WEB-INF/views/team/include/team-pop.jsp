<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="pop-msg-wrap msg">
	<div class="pop-msg">
		<p></p>
		<button onclick="btnClose();">확인</button>
	</div>
</div>

<div class="pop-msg-wrap question">
	<div class="pop-msg">
		<p></p>
		<button class="cancel-btn" onclick="btnClose();">취소</button>
		<button class="submit-btn" onclick=""></button>
	</div>
</div>

<div class="pop-msg-wrap answer">
	<div class="pop-msg">
		<p></p>
		<button class="close-btn">확인</button>
	</div>
</div>

<c:if test="${not empty param.result}">
	<div class="pop-msg-wrap" style="display:flex;">
		<div class="pop-msg">
			<p>
				<i class="fas fa-check-circle"></i><br>
				<c:if test="${param.result==1}">팀 가입이 성공적으로 완료되었습니다.<br>${team.tmName} 팀의 팀원이 되신 것을 축하드립니다!</c:if>
				<c:if test="${param.result==2}">팀이 성공적으로 생성되었습니다.<br>${team.tmName} 팀에서 많은 활동 부탁드릴게요!</c:if>
				<c:if test="${param.result==3}">변경이 완료되었습니다.</c:if>
			</p>
			<button onclick="btnClose();">확인</button>
		</div>
	</div>
</c:if>