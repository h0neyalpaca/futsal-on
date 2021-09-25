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
					<form action="/mypage/modify" method="post" id ="frm_modify">
						<table class="mypage-form">
							<tr>
								<th>아이디</th>
								<td>
									<c:out value="${authentication.userId}"/>
								</td>
							</tr>
							<tr>
								<th>현재비밀번호</th>
								<td>
									<input type="password" name="password" id="password" required />
									<button type="button" name="btnPwCheck" id="btnPwCheck">확인</button>
									<span class="valid-msg" id="pwCheck" >
									 	<c:if test="${!empty param.err and not empty modifyValid.password}">
											<i class="fas fa-exclamation-circle"></i> 비밀번호가 일치하지 않습니다.
										</c:if>
									</span>
								</td>
							</tr>
							<tr>
								<th>새비밀번호</th>
								<td>
									<input type="password" name="new-password" id="new-password" placeholder="띄어쓰기 없는 6~15자 영문 대/소문자 포함" />
									<span class="valid-msg" id="newPw">
						                <c:if test="${not empty modifyValid.password}">
						                	비밀번호는 영어,숫자,특수문자 조합의 8글자 이상의 문자열입니다.
						                </c:if>
					                </span>
								</td>
							</tr>
							<tr>
								<th>비밀번호확인</th>
								<td>
									<input type="password" name="check-new-password" id="check-new-password" />
									<span class="valid-msg" id="pwCheck">
						                <c:if test="${not empty modifyValid.checkPw}">
						                	비밀번호가 일치하지 않습니다
						                </c:if>
					                </span>
									
								</td>
							</tr>
							<tr>
								<th>이름</th>
								<td>
									<c:out value="${authentication.userName}"/>
								</td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td>
									<input type="text" name="nickName" id="nickName" size="6" value="${authentication.userNick}" placeholder="2~6자의 한글,영문" required />
									<button type="button" id="btnNickCheck">중복확인</button>
									<span class="valid-msg" id="nickCheck">
						                <c:if test="${!empty param.err and not empty modifyValid.nickName}">
						                	이미 존재하는 닉네임 입니다
						                </c:if>
					                </span>
								</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td>
									<input id="tell" type="tel" name="tell" value="${authentication.tell}" placeholder="숫자만 입력" required />
									<span class="valid-msg" id="tellCheck">
						                <c:if test="${not empty modifyValid.tell}">
						                	휴대폰 번호는 9~11자리의 숫자입니다
						                </c:if>
					                </span>
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									<c:out value="${authentication.email}"/>
								</td>
							</tr>
							<tr>
								<th>실력</th>
								<td>
									<label><input type="radio" name="capacity" id="capacity" value="상" ${authentication.capacity eq "상 "?"checked":""}/> 상</label>
									<label><input type="radio" name="capacity" id="capacity" value="중" ${authentication.capacity eq "중 "?"checked":""}/> 중</label>
									<label><input type="radio" name="capacity" id="capacity" value="하" ${authentication.capacity eq "하 "?"checked":""}/> 하</label>
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
	
<script type="text/javascript" src="/resources/js/member/modifyForm.js"></script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>