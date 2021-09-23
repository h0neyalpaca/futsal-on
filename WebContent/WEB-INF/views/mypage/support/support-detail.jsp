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
						    <c:choose>
								<c:when test="${support.type == 1}">
									<span>불편사항</span>
								</c:when>
								<c:when test="${support.type == 2}">
									<span>신고</span>
								</c:when>
								<c:when test="${support.type == 3}">
									<span>기타</span>
								</c:when>
							</c:choose>
						</div>
						<div class="tit_wraper"> 제목 : <span><c:out value="${support.title}"/></span></div>
						<div class="content-wraper">
							<p><c:out value="${support.content}"/></p>
						</div>
						
						<div class="button_wrapper">
							<input type="submit" onclick="location.href='/mypage/support/support-modify?bdIdx=${support.bdIdx}'" value="수정"> <input type="submit" value="삭제">
						</div>
					
				</div> 

			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>