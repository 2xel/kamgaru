<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/CultureBoardList.css">
</head>
<body>
	<jsp:include page="../Common/Header2.jsp"></jsp:include>
	<section class="community">
		<div class="wrap">
			<figure></figure>

			<a data-community-id="2605" class="button unpick">UNPICK</a>

			<h1>
				<a href="/community?id=2605">문화 정보</a>
			</h1>

			<p class="description">이 커뮤니티를 PICK하고 영화, 공연, 전시 등 문화 정보 및 할인 혜택을
				만나보세요!</p>

			<p class="details">
				<span class="pick">491586</span><span class="category">정보</span>
			</p>
		</div>
	</section>

	<section class="menu">
		<div class="wrap">

			<a href="/community?id=2605&amp;url=%2Fpage%2Fculture%2Flist"
				class="active">전체</a> <a
				href="/community?id=2605&amp;url=%2Fpage%2Fculture%2Flist%3FcategoryId%3D1">영화</a>

			<a
				href="/community?id=2605&amp;url=%2Fpage%2Fculture%2Flist%3FcategoryId%3D2">공연</a>

			<a
				href="/community?id=2605&amp;url=%2Fpage%2Fculture%2Flist%3FcategoryId%3D6">기타</a>

			<a href="./CultureBoardWrite.jsp">게시
				요청</a>

		</div>
	</section>


	<div id="container" data-category-id="">
		<div class="filter">
			<p>찾으시는 문화 정보가 있나요?</p>
		</div>
		<form class="search">
			<div>
				<input type="search" name="keyword" autocomplete="off"> <span
					class="button"></span>
			</div>
			<span class="cancel">취소</span>
		</form>
		<ol class="group">
			<li><a class="item"
				href="./CultureBoardView.jsp"><div
						class="thumb"
						data-image="https://cf-cpi.campuspick.com/culture/1492417878333465_thumb.jpg"
						style="background-image: url(&quot;https://cf-cpi.campuspick.com/culture/1492417878333465_thumb.jpg&quot;);"></div>
					<span class="category">공연</span><span class="event">이벤트</span>
				<h2>대학로 흥행깡패 연극 &lt;유도소년&gt; 초대 이벤트!</h2>
					<p>4월 17일(월) ~ 4월 21일(금)</p>
					<p class="highlight">20명 초대</p>
					<p>
						<span class="viewcount">3</span>
					</p>
					<hr></a></li>
		
		</ol>
	</div>


	<footer>
		<jsp:include page="../Common/Footer.jsp"></jsp:include>
	</footer>

	<script type="text/javascript">
		var _isLogged = true;
		var _appName = '';
		var _osName = '';
		var _pageView = '';
		var _serverTime = 1492418707277;
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