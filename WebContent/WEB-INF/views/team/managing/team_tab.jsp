<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul class="team-tab-wrap">
	<c:if test="${member.grade=='ME03'}">
		<li><a class="selected" href="${request.contextPath}/team/managing/modify">팀 정보</a></li>
		<li><a href="${request.contextPath}/team/managing/manage">팀원 관리</a></li>
		<li><a href="${request.contextPath}/team/managing/total-score">팀 전적</a></li>
		<li><a href="${request.contextPath}/team/managing/team-board">글 관리</a></li>
		<li><a href="${request.contextPath}/team/managing/delete-team">팀 해체</a></li>
	</c:if>
	<c:if test="${member.grade!='ME03'}">
		<li><a class="selected" href="${request.contextPath}/team/modify">팀 정보</a></li>
		<li><a href="${request.contextPath}/team/managing/manage">팀원 보기</a></li>
		<li><a href="${request.contextPath}/team/managing/total-score">팀 전적</a></li>
		<li><a href="${request.contextPath}/team/managing/team-board">작성 게시글</a></li>
		<li><a href="${request.contextPath}/team/managing/delete-team">팀 탈퇴</a></li>
	</c:if>
</ul>