<%@ page import="kr.kamgaru.DTO.CommunityDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/CommunityList.css">
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
	<div id="container">
		<h1>커뮤니티</h1>
		<%-- <form class="search" action="search.kam?title=${comtitle}">
			<div>
				<input type="search" name="title" value="${title}" placeholder="커뮤니티를 검색해보세요!" autocomplete="off" > 
				<span class="button"><input type="submit" value="검색"> </span>
				
			</div>
			<span class="cancel">취소</span>
		</form> --%>
		<div class="result">
			<!-- <h2>내 커뮤니티</h2>
			<ol class="communities">
				<li><a class="community" href="/community?id=1076"><figure
							style="background-image: url(&quot;https://cf-ccp.campuspick.com/1487851958841481.jpg&quot;);"></figure>
						<p class="name">계란을 좋아하는 모임</p>
						<img class="new" src="/images/community.new.png">
					<hr>
						<p class="details">
							<span class="pick">3,487</span>
						</p></a></li>
				
			</ol> -->


			<h2>커뮤니티 목록</h2>
			<c:forEach var="board" items="<%=list%>">
				<c:set var="comcode" value="${board.getComCode()}" />
				<c:set var="comtitle" value="${board.getComTitle()}" />
				<c:set var="comcontents" value="${board.getComContents()}" />
				<c:set var="comimage" value="${board.getComImage()}" />
				<ol class="communities">
					<li><a class="community" href="sublist.kam?comcode=${comcode}"> 
					<figure style="background-image:"></figure>
							<p class="name"> ▶ ${comtitle}</p> <!-- <img class="new"
							src="/images/community.new.png"> -->
							<hr> <!-- <p class="details">
							<span class="pick">496,250</span><span class="category">정보</span>
						</p> -->
							<p class="description">${comcontents}</p></a></li>


				</ol>
			</c:forEach>
			<ol class="communities">
				<li class="empty">찾으시는 커뮤니티가 없다면<br>새로운 커뮤니티를 만들어보세요!<a
					class="button" href="comwrite.kam">새 커뮤니티 만들기</a></li>
			</ol>
			
			
		</div>
		<div class="loading" style="display: none;">
			<img src="/images/community.loading.svg">
		</div>
	</div>


	<footer> <jsp:include page="../Common/Footer.jsp"></jsp:include>
	</footer>

</body>
</html>
