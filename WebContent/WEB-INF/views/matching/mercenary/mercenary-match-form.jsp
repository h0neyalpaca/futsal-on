<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching-mercenary-input.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="new-match-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<i class="fas fa-users"></i>용병매칭등록
					</div>
				</div>
				<div class="search-wrap">
					<form>
						<dl>
							<dt>경기지역</dt>
							<dd>
								<label class="selected"><input type="checkbox" checked>서울</label>
								<label><input type="checkbox">경기</label>
								<label><input type="checkbox">강원</label>
								<label><input type="checkbox">충청</label>
								<label><input type="checkbox">전라</label>
								<label><input type="checkbox">제주</label>
								<label><input type="checkbox">경상</label>
							</dd>
						</dl>
						<dl>
							<dt>상세주소</dt>
							<dd>
								<input type="text" /><i class="fas fa-search"></i>
							</dd>
						</dl>
						<dl>
							<dt>매치방식</dt>
							<dd>
								<label><input type="radio">5 : 5</label>
								<label class="selected"><input type="radio">6 : 6</label>
								<label><input type="radio">7 : 7</label>
							</dd>
						</dl>
						<dl>
							<dt>매치날짜</dt>
							<dd><input type="date" /></dd>
						</dl>
						<dl>
							<dt>용병비</dt>
							<dd><input type="number" step="1000" /></dd>
						</dl>
						<dl class="recruit">
							<dt>모집인원</dt>
							<dd><input type="number" step="1" style="margin-left: 10px;"/></dd>
						</dl>
						<dl>
							<dt>상대팀 실력</dt>
							<dd class="level-dd" style="padding-left: 10px;">
								<label><input type="radio">상</label>
								<label class="selected"><input type="radio">중</label>
								<label><input type="radio">하</label>
							</dd>
						</dl>
						<div class="textarea-wrap">
							<textarea name="opinion" cols="100" rows="20" placeholder="내용을 입력해주세요." style="resize: none;"></textarea>
						</div>
						<input type="submit" value="등록">
					</form>
				</div> 

			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
<script>
	let popup = document.querySelectorAll(".profile-name");

	popup.forEach(element => {
		element.addEventListener('click', () => {
			document.querySelector(".popup-teaminfo-wrap").style.display = 'block';
		})
	});

	document.querySelector(".popup-close").addEventListener('click',() =>{
		document.querySelector(".popup-teaminfo-wrap").style.display = 'none';
	})
</script>

</html>