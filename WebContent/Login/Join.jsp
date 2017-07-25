<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>캠거루 회원가입</title>
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="../js/jquery.validate.js"></script>
	<script src="../js/duplicator.join.js"></script>
	<link rel="shortcut icon" href="/Kamgaru/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/Join.css">
</head>
<body>
	<jsp:include page="../Common/Header2.jsp"></jsp:include>
	<div id="container" class="register">
		<h1 class="logo"><a href="../index.jsp">캠거루 회원가입</a></h1>
		<form id="join" action="Joinok.min" method="post">
			<div id="step1" class="step" style="display: block;">
				<input type="hidden" name="campus_id">
				<h2>학교 입력</h2>
				<p class="search">
					<input type="text" id="university" name="university" maxlength="20" class="text" placeholder="학교명" autocomplete="off">
				</p>
			</div>
			<div id="campusSearch">
				<h2>학교 선택</h2>
				<ol id="campusSuggest" class="suggest"></ol>
			</div>
			<div id="step2" class="step" style="display: block;">
				<h3>&nbsp;</h3>
				<h2>로그인 정보 입력</h2>
				<p>
					<input type="text" id="email" name="email" class="text" placeholder="이메일" autocomplete="off">
					<span class="description"></span>
				</p>
				<p>
					<input type="password" id="pwd" name="pwd" maxlength="20" class="text small" placeholder="비밀번호" autocomplete="off">
					<span class="description"></span>
				</p>
				<p>
					<input type="password" id="pwd2" name="pwd2" maxlength="20" class="text small" placeholder="비밀번호 확인" autocomplete="off">
					<span class="description"></span>
				</p>
			</div>
			<div id="step3" class="step" style="display: block;">
				<h3>&nbsp;</h3>
				<h2>개인 정보 입력</h2>
				<p>
					<input type="text" id="name" name="name" maxlength="20" class="text" placeholder="이름 (실명)" autocomplete="off">
					<span class="description"></span>
				</p>
				<p>
					<input type="text" id="nickname" name="nickname" maxlength="10" class="text" placeholder="닉네임" autocomplete="off">
					<span class="description"></span>
				</p>
			</div>
			<div>
				<h3>&nbsp;</h3>
				<p class="submit"><input type="submit" value="회원가입" class="text"></p>
			</div>
		</form>

			<footer>
			<jsp:include page="../Common/Footer.jsp"></jsp:include>
			</footer>
	</div>

</body>
</html>