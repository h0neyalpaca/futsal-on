<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/support/support-modify.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="new-support-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<i class="fas fa-question-circle"></i>문의사항 수정
					</div>
				</div>
				<div class="search-wrap">
					<form action="/mypage/support/support-update?bdIdx=${support.bdIdx}" method="post">
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
						<div class="textarea-wraper">
							<textarea name="content" id = "content" cols="100" rows="20" style="resize: none;">${support.content}</textarea>
						</div>
						<input type="submit" value="수정완료"> 
					</form>
				</div> 

			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>