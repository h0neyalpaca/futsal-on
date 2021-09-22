<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/mypage/mypage.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/mypage/mypage-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="mypage-wrap">
				<h2><i class="fas fa-user-cog"></i> 마이페이지</h2>
				<div class="mypage-con">
					<%@ include file="/WEB-INF/views/mypage/mypage_tab.jsp" %>
					<form action="/mypafe/modify" method="post">
						<table class="mypage-form">
							<tr>
								<th>아이디</th>
								<td>
									<c:out value="${member.userId}"/>
								</td>
							</tr>
							<tr>
								<th>현재비밀번호</th>
								<td>
									<input type="password" name="password" id="password" required />
									<span class="msg imp"><i class="fas fa-exclamation-circle"></i> 비밀번호가 일치하지 않습니다.</span>
								</td>
							</tr>
							<tr>
								<th>새비밀번호</th>
								<td>
									<input type="password" name="password" id="password" />
									<span class="msg">띄어쓰기 없는 6~15자 영문 대/소문자 포함</span>
								</td>
							</tr>
							<tr>
								<th>비밀번호확인</th>
								<td>
									<input type="password" name="password" id="password" />
									
								</td>
							</tr>
							<tr>
								<th>이름</th>
								<td>알파카</td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td>
									<input type="text" name="nickName" id="nickName" size="6" required />
									<button type="button" id="btnNickCheck">중복확인</button>
									<span class="msg">2~6자의 한글,영문</span>
								</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td>
									<input id="tell" type="tel" name="tell" value="${member.tell}" required />
									<span class="msg">숫자만 입력</span>
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									<c:out value="${member.email}"/>
								</td>
							</tr>
							<tr>
								<th>실력</th>
								<td>
									<label><input type="radio" name="grade" id="grade" value="" checked/> 상</label>
									<label><input type="radio" name="grade" id="grade" value="" /> 중</label>
									<label><input type="radio" name="grade" id="grade" value="" /> 하</label>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="btn-join">
										<input type="submit" value="변경하기" />
										<a class="leave_member" href="/mypage/leave-id">회원탈퇴</a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>