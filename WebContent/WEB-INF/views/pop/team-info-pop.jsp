<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="popup-teaminfo-wrap">
	<div class="popup-close"><i class="fas fa-times"></i></div>
	<div class="popup-teaminfo">
		<div class="popup-title">
			<h1>${teamPopup.tmName}</h1>
		</div>
		<c:if test="${filePopup.flIdx != null}">
			<div class="popup-team-img" style="background:url('${request.contextPath}/img/team/${filePopup.savePath}${filePopup.renameFileName}') center center;background-size:cover;">
		</c:if>
		<c:if test="${filePopup.flIdx == null}">
			<div class="popup-team-img" style="background:url('${request.contextPath}/img/team/no-img.jpg') center center;background-size:cover;">
		</c:if>
		</div>
		<div class="popup-team-info">
			<p><span class="tit">실력</span> ${teamPopup.tmGrade}</p>
			<p><span class="tit">활동지역</span> ${teamPopup.localCode}</p>
			<p class="intro">${teamPopup.tmInfo}</p>
		</div>
	</div>
</div>