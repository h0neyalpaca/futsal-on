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
					<form>
					    <div class="type_wrapper">문의 유형 : 
					    	<select name="type">
						    <option value="1" selected="selected">불편사항</option>
						    <option value="2">신고</option>						  
						    <option value="3">기타</option>
							</select>
						</div>
						<div class="tit_wraper"> 제목 : <input type="text" name="title" required="required"/></div>
						<div class="textarea-wraper">
							<textarea name="opinion" cols="100" rows="20" placeholder="내용을 입력해주세요." style="resize: none;"></textarea>
						</div>
						<input type="submit" value="수정완료"> 
					</form>
				</div> 

			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>html>