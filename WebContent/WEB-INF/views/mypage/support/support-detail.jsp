<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/support/support-detail.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="new-support-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<i class="far fa-clipboard"></i>문의사항 상세 페이지
					</div>
				</div>
				<div class="search-wrap">
					
					    <div class="type_wrapper">문의 유형 : 
					    	<span>불편사항</span>
							</select>
						</div>
						<div class="tit_wraper"> 제목 : <span>사이트 건의사항 입니다</span></div>
						<div class="content-wraper">
							<p>매칭 되었을 때 알림이 안와요 해결 부탁드립니다</p>
						</div>
						
						<div class="button_wrapper">
							<input type="submit" onclick="location.href='/mypage/support/support-modify'" value="수정"> <input type="submit" value="삭제">
						</div>
					
				</div> 

			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>html>