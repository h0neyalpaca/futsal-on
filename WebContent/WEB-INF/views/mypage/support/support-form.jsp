<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/support/support-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="new-support-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<i class="fas fa-question-circle"></i>문의사항등록
					</div>
				</div>
				<div class="search-wrap">
					<form action="/mypage/support/support-upload" method="post">
					
					    <div class="type_wrapper">문의 유형 : 
					    	<select name="type" id="type">
							    <option value="1" selected>불편사항</option>
							    <option value="2">신고</option>						  
							    <option value="3">기타</option>
							</select>
						</div>
						<div class="tit_wraper"> 제목 : <input type="text" name="title" id="title" required="required"/></div>
						<div class="textarea-wraper">
							<textarea name="content" id="content" cols="100" rows="20" placeholder="내용을 입력해주세요." style="resize: none;"></textarea>
						</div>
						<input type="submit" value="등록">
					</form>
				</div> 

			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>