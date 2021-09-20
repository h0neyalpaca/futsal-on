<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/myteam/myteam-form.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section>
		<div class="section">
			<div class="myteam-wrap">
				<h2><i class="far fa-futbol"></i> 나의 팀</h2>
				<div class="myteam-con">
					
					<%@ include file="/WEB-INF/views/team/managing/team_tab.jsp" %>
					<form action="/myteam/mpdify-team" method="post">
						<table class="team-create-form">
							<tr>
								<th>팀 이름</th>
								<td>
									다인다색
								</td>
							</tr>
							<tr>
								<th>팀 사진</th>
								<td>
									<input type="file" name="teamFile" id="teamFile" />
									<span class="msg">500MB 이하의 jpg, gif, png</span>
									<div class="team-file leader">
										<a class="view-file"><i class="fas fa-eye"></i> team_file_name.jpg</a>
										<a class="del-file"><i class="fas fa-trash-alt"></i> 팀 사진 삭제</a>
									</div>
								</td>
							</tr>
							<tr>
								<th>실력</th>
								<td>
									<label><input type="radio" name="teamGrade" id="teamGrade" value="" checked /> 상</label>
									<label><input type="radio" name="teamGrade" id="teamGrade" value="" /> 중</label>
									<label><input type="radio" name="teamGrade" id="teamGrade" value="" /> 하</label>
								</td>
							</tr>
							<tr>
								<th>활동지역</th>
								<td>
									<label><input type="checkbox" checked> 서울</label>
									<label><input type="checkbox" checked> 경기</label>
									<label><input type="checkbox"> 강원</label>
									<label><input type="checkbox"> 충청</label>
									<label><input type="checkbox"> 전라</label>
									<label><input type="checkbox"> 제주</label>
									<label><input type="checkbox"> 경상</label>
								</td>
							</tr>
							<tr>
								<th>소개글</th>
								<td>
									<textarea placeholder="팀 소개 내용을 입력해주세요.">안녕하세요. 다인다색입니다.</textarea>
									<span id="txtCounter" class="txt-counter">(0 / 최대 200자)</span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="btn-team-create">
										<input type="submit" value="팀 변경하기" />
									</div>
								</td>
							</tr>
						</table>
					</form>
					
					<!-- 멤버 화면 -->

					<table class="team-create-form">
							<tr>
								<th>팀 이름</th>
								<td>
									다인다색
								</td>
							</tr>
							<tr>
								<th>팀 사진</th>
								<td>
									<div class="team-file">
										<a class="view-file"><i class="fas fa-eye"></i> team_file_name.jpg</a>
									</div>
								</td>
							</tr>
							<tr>
								<th>실력</th>
								<td>
									상
								</td>
							</tr>
							<tr>
								<th>활동지역</th>
								<td>
									서울, 경기
								</td>
							</tr>
							<tr>
								<th>소개글</th>
								<td>
									안녕하세요. 다인다색입니다.
								</td>
							</tr>
							<tr>
								<td colspan="2">
								</td>
							</tr>
						</table>
				</div>
			</div>
		</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<c:if test="${not empty param.result}">
	<div class="pop-msg-wrap">
		<div class="pop-msg">
			<p><i class="fas fa-check-circle"></i><br>팀 가입이 성공적으로 완료되었습니다.<br>${teamInfo.tmName} 팀의 팀원이 되신 것을 축하드립니다.</p>
			<button onclick="btnClose();">확인</button>
		</div>
	</div>
	<script type="text/javascript">
		let btnClose = () => {
			let msgWrap = document.querySelector('.pop-msg-wrap');
			msgWrap.style.display='none';
		}
	</script>
</c:if>
</body>
</html>