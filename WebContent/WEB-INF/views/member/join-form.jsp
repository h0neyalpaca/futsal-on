<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/join/join.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="join-wrap">
				<h2><i class="fas fa-user-edit"></i> 회원가입</h2>
				<div class="join-con">
					<form action="/member/join" method="post" id="frm_join">
						<table class="join-form">
							<tr>
								<th>아이디</th>
								<td>
									<input type="text" name="userId" id="userId" size="10" required />
									<button type="button" id="btnIdCheck">중복확인</button>
									  <span id="idCheck"  class="msg imp" >
						           	  <c:if test="${not empty param.err and not empty joinValid.userId}">
						           	  	이미 존재하는 아이디 입니다.
						           	  </c:if>
					           	 	</span>
								</td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td>
									<input type="password" name="password" id="password" required />
									<span class="msg imp" id="passwordReg"><!-- 띄어쓰기 없는 6~15자 영문 대/소문자 포함 --></span>
								</td>
							</tr>
							<tr>
								<th>비밀번호확인</th>
								<td>
									<input type="password" name="passwordCheck" id="passwordCheck" required />
									<span class="msg imp" id="passwordDif"></span>
								</td>
							</tr>
							<tr>
								<th>이름</th>
								<td><input type="text" name="userName" id="userName" size="8" required /></td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td>
									<input type="text" name="userNick" id="userNick" size="6" required />
									<button type="button" id="btnNickCheck">중복확인</button>
									<span id="nickCheck"  class="msg imp" >
						           	  <c:if test="${not empty param.err and not empty joinValid.userId}">
						           	 <!--  	이미 존재하는 아이디 입니다. -->
						           	  </c:if>
					           	 	</span>
								</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td>
									<input id="tell" type="tel" name="tell" required />
									<span class="msg imp" id="tellReg"></span>
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									<input type="email" name="email" required />
									<span class="msg imp"><i class="fas fa-exclamation-circle"></i> 회원가입 인증을 위한 이메일입니다. 정확하게 입력해주세요.</span>
									<!-- button type="button" id="btnSendEmail" style="display:none;">인증발송</button -->
								</td>
							</tr>
							<tr>
								<th>실력</th>
								<td>
									<label><input type="radio" name="capacity" value="상" /> 상</label>
									<label><input type="radio" name="capacity" value="중" /> 중</label>
									<label><input type="radio" name="capacity" value="하" /> 하</label>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<h4>약관 동의</h4>
									<div class="terms-wrap">
										<label for="termsService"><input type="checkbox" name="serviceCheck" id="serviceCheck" value = "service" checked /> <a>서비스 이용약관</a>에 동의합니다. <span class="terms_necessary">(필수)</span></label>
										<label for="termsService"><input type="checkbox" name="privacyCheck" id="privacyCheck" value = "privacy" checked /> <a>개인정보 처리 방침</a>에 동의합니다. <span class="terms_necessary">(필수)</span></label>
									</div>
									<div class="btn-join">
										<input type="submit" value="풋살ON 시작하기" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					
					<script type="text/javascript" src="/resources/js/member/joinForm.js"></script> 
				</div>
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>