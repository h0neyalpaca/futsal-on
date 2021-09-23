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
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					<%@ include file="/WEB-INF/views/team/managing/team_tab.jsp" %>
					<table class="team-member-form">
						<tr>
							<th>NO</th>
							<th>이름</th>
							<th>팀원 등급</th>
							<c:if test="${authentication.grade=='ME03'}">
								<th>팀장 위임</th>
								<th>팀원 추방</th>
							</c:if>
						</tr>
						<c:forEach items="${tmMembers}" var="tmMembers" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${tmMembers.userId}</td>
							<td>
								<c:if test="${authentication.grade=='ME03'&&tmMembers.grade!='ME03'}">
									<div class="selectbox">
										<select name="grdOpt">
											<option value="ME02" ${tmMembers.grade eq 'ME02'?'selected':''}>매니저</option>
											<option value="ME01" ${tmMembers.grade eq 'ME01'?'selected':''}>팀원</option>
										</select>
									</div>
									<button class="btn-change-grade" onclick="manageGrade(this, '${tmMembers.userId}');return false;">변경</button>
								</c:if>
								<c:if test="${authentication.grade!='ME03'||tmMembers.grade=='ME03'}">
									${tmMembers.grade eq 'ME03'?'팀장':'ME02'?'매니저':'팀원'}
								</c:if>
							</td>
							<c:if test="${authentication.grade=='ME03'}">
								<td><c:if test="${tmMembers.grade!='ME03'}"><button onclick="manageDelegation('${tmMembers.userId}');">팀장 위임</button></c:if></td>
								<td><c:if test="${tmMembers.grade!='ME03'}"><button onclick="manageExpulsion(this, '${tmMembers.userId}');return false;">추방</button></c:if></td>
							</c:if>
						</tr>
						</c:forEach>
					</table>
					
					<form name="modfrm" method="post">
						<input type="hidden" name="userId">
						<input type="hidden" name="grade">
					</form>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<c:if test="${not empty param.result}">
<div class="pop-msg-wrap" style="display:flex;">
	<div class="pop-msg">
		<p><i class="fas fa-check-circle"></i><br>팀장을 위임하였습니다!</p>
		<button onclick="btnClose();">확인</button>
	</div>
</div>
</c:if>
<div class="pop-msg-wrap delegation">
	<div class="pop-msg">
		<p><i class="fas fa-exclamation-triangle"></i><br>팀장을 위임하시겠습니까?<br>팀에 대한 모든 권한을 잃게 됩니다.</p>
		<button onclick="delegation();">팀을 위임하겠습니다.</button><button class="cancel" onclick="btnClose();">다시 생각해보겠습니다.</button>
	</div>
</div>

<script type="text/javascript">
(()=>{
	let userId = "";
})();

let manageDelegation = (userId) => {
	let msgWrap = document.querySelector('.pop-msg-wrap.delegation');
	msgWrap.style.display='flex';
	this.userId = userId;
}
let delegation = () => {
	var frm = document.modfrm;
	frm.userId.value=userId;
	frm.action='${request.contextPath}/team/managing/manage-delegation';
	frm.submit();
}
let btnClose = () => {
	let msgWrap = document.querySelector('.pop-msg-wrap');
	msgWrap.style.display='none';
}


/* function manageGrade(e, userId) {
	var frm = document.modfrm;
	frm.userId.value=userId;
	frm.grade.value=e.parentNode.childNodes[1].childNodes[1].value;
	frm.action='${request.contextPath}/team/managing/manage-grade';
	frm.submit();
}
function manageExpulsion(e, userId) {
	var frm = document.modfrm;
	frm.userId.value=userId;
	frm.grade.value=e.parentNode.childNodes[1].childNodes[1].value;
	frm.action='${request.contextPath}/team/managing/manage-expulsion';
	frm.submit();
} */
</script>
</body>
</html>