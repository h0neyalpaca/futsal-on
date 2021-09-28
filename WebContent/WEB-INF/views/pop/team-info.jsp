<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 팝업 -->
		<div class="popup-teaminfo-wrap">
			<div class="popup-close"><i class="fas fa-times"></i></div>
			<div class="popup-teaminfo">
				<div class="popup-title">
					<h1>${matchBox.getTmName()}</h1>
				</div>
				<div class="popup-team-img">
					<img src="" alt="">
				</div>
				<div class="popup-team-info">
					<p><span class="tit">실력</span> ${matchBox.getTmGrade()}</p>
					<p><span class="tit">활동지역</span> ${matchBox.getLocalCode()}</p>
					<p class="intro">${matchBox.getTmInfo()}</p>
				</div>
			</div>
		</div>