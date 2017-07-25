<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>캠거루</title>
   
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"/></sript>
	<script src="js/underscore.js"></script>
	<script src="js/common.js"></script>
	<script src="js/list.index.js"></script>
   
   <link rel="shortcut icon" href="/Kamgaru/images/favicon.ico">
   <link rel="stylesheet" type="text/css" href="css/common.css">
   <link rel="stylesheet" type="text/css" href="css/Index.css">
</head>
<body>
   <jsp:include page="./Common/Header2.jsp"></jsp:include>
   
   <div id="container">
   
      <section class="download">
         <h1 class="text3">캠거루 - 대학생 SNS</h1>
         <div class="buttons">
            <a href="/" class="playstore">캠거루 다운로드 - Google Play</a>
            <a href="/" class="appstore">캠거루 다운로드 - AppStore</a>
         </div>
      </section>

      <section class="center">
         <div class="recent" data-id="2572">
            <div class="title">
               <h2><a href="<%=request.getContextPath()%>/GroupBoard/GroupBoardList.kam">동아리</a></h2>
               <p class="description">이 커뮤니티를 PICK하고 모집 공고를 확인하여 관심있는 동아리에 지원해보세요!</p>
            </div>
            
            <ol class="items">
               <div class="loading"><img src="images/community.loading.svg"></div>
            </ol>
            
            <div class="buttons">
               <a href="<%=request.getContextPath()%>/GroupBoard/GroupBoardList.kam" class="button blue">모집 공고 더 보기</a>
               <a href="<%=request.getContextPath()%>/GroupBoard/GroupBoardRankList.kam" class="button gray">동아리 랭킹 보기</a>
            </div>
         </div>

      <div class="cards">

        <div class="card">
          <p class="profile">
            <span class="badge">AD</span>
            <img class="picture" src="https://cf-eba.everytime.kr/20170425_cpcard_yellowenvelope_profile.jpg">
            <span class="nickname">광고</span>
          </p>
          <figure class="thumbnail"><img src="<%=request.getContextPath()%>/images/songvely.jpg"></figure>
          <div class="content">
            <h3>송블리 팬클럽</h3>
            <p class="text">코스타의 아름다운 미녀 송블리<br> 송블리 팬클럽에 방문해보세요.</p>
            <a href="http://192.168.0.183:8090/songvely/index.html" class="button">홈페이지 방문하기</a>
          </div>
        </div>
      </div>
        
         <div class="recent" data-id="2571">
            <div class="title">
               <h2><a href="<%=request.getContextPath()%>/ActivityBoard/activityboardlist.kam">대외활동</a></h2>
               <p class="description">이 커뮤니티를 PICK하고 인기있는 대외활동 소식을 확인해보세요!</p>
            </div>
            
            <ol class="items">
               <div class="loading"><img src="images/community.loading.svg"></div>
            </ol>
            
            <div class="buttons">
               <a href="<%=request.getContextPath()%>/ActivityBoard/activityboardlist.kam" class="button blue">대외활동 더 보기</a>
               <a href="<%=request.getContextPath()%>/ActivityBoard/ActivityBoardSubList.jsp" class="button gray">팀원 모집 게시판</a>
            </div>
         </div>
      
         <div class="recent" data-id="2570">
            <div class="title">
               <h2><a href="<%=request.getContextPath()%>/Common/ContestListAction.kam">공모전</a></h2>
               <p class="description">이 커뮤니티를 PICK하고 인기있는 공모전 소식을 확인해보세요!</p>
            </div>
            
            <ol class="items">
               <div class="loading"><img src="images/community.loading.svg"></div>
            </ol>
         
            <div class="buttons">
               <a href="<%=request.getContextPath()%>/Common/ContestListAction.kam" class="button blue">공모전 더 보기</a>
               <a href="<%=request.getContextPath()%>/Common/ContestBoardTeam.kam" class="button gray">팀원 모집 게시판</a>
            </div>
         </div>
      
      </section>
    
   <!-- 로그인/회원가입 Form -->
      <section class="leftside">
      <%
         if(session.getAttribute("memcode")!=null){
        	 if(session.getAttribute("memcode").equals("1")){
	            out.print("<div class='logged'>");
	            out.print("<img src='https://cf-epi.everytime.kr/0.png' class='picture'>");
	            out.print("<span class='nickname'>"+ session.getAttribute("nickname") + "</span>");
	            out.print("<a href='"+request.getContextPath()+"/Common/AdminListAction.kam' class='button login'>관리자화면</a>");
	            out.print("<a href='"+request.getContextPath()+"/Login/Logout.jsp' class='button register'>로그아웃</a>");
	            out.print("<hr></div>");
        	 }else{
                 out.print("<div class='logged'>");
                 out.print("<img src='https://cf-epi.everytime.kr/0.png' class='picture'>");
                 out.print("<span class='nickname'>"+ session.getAttribute("nickname") + "</span>");
                 out.print("<a href='"+request.getContextPath()+"/Login/Mypage.jsp' class='button login'>마이페이지</a>");
                 out.print("<a href='"+request.getContextPath()+"/Login/Logout.jsp' class='button register'>로그아웃</a>");
                 out.print("<hr></div>");
        	 }
         }else{
            out.print("<div class='login'>");
            out.print("<p class='introduction'>관심있는 커뮤니티를 PICK하고<br>다른 대학생들과 대화를 나누어 보세요!</p>");
            out.print("<a href='"+request.getContextPath()+"/Login/Login.jsp' class='button login'>로그인</a>");
            out.print("<a href='"+request.getContextPath()+"/Login/Join.jsp' class='button register'>회원가입</a>");
            out.print("<hr></div>");
         }
      %>
      </section>
   </div>

   <footer> 
   <jsp:include page="./Common/Footer.jsp"></jsp:include>
   </footer>
     <script type="text/javascript">
    var _isLogged = false;
  </script>

</body>
</html>