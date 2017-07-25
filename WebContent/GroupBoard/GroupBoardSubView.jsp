<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/GroupBoardSubView.css">
</head>
<body>
  <jsp:include page="../Common/Header2.jsp"></jsp:include>
  <section class="community">
    <div class="wrap">
      <figure style="background-image: url('https://cf-ccp.campuspick.com/cp_circle.jpg');"></figure>

      <a data-community-id="2572" class="button pick">PICK</a>

      <h1><a href="/community?id=2572">동아리</a></h1>

      <p class="description">이 커뮤니티를 PICK하고 모집 공고를 확인하여 관심있는 동아리에 지원해보세요!</p>

      <p class="details"><span class="pick">496333</span><span class="category">정보</span></p>
    </div>
  </section>

  <section class="menu">
    <div class="wrap">

      <a href="/community?id=2572&amp;url=%2Fpage%2Fclub%2Frecruitlist">모집 공고</a>

      <a href="/community?id=2572&amp;url=%2Fpage%2Fclub%2Flist" class="active">동아리 랭킹</a>

      <a href="/community?id=2572&amp;boardId=369368">게시판</a>

      <a href="/community?id=2572&amp;url=%2Fpage%2Fclub%2Frequest">게시 요청</a>

    </div>
  </section>


  <div id="container" data-club-id="9">
    <div class="section title">

      <div class="image" style="background-image: url('https://cf-cpi.campuspick.com/club/1487843339450794.jpg');"></div>

      <h1>문화교류연합동아리 매디</h1>
      <span class="vote" data-vote="155">155</span>

      <a class="votebutton unvoted"></a>
      <p class="votebubble">마음에 드는 동아리에<br>좋아요를 눌러주세요.</p>

      <hr>
    </div>
    <div class="section category">
      <h2>분야</h2>
      <p data-category-id="1"><span>문화/예술/공연</span></p>
      <hr>
    </div>
    <div class="section local">
      <h2>활동 지역</h2>
      <p data-local-id="1"><span>수도권</span></p>
      <hr>
    </div>
    <div class="section size">
      <h2>규모</h2>
      <p data-size="4"><span>50명 이상</span></p>
      <hr>
    </div>

    <div class="section website">
      <h2>웹사이트</h2>
      <p><a href="http://ygvmsj28.wix.com/maddy0221" class="link">바로가기</a></p>
      <hr>
    </div>

    <div class="section">
      <h2>동아리 소개</h2>
      <hr>
      <article> “무슨 일을 하든지 마무리를 분명하게 짓는 사람이 되자!” <br><br>“20대에 깊은 추억을 남기자!”<br><br>라는 의미를 실현해 나가고 있는 대학생 연합동아리 ‘매디’입니다. <br><br>2017년 새학기를 맞아 매디와 함께 <br>20대를 20대답게 보내 볼 5기 회원님들을 모집합니다.<br><br>많은 대학생들이 스펙을 쌓고 과제를 완성하기 바쁩니다. <br>아름다운 추억을 만들어 나가야 하는 청춘을 이렇게 보내야 한다는 게 안타깝습니다. <br>매디에서 꽃다운 20대에 많은 문화활동을 통해 추억을 남기고 자신을 발견해 나가셨으면 좋겠습니다. <br><br>매디는 서울 및 경인지역 학생들로 이루어진 연합동아리입니다.<br><br>지역 친구, 학교 친구 등 익숙한 관계를 넘어, 다양한 지역, 다른 환경에서 자라온 사람들과 함께 문화교류, MT, 여행, 문화생활, 스터디 등을 즐길 준비가 되셨다면 지금 매디에 지원해주세요!! <br><br>대나무숲, 지역 조 활동, 다양한 체험 활동 등의 활동이 여러분을 기다리고 있습니다!</article>
    </div>
    <div class="recruits">
      <h2>모집 공고</h2>
      <ol class="group">
        
      <li><a class="item" href="/community?id=2572&amp;url=%2Fpage%2Fclub%2Frecruitview%3Fid%3D11"><time>02/23</time><h3>문화교류연합동아리 매디에서 5기를 모집합니다(~3.10)</h3><hr></a></li></ol>
    </div>
  </div>


  <footer>
    <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

  <script type="text/javascript">
    var _isLogged = true;
    var _appName = '';
    var _osName = '';
    var _pageView = '';
    var _serverTime = 1492590888721;
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