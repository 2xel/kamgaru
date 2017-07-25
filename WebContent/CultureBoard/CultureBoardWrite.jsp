<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/CultureBoardWrite.css">
</head>
<body>
	<jsp:include page="../Common/Header2.jsp"></jsp:include>
	<div class="title">
		<div class="wrap">
			<h1>
				문화 정보 <strong>무료</strong> 게시 요청
			</h1>
			<p>
				캠퍼스픽은 국내 <strong>최다 이용자 수</strong>를 보유한 대학생 SNS입니다.<br>대학생에게
				유익한 맞춤 정보를 캠퍼스픽에서 효과적으로 홍보하세요.
			</p>
			<p>
				문화 정보 게시는 <strong>무료로 진행</strong>되며,<br>담당자의 검토 및 승인 절차를 걸쳐
				48시간 이내 게시됩니다.
			</p>
			<p class="notice">* 별표 표시는 필수 항목입니다.</p>
		</div>
	</div>
	<form id="container">
		<h2>담당자 정보</h2>
		<p>
			<input type="text" name="manager_name" placeholder="담당자 이름 *"
				maxlength="100" autocomplete="off" class="text">
		</p>
		<p>
			<input type="text" name="manager_phone" placeholder="담당자 전화번호 *"
				maxlength="100" autocomplete="off" class="text">
		</p>
		<h2>개요</h2>
		<p>
			<input type="text" name="title" placeholder="제목 *" maxlength="100"
				autocomplete="off" class="text">
		</p>
		<div class="category">
			<h3>분야 *</h3>
			<label><input type="radio" name="category_id" value="1">영화</label>
			<label><input type="radio" name="category_id" value="2">공연</label>
			<label><input type="radio" name="category_id" value="3">전시</label>
			<label><input type="radio" name="category_id" value="4">축제</label>
			<label><input type="radio" name="category_id" value="5">행사</label>
			<label><input type="radio" name="category_id" value="6"
				checked="">기타</label>
		</div>
		<div>
			<h3>기간 *</h3>
			<span>※ 당일 진행하는 행사일 경우 시작일과 종료일을 동일하게 지정</span>
			<hr>
			<input type="date" name="start_date" autocomplete="off" class="date">
			<span>부터</span> <input type="date" name="end_date" autocomplete="off"
				class="date"> <span>까지</span>
			<hr>
		</div>
		<p>
			<input type="text" name="benefit" placeholder="혜택 (20% 할인, 무료 초대 등)"
				maxlength="20" autocomplete="off" class="text">
		</p>
		<p>
			<input type="number" name="price" placeholder="가격" autocomplete="off"
				class="text">
		</p>
		<p>
			<input type="text" name="place" placeholder="장소" maxlength="20"
				autocomplete="off" class="text">
		</p>
		<p>
			<input type="text" name="website" placeholder="웹사이트 (http:// 포함)"
				autocomplete="off" class="text">
		</p>
		<p>
			<textarea name="description" placeholder="※ 상세 내용을 자유롭게 적어주세요."></textarea>
		</p>

		<h2>이미지</h2>
		<ul>
			<li>본문 : 게시물 내에 삽입되는 원본 이미지, 1200×1710px 권장</li>
			<li>포스터 : 목록에 삽입되는 썸네일 이미지, 없을 시 '본문' 이미지로 대체</li>
		</ul>
		<p class="wrap">
			<input type="text" name="attach_filename" placeholder="본문 *"
				class="text" readonly=""><input type="file" name="attach"
				class="file"><input type="button" name="attach_button"
				value="파일 선택" class="attach">
		</p>
		<p class="wrap thumbnail">
			<input type="text" name="attach_filename" placeholder="포스터"
				class="text" readonly=""><input type="file" name="attach"
				class="file"><input type="button" name="attach_button"
				value="파일 선택" class="attach">
		</p>

		<label class="agreement">
			<p>게시 처리 등 안내를 위해 담당자 이메일을 수집하며, 수집한 개인정보는 1년간 보관됩니다.</p>
			<p class="bold">
				<input type="checkbox" name="agree">위 내용에 동의합니다.
			</p>
		</label> <input type="submit" value="무료 게시 요청하기" class="submit">
	</form>


	<footer>
		<jsp:include page="../Common/Footer.jsp"></jsp:include>
	</footer>

	<script type="text/javascript">
		var _isLogged = true;
		var _appName = '';
		var _osName = '';
		var _pageView = '';
		var _serverTime = 1492419398130;
		var _clientTime = new Date().getTime();
		var _diffTime = _clientTime - _serverTime;
	</script>
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'https://www.google-analytics.com/analytics.js', 'ga');
		ga('create', 'UA-90020075-2', 'auto');
		ga('send', 'pageview');
	</script>



</body>
</html>