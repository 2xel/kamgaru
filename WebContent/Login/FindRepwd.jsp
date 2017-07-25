<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>캠거루 비밀번호변경</title>

	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="../js/jquery.validate.js"></script>
	<script src="../js/duplicator.join.js"></script>
	<link rel="shortcut icon" href="/Kamgaru/images/favicon.ico">
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/repwd.css">
	<% String memberid=(String)session.getAttribute("memcode"); %>
</head>
<body>
	<div id="container" class="register">
		<h1 class="logo"><a href="/">캠거루 비밀번호변경</a></h1>
		<form id="join" action="repwdok.min?" method="post">
			<div id="step2" class="step" style="display: block;">
				<h3>&nbsp;</h3>
					<input type="hidden" id="memcode" name="memcode" value="<%=memberid%>">
				<p>
					<input type="password" id="pwd" name="pwd" maxlength="20" class="text small" placeholder="비밀번호" autocomplete="off">
					<span class="description"></span>
				</p>
				<p>
					<input type="password" id="pwd2" name="pwd2" maxlength="20" class="text small" placeholder="비밀번호 확인" autocomplete="off">
					<span class="description"></span>
				</p>
			</div>
			<div>
				<h3>&nbsp;</h3>
				<p class="submit"><input type="submit" value="비밀번호 재설정" class="text"></p>
			</div>
		</form>

			<footer>
			<jsp:include page="../Common/Footer.jsp"></jsp:include>
			</footer>
	</div>

	<script type="text/javascript">
	$(document).ready(function () {
		
		$("#repwd").validate({
	        errorElement: 'span',
	        errorClass: 'description',
	        rules: {
	        	pwd: {required:true, minlength:5, maxlength:20},
	        	pwd2: {required:true, equalTo:'#pwd'}
	        },
	        messages: {
	        	pwd: {required:"필수입력 항목입니다", minlength:"{0}글자 이상 입력하세요", maxlength:"{0}자이하여야합니다"},
		        pwd2: {required:"필수입력 항목입니다", equalTo:"비밀번호가 일치하지 않습니다"}
	           }
        });
    });
    
	
	</script>
</body>
</html>