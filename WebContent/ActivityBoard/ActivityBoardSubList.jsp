<%@page import="kr.kamgaru.DTO.Activityreply"%>
<%@page import="kr.kamgaru.DTO.ActivityDTO"%>
<%@page import="kr.kamgaru.DTO.ContestDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/ContestBoardSubList.css">
<link rel="stylesheet" type="text/css" href="../css/Paging.css">
</head>
<body>
	<%
			int cpage = (Integer)request.getAttribute("cpage");
			int pagesize = (Integer)request.getAttribute("pagesize");
			int pagecount = (Integer)request.getAttribute("pagecount");
			int totalboardCount = (Integer)request.getAttribute("totalboardCount"); 
			String BoardId = (String) request.getAttribute("boardid");		
			ArrayList<Activityreply> list = (ArrayList) request.getAttribute("list");

	%>
	<jsp:include page="../Common/Header2.jsp"></jsp:include>

	<section class="community">
		<div class="wrap">
			<figure
				style="background-image: url('https://cf-ccp.campuspick.com/cp_contest.jpg');"></figure>

		
			<h1>
				<a href="../ActivityBoard/activityboardlist.kam"">대외활동</a>
			</h1>

			<p class="description">아 하기시러</p>

			<p class="details"> 
				<span class="pick">499619</span><span class="category">정보</span>
			</p>
		</div>
	</section>

	<section class="menu">
		<div class="wrap">

			<a href="<%=request.getContextPath()%>/ActivityBoard/activityboardlist.kam">전체 대외활동</a> 
			<a href="../ActivityBoard/activityBoardTeam.kam" class="active">팀원 모집</a> 
			<a href="<%=request.getContextPath()%>/Common/Write.jsp">게시 요청</a>

		</div>
	</section>

	<div id="container" data-target="1" data-board="1" data-activity-id="">
		<a data-community-url="/page/activity/write?target=1&amp;board=1&amp;activityId=" class="floating write"
			href="/community?id=2570&amp;url=%2Fpage%2Factivity%2Fwrite%3Ftarget%3D1%26board%3D1%26activityId%3D">글쓰기</a>

		<ol class="group">
			<c:forEach var="team" items="<%=list%>">
				<c:set var="enrolldate" value="${team.getReplyEnrollDate()}" />
				<c:set var="contents" value="${team.getReplyContents()}" />
				<c:set var="boarid" value="${team.getBoardID()}" />
				<c:set var="memcode" value="${team.getMemCode()}" />
				<c:set var="title" value="${team.getTitle()}" />
	
				<li><a class="item" href="../ActivityBoard/activityboardcontent.kam?boardid=${boarid}">

						<h2>${contents}</h2> <time>${enrolldate}</time>
						<hr>
						<h3>${title}</h3>
						<hr>
				</a></li>
			</c:forEach>
			<!--  <li class="empty">아직 작성된 글이 없습니다.</li> -->
		</ol>
	</div>
		
		
		<!-- 페이징 처리  -->
		<div class="page_nb" id="list_page">
		<ul>
			<li>
				<span>
					<c:if test="${cpage>1}">
						<a href="activityBoardTeam.kam?cp=${cpage-1}&ps=${pagesize}">이전</a>
					</c:if> 
					<c:forEach var="i" begin="1" end="${pagecount}" step="1">
							<c:choose>
								<c:when test="${cpage==i}">
									<font color='#1DCDFF'>[${i}]</font>
								</c:when>
								<c:otherwise>
									<a href="activityBoardTeam.kam?cp=${i}&ps=${pagesize}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach> 
					<c:if test="${cpage<pagecount}">
							<a href="activityBoardTeam.kam?cp=${cpage+1}&ps=${pagesize}">다음</a>
					</c:if>
				</span>
			</li>
		</ul>
	</div>
			
		<footer>
			<jsp:include page="../Common/Footer.jsp"></jsp:include>
		</footer>

</body>
</html>