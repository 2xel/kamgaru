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
		ArrayList<ContestDTO> list = (ArrayList) request.getAttribute("list");
		//ArrayList<ContestDTO> comment = (ArrayList)request.getAttribute("comment");
		
%>

  <jsp:include page="../Common/Header2.jsp"></jsp:include>

  <section class="community">
    <div class="wrap">
      <figure style="background-image: url('https://cf-ccp.campuspick.com/cp_contest.jpg');"></figure>

      <a data-community-id="2570" class="button unpick">UNPICK</a>

      <h1><a href="/community?id=2570">공모전</a></h1>

      <p class="description">이 커뮤니티를 PICK하고 인기있는 공모전 소식을 확인해보세요!</p>

      <p class="details"><span class="pick">${totalboardCount }</span><span class="category">정보</span></p>
    </div>
  </section>

  <section class="menu">
    <div class="wrap">

      <a href="<%=request.getContextPath() %>/Common/ContestListAction.kam">전체 공모전</a>

      <a href="<%=request.getContextPath() %>/Common/ContestBoardTeam.kam" class="active">팀원 모집</a>


      <a href="<%=request.getContextPath() %>/Common/Write.jsp">게시 요청</a>

    </div>
  </section>


<br>
  <div id="container" data-target="1" data-board="1" data-activity-id="">
 
  
  <ol class="group">
	     <c:forEach var="team" items="<%=list%>">
		      		<c:set var="enrolldate" value="${team.getReplyEnrollDate()}" />
					<c:set var="contents" value="${team.getReplyContents()}" />
					<c:set var="boarid" value="${team.getBoardId()}" />
					<c:set var="title" value="${team.getTitle()}" />
				<li>
				
					<%-- <a class="item" href="../ContestBoard/ContestBoardView.jsp?boardid=${team.getBoardId()}"> --%>
					 <a class="item" href="../Common/ContestBoardViewAction.kam?boardid=${team.getBoardId()}">
							<h2>${contents}</h2>
							<time>${enrolldate}</time>
							<hr>
							<h3>${title}</h3>
							<hr>
					</a>
				</li>
	      </c:forEach>
    </ol>
    
 	<div class="page_nb" id="list_page">
		<ul>
			<li>
				<span>
					<c:if test="${cpage>1}">
						<a href="ContestBoardTeam.kam?cp=${cpage-1}&ps=${pagesize}">이전</a>
					</c:if> 
					<c:forEach var="i" begin="1" end="${pagecount}" step="1">
							<c:choose>
								<c:when test="${cpage==i}">
									<font color='#1DCDFF'>[${i}]</font>
								</c:when>
								<c:otherwise>
									<a href="ContestBoardTeam.kam?cp=${i}&ps=${pagesize}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach> 
					<c:if test="${cpage<pagecount}">
							<a href="ContestBoardTeam.kam?cp=${cpage+1}&ps=${pagesize}">다음</a>
					</c:if>
				</span>
			</li>
		</ul>
	</div>

  <footer>
    <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

  <script type="text/javascript">
    var _isLogged = true;
    var _appName = '';
    var _osName = '';
    var _pageView = '';
    var _serverTime = 1492587845492;
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