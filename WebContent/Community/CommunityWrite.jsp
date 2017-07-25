<%@ page import = "kr.kamgaru.DTO.CommunityDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/CommunityWrite.css">
<!-- <script>
	String filepath = getElementByName('ComImage').value;
</script> -->
</head>
<body>
<%-- <%
CommunityDTO list = (CommunityDTO) request.getAttribute("list");
%> --%>
  <jsp:include page="../Common/Header2.jsp"></jsp:include>
  <!-- <form id="container" action="comwriteok.kam?ComCode=?" method="POST"> -->
  <form id="container" action="comwriteok.kam" method="POST">
  	<!-- <input type="file" name="ComImage"> -->
<!--     <input type="file" name="ComImage" class="file"> -->
    <figure class="picture">
      <img src="../images/community.new.figure.empty.png" data-empty-src="images/community.new.figure.empty.png" id="filename">
      <span class="upload">

      <input type = "button" name = "attach" class="upload" >
		<input type="file" name="ComImage" class="file" onchange="javascript: document.getElementById('filename').value = this.value">
      </span>

    </figure>
    <section>
      <p><label class="label">이름</label><input type="text" name="ComTitle" placeholder="커뮤니티 이름" class="text"></p>
      <p><label class="label">설명</label><input type="text" name="ComContents" placeholder="커뮤니티 설명" class="text"></p>
    </section>
   
    <input type="submit" value="커뮤니티 등록하기" class="submit">
  </form>


  <footer>
    <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

  <script type="text/javascript">
    var _isLogged = true;
    var _appName = '';
    var _osName = '';
    var _pageView = '';
    var _serverTime = 1492587672161;
    var _clientTime = new Date().getTime();
    var _diffTime = _clientTime - _serverTime;
  </script>
  <script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-90020075-2', 'auto');
    ga('send', 'pageview');
  </script>



</body>
</html>
