<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching-mercenary-modify.css" />
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=5bdae166c6881cf42916fd1d25349e6e&libraries=services,clusterer,drawing"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="new-match-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<a href=""><i class="fas fa-users"></i>용병매칭수정</a>
					</div>
				</div>
				<div class="search-wrap">
					<form form onsubmit="return formCheck();" action="/matching/team/mercenary-modify-register" method="post">
						<dl>
							<dt>경기지역</dt>
							<dd class="matchRegion">
								<label><input type="radio" class="bt1" value="LC11" name="localCode">서울</label>
								<label><input type="radio" class="bt2" value="LC31" name="localCode">경기</label>
								<label><input type="radio" class="bt3" value="LC32" name="localCode" >강원</label>
								<label><input type="radio" class="bt4" value="LC33" name="localCode" />충청</label>
								<label><input type="radio" class="bt5" value="LC35" name="localCode" >전라</label>
								<label><input type="radio" class="bt6" value="LC39" name="localCode" >제주</label>
								<label><input type="radio" class="bt7" value="LC37" name="localCode" />경상</label>
								<span id="checkRegion" class="validMsg"></span>
							</dd>
						</dl>
						<dl>
							<dt>상세주소</dt>
							<dd>
								<input type="text" class="keyword" name="detailAddress" value="${matchModify.getAddress()}" /><i class="fas fa-search" id="search"></i>
								<span id="checkAddress" class="validMsg"></span>
								<div id="map" style="width:200px;height:150px;"></div>
							</dd>
						</dl>
						<dl>
							<dt>매치방식</dt>
							<dd class="matchStyle">
								<label><input type="radio" name="size" value="5">5 : 5</label>
								<label><input type="radio" name="size" value="6">6 : 6</label>
								<label><input type="radio" name="size" value="7">7 : 7</label>
								<span id="checkMatchStyle"></span>
							</dd>
						</dl>
						<dl>
							<dt>매치날짜</dt>
							<dd>
								<input type="date" name="date" id="currentDate" value="${matchModify.getMatchDate()}"/>
								<input type="time" name="time" min="8:00" max="22:00" id="currentTime" value="${matchModify.getMatchTime()}"/>
								<span id="checkMatchDate"></span>	
							</dd>
						</dl>
						<dl>
							<dt>용병비</dt>
							<dd>
							<input type="number" step="1000" min="0" name="cost" id="pay" value="${matchModify.getExpense()}"/>
							<span id="checkMatchPay"></span>
							</dd>
						</dl>
						<dl class="recruit">
							<dt>모집인원</dt>
							<dd>
							<input type="number" step="1" min="1" style="margin-left: 10px;" name="mercenary" id="mercenary" value="${matchModify.getMatchNum()}"/>
							<span id="checkMercenary"></span>
							</dd>
						</dl>
						<dl>
							<dt>상대팀 실력</dt>
							<dd class="level-dd" style="padding-left: 10px;">
								<label><input type="radio" name="level" value="high">상</label>
								<label><input type="radio" name="level" value="middle">중</label>
								<label><input type="radio" name="level" value="low">하</label>
								<span id="checkMatchLevel"></span>
							</dd>
						</dl>
						<div class="textarea-wrap" >
							<textarea id="contentId" name="content"  cols="100" rows="20" placeholder="내용을 입력해주세요." style="resize: none;" >${matchModify.getContent()}</textarea>
							<input type="hidden" name="userId" value="${authentication.userId}">
							<span id="checkContent"></span>
						</div>
						<div class="submit-wrap">
							<input type="hidden" name="userId" value="${authentication.userId}">
							<input type="hidden" name="matchIdx" value="${matchIdx}">
							<input type="hidden" name="match" value="mercenary">
							<input type="submit" name="modify" value="수정">
							<input type="submit" id="del" name="modify" value="삭제">
						</div>
					</form>
				</div> 
				<!-- End search wrap -->
				<!-- <h2>새로 모집중인 매치</h2> -->
				<!-- 하나의 매치 박스 -->
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
<script type="text/javascript">
(() => {
	document.querySelector('#del').addEventListener('click',e=>{
		if (window.confirm("정말로 삭제 하시겠습니까?")) {
			
			location.href="/matching/team/mercenary-modify-register?matchIdx=${matchIdx}&modify=삭제";
		}
	})
	
	let region = document.getElementsByName('localCode');
	let checkLocal = '${matchModify.getLocalCode()}';
	
	region.forEach((e) => {
		if (checkLocal == e.value) {
			e.parentNode.className = 'selected';
			e.checked = true;
		}
	})
	
	let size = document.getElementsByName('size');
	let checkSize = '${matchModify.getTmMatch()}';
	
	size.forEach((e) => {
		if (checkSize == e.value) {
			e.parentNode.className = 'selected';
			e.checked = true;
		}
	})
	
	let level = document.getElementsByName('level');
	let checkLevel = '${matchModify.getGrade()}';
	
	level.forEach((e) => {
		if (checkLevel == e.value) {
			e.parentNode.className = 'selected';
			e.checked = true;
		}
	})
	
		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	let searchMap = (keyword) =>{
		
		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places(); 

		// 키워드로 장소를 검색합니다
		ps.keywordSearch(keyword, placesSearchCB); 

		// 키워드 검색 완료 시 호출되는 콜백함수 입니다
		function placesSearchCB (data, status, pagination) {
		    if (status === kakao.maps.services.Status.OK) {

		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		        // LatLngBounds 객체에 좌표를 추가합니다
		        var bounds = new kakao.maps.LatLngBounds();

		        for (var i=0; i<data.length; i++) {
		            displayMarker(data[i]);    
		            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
		        }       

		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		        map.setBounds(bounds);
		    } 
		}

		// 지도에 마커를 표시하는 함수입니다
		function displayMarker(place) {
		    
		    // 마커를 생성하고 지도에 표시합니다
		    var marker = new kakao.maps.Marker({
		        map: map,
		        position: new kakao.maps.LatLng(place.y, place.x) 
		    });

		    // 마커에 클릭이벤트를 등록합니다
		    kakao.maps.event.addListener(marker, 'click', function() {
		        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
		        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
		        infowindow.open(map, marker);
		    });
		}
		
	}
	
	searchMap('${matchModify.getAddress()}');
	
	document.querySelector('.matchRegion').addEventListener('click', e => {
		let region = document.getElementsByName('localCode');
		region.forEach((noCheck) => {
		
			if (noCheck.checked == true) {
				noCheck.parentNode.className = 'selected';
				
			}else{
				
				noCheck.parentNode.className = null;
				noCheck.checked = false;
			}
		})
	})
	
	
	document.querySelector('.matchStyle').addEventListener('click', e => {
		let size = document.getElementsByName('size');
		size.forEach((noCheck) => {
			if (noCheck.checked == true) {
				noCheck.parentNode.className = 'selected';
			}else{
				noCheck.parentNode.className = '';
				console.dir(noCheck.checked);
				noCheck.checked = false;
			}
		})
	})
	
	document.querySelector('.level-dd').addEventListener('click', e => {
		let level = document.getElementsByName('level');
		level.forEach((noCheck) => {
			if (noCheck.checked == true) {
				noCheck.parentNode.className = 'selected';
			}else{
				noCheck.parentNode.className = '';
				noCheck.checked = false;
			}
		})
	})
		
	document.querySelector('#search').addEventListener('click', (e) => {
	    let keyword = document.querySelector('.keyword').value;
		searchMap(keyword);
	});
	
	document.querySelector('.keyword').addEventListener('keyup', (e)=> {
	    if (e.keyCode === 13) {
	    	let keyword = document.querySelector('.keyword').value;
			searchMap(keyword);
	  }  
	});
	
	

})();
</script>

</html>