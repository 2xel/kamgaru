<%@ page import="kr.kamgaru.DTO.CommunityDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/CommunitySubList.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
<%
		int cpage = (Integer)request.getAttribute("cpage");
        int pagesize = (Integer)request.getAttribute("pagesize");
        int pagecount = (Integer)request.getAttribute("pagecount");
        int totalboardCount = (Integer)request.getAttribute("totalboardCount"); 
        ArrayList<CommunityDTO> list = (ArrayList)request.getAttribute("list");
	%>
    <jsp:include page="../Common/Header2.jsp"></jsp:include>
 
	
	<%-- <section class="community">
    <div class="wrap">
      <figure style="background-image: url"></figure>

      <h1><a href="/community?id=2697">${title}커뮤니티이름</a></h1>

      <p class="description">${title}커뮤니티설명</p>

      <!-- <p class="details"><span class="pick">615</span><span class="category">정보</span></p> -->
    </div>
  </section> --%>
<div id="container" data-community-id="2697" data-board-id="376565" data-article-id="" data-mode="" data-keyword="">
		<div class="buttons" style="display: block;">
			<div class="search">글 내용, 해시태그 검색</div>
			<form class="search">
				<div>
					<input type="search" name="keyword" autocomplete="off"
						placeholder="글 내용, 해시태그 검색"><span class="button"></span>
				</div>
				<span class="cancel">취소</span>
			</form>
			<hr>
		</div>
		
			<ol class="communities">
				<li class="empty"><a class="button" href="subwrite.kam?comcode=${comcode}">커뮤니티 내용 추가하기</a></li>
			</ol>
	<c:forEach var="board" items="<%=list%>">
				<c:set var="comboardid" value="${board.getComBoardID()}" />
				<c:set var="comcode" value="${board.getComCode()}" />
				<c:set var="memcode" value="${board.getMemCode()}" />
				<c:set var="title" value="${board.getTitle()}" />
				<c:set var="enrolldate" value="${board.getEnrollDate()}" />
				<c:set var="hit" value="${board.getHit() }" />
				<c:set var="replyCount" value="${board.getReplyCount()}"/>
				
    <form class="write"></form>
		<div class="articlelist" style="display: block;">
				
				<ol class="group">
				
				<li><a class="article" href="subview.kam?comboardid=${comboardid}"><p class="profile">
							<img class="picture"
								src=""><span
								class="nickname creator">${title}</span>
							<time>${enrolldate }</time>
						</p>
						<hr>
						<p class="text short">
							
						</p>
						
						<p class="status">
							<span class="viewcount" style=" padding-left: 14px; color: #a6a6a6; background: transparent url('https://www.campuspick.com/images/viewcount2.png') no-repeat left center; background-size: 12px 8px;">${hit}</span> 
						
						
						<span class="commentcount" style=" padding-left: 12px; color: #a6a6a6; background: transparent url('https://www.campuspick.com/images/commentcount.png') no-repeat left center; background-size: 9px 9px;">${replyCount}</span>
						<%-- <span><a class="button" href="comDeleteok.kam?comboardid=${comboardid}">커뮤니티 삭제</a></span> --%>
						<hr></a></li>
			</ol>
	</c:forEach>
	
  	
			<div class="loading" style="display: none;">
				<img src="/images/community.loading.svg">
			</div>
			
		</div>
		<div class="articleitem"></div>
  </div>


  <footer>
   <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

  <script type="text/javascript">
    var _isLogged = false;
    var _appName = '';
    var _osName = '';
    var _pageView = '';
    var _serverTime = 1492416250331;
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
