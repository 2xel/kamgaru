<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>캠거루 비밀번호찾기</title>
	<meta charset="UTF-8">
	<link rel="shortcut icon" href="/Kamgaru/images/favicon.ico">
	<link type="text/css" href="../css/common.css" rel="stylesheet">
	<link type="text/css" href="../css/find.css" rel="stylesheet">
	<link rel="shortcut icon" href="../images/favicon.ico">

	<script type="text/javascript" src="../js/find.js"></script>
</head>
<body>
	<jsp:include page="../Common/Header2.jsp"></jsp:include>
	<div id="container" class="find">
		<h1 class="logo"><a href="/">캥거루 아이디/비밀번호 찾기</a></h1>




		<h2>비밀번호 찾기</h2>
		<form class="findpassword" id="findpassword" action="findpwd.min">
			<p class="input"><input type="text" name="email" id="email" class="text" placeholder="가입된 이메일" autocomplete="off"></p>
			<p class="submit"><input type="submit" value="확인" class="text"></p>
			<p class="description">※ 해당 이메일로 비밀번호룰 학인할 수 있는 방법을 안내해 드립니다.</p>
		</form>
		<div class="box">
			<h3>이메일이 오지 않는다면?</h3>
			<p>이메일 서비스 제공자 사정에 의해 즉시 도착하지 않을 수 있으니, 최대 30분 정도 기다리신 후 다시 시도해주세요. 그래도 오지 않는다면 이메일이 스팸편지함으로 이동하지 않았는지 확인해보세요.</p>
		</div>
	</div>

</body>
</html>