<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/ContestBoardSubView.css">
</head>
<body>
   <jsp:include page="../Common/Header2.jsp"></jsp:include>
  <section class="community">
    <div class="wrap">
      <figure style="background-image: url('https://cf-ccp.campuspick.com/cp_contest.jpg');"></figure>

      <a data-community-id="2570" class="button unpick">UNPICK</a>

      <h1><a href="/community?id=2570">공모전</a></h1>

      <p class="description">이 커뮤니티를 PICK하고 인기있는 공모전 소식을 확인해보세요!</p>

      <p class="details"><span class="pick">499648</span><span class="category">정보</span></p>
    </div>
  </section>

  <section class="menu">
    <div class="wrap">

      <a href="/community?id=2570&amp;url=%2Fpage%2Factivity%2Flist%3Ftarget%3D1">전체 공모전</a>

      <a href="/community?id=2570&amp;url=%2Fpage%2Factivity%2Fboard%3Ftarget%3D1%26board%3D1" class="active">팀원 모집</a>

      <a href="/community?id=2570&amp;url=%2Fpage%2Factivity%2Fboard%3Ftarget%3D1%26board%3D2">Q&amp;A</a>

      <a href="/community?id=2570&amp;url=%2Fpage%2Factivity%2Frequest">게시 요청</a>

    </div>
  </section>


  <div id="container" data-article-id="60" data-activity-target="1">
    <div class="title">
      <h1>팀원을 모집합니다.</h1>
      <span class="viewcount" data-article-view-count="18">18</span>
      <time data-article-created-at="2017-04-18 11:05:05">04/18 11:05</time>

      <hr>
    </div>
    <div class="activity">
      <h2>이 글에 대한 공모전</h2>
      <a data-community-url="/page/activity/view?id=315" href="/community?id=2570&amp;url=%2Fpage%2Factivity%2Fview%3Fid%3D315">
        <div class="thumb" style="background-image: url('https://cf-cpi.campuspick.com/activity/1492135251435209_thumb.jpg');"></div>
        <span class="dday" data-activity-end-date="2017-04-30">D-11</span>
        <h3>서울여자대학교 교육방송국에서 제30회 S.W.B.S 대학가요제 예선을 개최합니다!</h3>
        <p class="medium">서울여자대학교 교육방송국 S.W.B.S</p>
        <p class="small category"><span data-activity-category-id="106">예체능</span></p>
        <p class="small viewcount" data-activity-view-count="210">210</p>
        <hr>
      </a>
    </div>
    <div class="text">
      <h2>글 내용</h2>
      <p>저는 남자고 아주 잘생겼습니다.</p>
    </div>
    <div class="comments">
      <h2>댓글</h2>
      <ol class="group">
        <li class="empty">아직 작성된 댓글이 없습니다.</li>
      </ol>
    </div>

    <div class="buttons">
      <div class="wrap">
        <a class="comment">댓글달기</a>
        <hr>
      </div>
    </div>
    <form class="writecomment">
      <div class="wrap">
        <input type="text" name="text" placeholder="내용을 입력해주세요." autocomplete="off">
        <span class="submit"></span>
      </div>
    </form>

  </div>


  <footer>
    <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

  <script type="text/javascript">
    var _isLogged = true;
    var _appName = '';
    var _osName = '';
    var _pageView = '';
    var _serverTime = 1492589937738;
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