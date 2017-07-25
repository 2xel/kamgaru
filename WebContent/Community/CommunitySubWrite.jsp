<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/CommunitySubWrite.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ComCode = (String) request.getAttribute("comcode");
   
%>
</head>
<body>
	<jsp:include page="../Common/Header2.jsp"></jsp:include>

	<section class="community">
	<div class="wrap">
		<figure></figure>

		<!-- <a data-community-id="2757" class="button setting">설정</a> -->

		<h1>
			<a href="">커뮤니티이름</a>
		</h1>

		<p class="description">커뮤니티설명</p>

		<p class="details">
		<!-- 	<span class="pick">1</span><span class="category">기타</span> -->
		</p>
	</div>
	</section>


	<div id="container" data-community-id="2757" data-board-id="378171"
		data-article-id="" data-article-token="" data-mode="write"
		data-keyword="">
		<div class="buttons"></div>
		<form class="write" style="display: block;" action="subwriteok.kam" method="POST">
			<p class="text">
				<!-- <textarea name="ComCode" placeholder="커뮤니티 번호입력해주세요."></textarea>
				<textarea name="MemCode" placeholder="회원 번호를 입력해주세요."></textarea> -->
				<textarea name="Title" placeholder="내용을 입력해주세요."></textarea>
			</p>
			<span class="attach detached"></span><input class="button"
				type="reset" value="취소">
				<input class="submit" type="submit" value="완료">
			<hr>
			<input type="hidden">
		</form>
		<div class="articlelist"></div>
		<div class="articleitem"></div>
	</div>


	<footer> <jsp:include page="../Common/Footer.jsp"></jsp:include>
	</footer>

	<script type="text/javascript">
		var _isLogged = true;
		var _appName = '';
		var _osName = '';
		var _pageView = '';
		var _serverTime = 1493085874951;
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