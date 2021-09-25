<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/matching/matching-team-input.css" />
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=5bdae166c6881cf42916fd1d25349e6e&libraries=services,clusterer,drawing"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<section>
		<div class="section">
			<div class="new-match-wrap">
				<div class="sub-nav-wrap">
					<div class="sub-nav">
						<i class="fas fa-users"></i>팀매칭등록
					</div>
				</div>
				<div class="search-wrap">
					<form onsubmit="return formCheck()" action="/matching/team/team-match-register" method="post" >
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
							</dd>
						</dl>
						<dl>
							<dt>상세주소</dt>
							<dd>
								<input type="text" class="keyword" name="detailAddress"/><i class="fas fa-search" id="search"></i>
								<div id="map" style="width:200px;height:150px;"></div>
							</dd>
						</dl>
						<dl>
							<dt>매치방식</dt>
							<dd class="matchStyle">
								<label><input type="radio" name="size" value="5">5 : 5</label>
								<label><input type="radio" name="size" value="6">6 : 6</label>
								<label><input type="radio" name="size" value="7">7 : 7</label>
							</dd>
						</dl>
						<dl>
							<dt>매치날짜</dt>
							<dd>
								<input type="date" name="date" />
								<input type="time" name="time" min="0" />
							</dd>
			
						</dl>
						
						<dl>
							<dt>구장비</dt>
							<dd><input type="number" step="1000" min="0" name="cost"/></dd>
						</dl>
						<dl>
							<dt>우리팀 실력</dt>
							<dd class="level-dd" style="padding-left: 10px;">
								<label><input type="radio" name="level" value="상">상</label>
								<label><input type="radio" name="level" value="중">중</label>
								<label><input type="radio" name="level" value="하">하</label>
							</dd>
						</dl>
						<div class="textarea-wrap">
							<textarea name="content" cols="100" rows="20" placeholder="내용을 입력해주세요." style="resize: none;"></textarea>
						</div>
						<input type="submit" value="등록">
					</form>
				</div> 
				<!-- End search wrap -->
				<!-- <h2>새로 모집중인 매치</h2> -->
				<!-- 하나의 매치 박스 -->
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

<script type="text/javascript">
(() => {
	

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
	
	
	
	
	// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	
	
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
	let formCheck = () => {
		let localCode = document.getElementsByName('localCode');
		let value = null;
		localCode.forEach((e) => {
			if (e.checked) {
				value = e.value;
			}
		})
		
		
		console.dir("동작 확인 : " + value);
		if (value == null) {
			document.querySelector('.matchRegion').innerHTML = '지역을 선택해주세요.';
			return true;
		}
		return false;
		
	}
	
 	document.querySelector('#match_reg').addEventListener('submit', function(e){
		e.preventDefault;
		let localCode = document.getElementsByName('localCode');
		let value = null;
		localCode.forEach((e) => {
			if (e.checked) {
				value = e.value;
			}
		})
		
		
		console.dir("동작 확인 : " + value);
		if (value == null) {
			document.querySelector('.matchRegion').innerHTML = '지역을 선택해주세요.';
			e.preventDefault;
		}
		
   })  
	
	

})(); 


/* 	
	let popup = document.querySelectorAll(".profile-name");

	popup.forEach(element => {
		element.addEventListener('click', () => {
			document.querySelector(".popup-teaminfo-wrap").style.display = 'block';
		})
	});

	document.querySelector(".popup-close").addEventListener('click',() =>{
		document.querySelector(".popup-teaminfo-wrap").style.display = 'none';
	}) */
</script>


</body>
</html>