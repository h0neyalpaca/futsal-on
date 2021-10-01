<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="popup-teaminfo-wrap">
	<div class="popup-close"><i class="fas fa-times"></i></div>
	<div class="popup-teaminfo">
		<div class="popup-title">
			<h1>${teamPopup.tmName}</h1>
		</div>
			<div class="popup-team-img" style="background-size:cover;">
		</div>
		<div class="popup-team-info">
			<p class="popup-tm-grade"></p>
			<p class="popup-tm-lc"></p>
			<p class="intro popup-tm-info"></p>
		</div>
	</div>
</div>