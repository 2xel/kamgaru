<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/ContestBoardWrite.css">
</head>
<body>
  <jsp:include page="../Common/Header2.jsp"></jsp:include>

  <div class="title">
    <div class="wrap">
      <h1>공모전/대외활동 <strong>무료</strong> 게시 요청</h1>
      <p>캠퍼스픽은 국내 <strong>최다 이용자 수</strong>를 보유한 대학생 SNS입니다.<br>대학생에게 유익한 맞춤 정보를 캠퍼스픽에서 효과적으로 홍보하세요.</p>
      <p>공모전(대외활동) 게시는 <strong>무료로 진행</strong>되며,<br>담당자의 검토 및 승인 절차를 걸쳐 48시간 이내 게시됩니다.</p>
      <p class="notice">* 별표 표시는 필수 항목입니다.</p>
    </div>
  </div>
  <form id="container">
    <h2>담당자 정보</h2>
    <p><input type="text" name="manager_name" placeholder="이름 *" maxlength="10" autocomplete="off" class="text"></p>
    <p><input type="number" name="manager_phone" placeholder="연락처 * (- 없이 숫자만)" maxlength="100" autocomplete="off" class="text"></p>
    <p><input type="email" name="manager_email" placeholder="이메일 *" maxlength="100" autocomplete="off" class="text"></p>
    <p><input type="text" name="manager_company" placeholder="소속 *" maxlength="100" autocomplete="off" class="text"></p>
    <h2>활동 개요</h2>
    <p><input type="text" name="title" placeholder="제목 *" maxlength="100" autocomplete="off" class="text"></p>
    <p><input type="text" name="website" placeholder="웹사이트 (http:// 포함)" autocomplete="off" class="text"></p>
    <div>
      <h3>종류 *</h3>
      <label><input type="radio" name="target" value="1" checked="">공모전</label>
      <label><input type="radio" name="target" value="2">대외활동</label>
    </div>
    <div class="category" data-targetfor="1">
      <h3>분야 *</h3>
      <label><input type="checkbox" name="category" value="101">기획/아이디어</label>
      <label><input type="checkbox" name="category" value="102">브랜드/네이밍</label>
      <label><input type="checkbox" name="category" value="103">광고/마케팅</label>
      <label><input type="checkbox" name="category" value="104">사진/영상/UCC</label>
      <label><input type="checkbox" name="category" value="105">디자인</label>
      <label><input type="checkbox" name="category" value="106">예체능</label>
      <label><input type="checkbox" name="category" value="107">문학/수기/시나리오</label>
      <label><input type="checkbox" name="category" value="108">IT/소프트웨어/게임</label>
      <label><input type="checkbox" name="category" value="109">기타</label>
    </div>
    <div class="category" data-targetfor="2" style="display: none;">
      <h3>분야 *</h3>
      <label><input type="checkbox" name="category" value="201">국내봉사</label>
      <label><input type="checkbox" name="category" value="202">해외봉사/탐방</label>
      <label><input type="checkbox" name="category" value="203">서포터즈</label>
      <label><input type="checkbox" name="category" value="204">기자단</label>
      <label><input type="checkbox" name="category" value="205">마케터</label>
      <label><input type="checkbox" name="category" value="206">기타</label>
    </div>
    <div>
      <h3>접수기간 *</h3>
      <input type="date" name="start_date" autocomplete="off" class="date">
      <span>부터</span>
      <input type="date" name="end_date" autocomplete="off" class="date">
      <span>까지</span>
      <hr>
    </div>
    <h2>기관 정보</h2>
    <p><input type="text" name="company" placeholder="주최 기관명 *" maxlength="100" autocomplete="off" class="text"></p>
    <div>
      <h3>주최 구분 *</h3>
      <label><input type="radio" name="company_type" value="1" checked="">중앙정부/기관</label>
      <label><input type="radio" name="company_type" value="2">지자체/기관</label>
      <label><input type="radio" name="company_type" value="3">학교/재단/협회</label>
      <label><input type="radio" name="company_type" value="4">비영리단체</label>
      <label><input type="radio" name="company_type" value="5">공기업</label>
      <label><input type="radio" name="company_type" value="6">일반기업</label>
      <label><input type="radio" name="company_type" value="7">신문/방송/언론</label>
      <label><input type="radio" name="company_type" value="8">기타</label>
    </div>
    <p><input type="text" name="company2" placeholder="주관 기관명" maxlength="100" autocomplete="off" class="text"></p>
    <p><input type="text" name="company3" placeholder="후원/협찬 기관명" maxlength="100" autocomplete="off" class="text"></p>
    <h2>시상 정보</h2>
    <p><input type="number" name="prize_top" placeholder="1등 시상금 (원)" autocomplete="off" class="text"></p>
    <div>
      <h3>총 시상금 규모</h3>
      <label><input type="radio" name="prize_total" value="1">5천만원이상</label>
      <label><input type="radio" name="prize_total" value="2">5천만원~3천만원</label>
      <label><input type="radio" name="prize_total" value="3">3천만원~1천만원</label>
      <label><input type="radio" name="prize_total" value="4" checked="">1천만원이하</label>
    </div>
    <div>
      <h3>특전</h3>
      <label><input type="checkbox" name="prize_benefit" value="1">입사가산점</label>
      <label><input type="checkbox" name="prize_benefit" value="2">인턴채용</label>
      <label><input type="checkbox" name="prize_benefit" value="3">정규직채용</label>
      <label><input type="checkbox" name="prize_benefit" value="4">해외연수</label>
      <label><input type="checkbox" name="prize_benefit" value="5">작품전시</label>
      <label><input type="checkbox" name="prize_benefit" value="6">기타</label>
    </div>
    <h2>상세 내용</h2>
    <p><textarea name="description" placeholder="※ 개요, 주제, 일정, 참가자격, 시상내역 및 혜택, 활동내용, 유의사항, 접수방법, 문의처 등 상세 내용을 자유롭게 적어주세요."></textarea></p>

    <h2>이미지</h2>
    <ul>
      <li>본문 : 게시물 내에 삽입되는 원본 이미지, 1200×1710px 권장</li>
      <li>포스터 : 목록에 삽입되는 썸네일 이미지, 없을 시 '본문' 이미지로 대체</li>
    </ul>
    <p class="wrap"><input type="text" name="attach_filename" placeholder="본문 *" class="text" readonly=""><input type="file" name="attach" class="file"><input type="button" name="attach_button" value="파일 선택" class="attach"></p>
    <p class="wrap thumbnail"><input type="text" name="attach_filename" placeholder="포스터" class="text" readonly=""><input type="file" name="attach" class="file"><input type="button" name="attach_button" value="파일 선택" class="attach"></p>

    <label class="agreement">
      <p>게시 처리, 유료 광고 게재 등 안내를 위해 담당자 이름, 연락처, 이메일, 소속을 수집하며, 수집한 개인정보는 1년간 보관됩니다.</p>
      <p class="bold"><input type="checkbox" name="agree">위 내용에 동의합니다.</p>
    </label>
    <input type="submit" value="무료 게시 요청하기" class="submit">
  </form>


  <footer>
    <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

  <script type="text/javascript">
    var _isLogged = true;
    var _appName = '';
    var _osName = '';
    var _pageView = '';
    var _serverTime = 1492419779331;
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