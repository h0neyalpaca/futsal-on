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
<script>
function updateMember(e, userId) {
	var frm = document.modfrm;
	console.log(e.parentNode.childNodes[1].childNodes[1].value);
	frm.userId.value=userId;
	frm.grade.value=e.parentNode.childNodes[1].childNodes[1].value;
	frm.submit();
}
</script>
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
							<c:if test="${authentication.grade=='ME03'}"><th>팀원 추방</th></c:if>
						</tr>
						<c:forEach items="${tmMembers}" var="tmMembers" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${tmMembers.userId}</td>
							<td>
								<c:if test="${authentication.grade=='ME03'}">
									<div class="selectbox">
										<select name="grdOpt">
											<option value="ME03" ${tmMembers.grade eq 'ME03'?'selected':''}>회장</option>
											<option value="ME02" ${tmMembers.grade eq 'ME02'?'selected':''}>매니저</option>
											<option value="ME01" ${tmMembers.grade eq 'ME01'?'selected':''}>팀원</option>
										</select>
									</div>
									<button class="btn-change-grade" onclick="updateMember(this, '${tmMembers.userId}');return false;">변경</button>
								</c:if>
								<c:if test="${authentication.grade!='ME03'}">
									${authentication.grade}
								</c:if>
							</td>
							<c:if test="${authentication.grade=='ME03'}">
								<td><button class="btn-out-member">추방</button></td>
							</c:if>
						</tr>
						</c:forEach>
					</table>
					
					<form name="modfrm" action="/team/manage-func" method="post">
						<input type="hidden" name="userId">
						<input type="hidden" name="grade">
					</form>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>