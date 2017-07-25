<%@page import="java.util.ArrayList"%>
<%@page import="kr.kamgaru.DAO.DAO"%>
<%@page import="kr.kamgaru.DTO.ActivityDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/ActivityBoardSubView.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
<%
		int cpage = (Integer) request.getAttribute("cpage");
		int pagesize = (Integer) request.getAttribute("pagesize");
		int pagecount = (Integer) request.getAttribute("pagecount");
		int totalboardCount = (Integer) request.getAttribute("totalboardCount");
		ArrayList<ActivityDTO> list = (ArrayList) request.getAttribute("list");
	%>
  <jsp:include page="../Common/Header2.jsp"></jsp:include>
  <section class="community">
    <div class="wrap">
      <figure style="background-image: url('https://cf-ccp.campuspick.com/cp_activity.jpg');"></figure>

      <h1><a href="/community?id=2571">대외활동</a></h1>

      <p class="description">이 커뮤니티를 PICK하고 인기있는 대외활동 소식을 확인해보세요!</p>

      <p class="details"><span class="pick">498092</span><span class="category">정보</span></p>
    </div>
  </section>

  <section class="menu">
    <div class="wrap">

      <a href="activityboardlist.kam">전체 대외활동</a>

      <a href="/ActivityBoard/ativityBoardTeam.kam" class="active">팀원 모집</a>

    </div>
  </section>


  <div id="container" data-article-id="58" data-activity-target="2">
    <div class="title">
      <h1>같이 하실분~</h1>
      <span class="viewcount" data-article-view-count="18">18</span>
      <time data-article-created-at="2017-04-17 15:33:32">04/17 15:33</time>

      <hr>
    </div>
    <div class="activity">
      <h2>이 글에 대한 대외활동</h2>
      <a data-community-url="/page/activity/view?id=328" href="/community?id=2571&amp;url=%2Fpage%2Factivity%2Fview%3Fid%3D328">
        <div class="thumb" style="background-image: url('https://cf-cpi.campuspick.com/activity/1492402287829370_thumb.jpg');"></div>
        <span class="dday" data-activity-end-date="2017-05-08">D-21</span>
        <h3>동성제약 대학생 마케팅 서포터즈 동행 모집</h3>
        <p class="medium">동성제약</p>
        <p class="small category"><span data-activity-category-id="203">서포터즈</span></p>
        <p class="small viewcount" data-activity-view-count="53">53</p>
        <hr>
      </a>
    </div>


  <footer>
    <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

  

</body>
</html>
