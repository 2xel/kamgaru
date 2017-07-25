<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>캠거루</title>
   
	<script src="https://www.campuspick.com/js/jquery-3.1.1.min.js"></script>
	<script src="../js/jquery.validate.js"></script>
   <script src="../js/duplicator.join.js"></script>
   
   <link rel="stylesheet" type="text/css" href="../css/common.css">
   <link rel="stylesheet" type="text/css" href="../css/Mypage.css">
</head>
<body>
   <jsp:include page="../Common/Header2.jsp"></jsp:include>
   
	<div id="container" data-token="">
    	<div class="profile">
			<img src="https://cf-epi.everytime.kr/0.png" class="picture">
			<span class="nickname"><%=session.getAttribute("nickname")%></span>
			<span class="userid"><%=session.getAttribute("email")%></span>
		</div>
		<h1>회원정보 수정하기</h1>
			<form id="mypage" action="Mypage.min" method="post">
				<div id="step1" class="step" style="display: block;">
					<h2>&nbsp;</h2>
					<input type="hidden" name="memcode" id="memcode" value="<%=session.getAttribute("memcode")%>">
					<h2>학교 입력</h2>
					<p class="search">
					<input type="text" id="university" name="university" value="<%=session.getAttribute("university")%>" maxlength="20" class="text" placeholder="학교명" autocomplete="off">
					</p>
				</div>
				<div id="campusSearch">
					<h2>학교 선택</h2>
					<ol id="campusSuggest" class="suggest"></ol>
				</div>
				<div id="step2" class="step" style="display: block;">
					<h2>&nbsp;</h2>
					<h2>변경전 비밀번호</h2>
					<p>
					<input type="password" id="oldpwd" name="oldpwd" maxlength="20" class="text small" placeholder="현재 비밀번호" autocomplete="off">
					<span class="description"></span>
					</p>
					<h2>&nbsp;</h2>
					<h2>변경할 비밀번호</h2>
					<p>
					<input type="password" id="pwd" name="pwd" maxlength="20" class="text small" placeholder="변경할 비밀번호" autocomplete="off">
					<span class="description"></span>
					</p>
					<p>
					<input type="password" id="pwd2" name="pwd2" maxlength="20" class="text small" placeholder="변경할 비밀번호 확인" autocomplete="off">
					<span class="description"></span>
					</p>
				</div>
				<div>
					<h3>&nbsp;</h3>
					<p class="submit"><input type="submit" value="회원정보 수정" class="text"></p>
				</div>
			</form>
	</div>
	 <%
         if(session.getAttribute("memcode")==null){
				out.println("<script>");
				out.println("history.back();");
				out.println("</script>");
         }
      %>
		<footer> 
		<jsp:include page="../Common/Footer.jsp"></jsp:include>
		</footer>
		
			<script type="text/javascript">
	$(document).ready(function () {
		
		$("#mypage").validate({
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