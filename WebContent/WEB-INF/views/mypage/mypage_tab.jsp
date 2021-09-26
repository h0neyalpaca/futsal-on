<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="mypage-tab-wrap">
	<li><a class="selected" href="/mypage/personal-notice">알림내역</a></li>
	<li><a href="/mypage/my-application">용병신청내역</a></li>
	<li><a href="/mypage/support/support-list">문의내역</a></li>
	<li><a href="/mypage/modify-form">회원정보수정</a></li>
</ul>

<script type="text/javascript">
let tabBtns = document.querySelector('.mypage-tab-wrap').children;
for (var i = 0; i < tabBtns.length; i++) {
	let tabBtn = tabBtns[i].children[0];
	tabBtn.classList.remove('selected');
	if(tabBtn.href==document.location.href.split('?')[0]){
		tabBtn.classList.add('selected');
	}
}
</script>