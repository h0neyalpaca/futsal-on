<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/mypage/mypage.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<div class="section">
			<div class="mypage-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="mypage-con">
					<%@ include file="/WEB-INF/views/mypage/mypage_tab.jsp" %>
					
					
					<p class="leave-msg">회원 탈퇴를 진행하면 <strong>회원 정보가 소멸되며 다시 시스템을 이용하실 수 없습니다.</strong>
					<br>탈퇴 후 되돌릴 수 없으니 신중하게 결정해주세요.</p>
					<br>
					<strong>버튼을 클릭하면 바로 탈퇴가 진행이 되니 신중히 눌려주세요.</strong></br>
					<button type="submit" class="btn-leave-member" style="color:red" onclick="location.href='/mypage/leave?userId=${authentication.userId}'">풋살온 탈퇴하기</button>
				
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>