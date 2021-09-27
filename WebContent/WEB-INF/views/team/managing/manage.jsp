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
					<%@ include file="/WEB-INF/views/team/include/team_tab.jsp" %>
					<table class="team-member-form">
						<tr>
							<th width="80px">NO</th>
							<th>아이디</ht>
							<th>이름</th>
							<th>팀원 등급</th>
							<c:if test="${authentication.grade=='ME03'}">
								<th>팀장 위임</th>
								<th>팀원 추방</th>
							</c:if>
						</tr>
						<c:forEach items="${tmMembers}" var="tmMembers" varStatus="status">
						<tr>
							<td><c:out value="${status.count}" /></td>
							<td><c:out value="${tmMembers.userId}" /></td>
							<td><c:out value="${tmMembers.userName}" /></td>
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
									<c:out value="${tmMembers.grade eq 'ME03'?'팀장':tmMembers.grade eq 'ME02'?'매니저':'팀원'}" />
								</c:if>
							</td>
							<c:if test="${authentication.grade=='ME03'}">
								<td><c:if test="${tmMembers.grade!='ME03'}"><button onclick="manageDelegation('${tmMembers.userId}');">팀장 위임</button></c:if></td>
								<td><c:if test="${tmMembers.grade!='ME03'}"><button onclick="manageExpulsion('${tmMembers.userId}');">추방</button></c:if></td>
							</c:if>
						</tr>
						</c:forEach>
					</table>
					
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<%@ include file="/WEB-INF/views/team/include/team-pop.jsp" %>
<script type="text/javascript" src="${request.contextPath}/resources/js/team/managing.js"></script> 
</body>
</html>