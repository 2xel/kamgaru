<%@page import="kr.kamgaru.DAO.DAO"%>
<%@page import="kr.kamgaru.service.Community.ComSubViewAction"%>
<%@page import="kr.kamgaru.DTO.CommunityDTO"%>
<%@page import="kr.kamgaru.DTO.CommunityReplyDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css"
	href="../css/CommunitySubView.css">
<!-- <link rel="stylesheet" type="text/css" href="../css/groupBoardView.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="../css/club.view.css"> -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ComBoardId = (String) request.getAttribute("comboardid");
    CommunityDTO dto = (CommunityDTO) request.getAttribute("dto");
    ArrayList<CommunityReplyDTO> replyResult =(ArrayList)request.getAttribute("replyResult");
  //ArrayList<CommunityDTO> list = (ArrayList)request.getAttribute("list");
%>
</head>
<script type="text/javascript">
function CoDelete(){
	if(!confirm("정말 커뮤니티를 삭제 하시겠습니까?")){
	      return;
	   }
	   
	   var ComBoardId = '<%=ComBoardId%>';
			var loginData = null; //session 값

			/* if(loginData == null){
			   alert("로그인 해주세요.");
			   return;
			}else{
			   if(loginData.memberCode != memCode){
			      alert("본인의 글만 삭제 할 수 있습니다.");
			      return;
			   }
			} */

			var param_data = {};
			param_data.ComBoardID = ComBoardId;
	$.ajax({
		url : "comDelete.kam",
		data : param_data,
		success : function(data) {

			alert("ok");

			//새로고침
			location.reload();
		},
		error : function(e) {
			alert(e.responseText);
		}
	});
	
}
//댓글작성
function fnReplyWrite(){
   
   var ComBoardId = '<%=ComBoardId%>';
   var loginData = null; //session 값
   
   var comment_input = $("#comment_input").val();
   
   if(comment_input == ""){
      alert("내용을 입력해주세요.");
      return;
   }
  
   
   var param_data = {};
   param_data.ComBoardID = ComBoardId;
   param_data.MemCode = 1; // loginData.MemCode;
   param_data.Contents = encodeURIComponent(comment_input);
   
   $.ajax({
      url:"comReplyok.kam",
      data:param_data,
      success:function(data){
         //alert("ok");
         
         //새로고침
         location.reload();
      },
      error:function(e){
         alert(e.responseText);
      }
   });
   
}
//댓글 삭제
function fnReplyDelete(communityCn , memCode){
   
   if(!confirm("댓글을 삭제 하시겠습니까?")){
      return;
   }
   
   
   var ComBoardId = '<%=ComBoardId%>';
		var loginData = null; //session 값

		/* if(loginData == null){
		   alert("로그인 해주세요.");
		   return;
		}else{
		   if(loginData.memberCode != memCode){
		      alert("본인의 글만 삭제 할 수 있습니다.");
		      return;
		   }
		} */

		var param_data = {};
		param_data.ComBoardID = ComBoardId;
		param_data.CommunityCn = communityCn;

		$.ajax({
			url : "comReplyDelete.kam",
			data : param_data,
			success : function(data) {

				alert("ok");

				//새로고침
				location.reload();
			},
			error : function(e) {
				alert(e.responseText);
			}
		});

	}
function CoDelete(){
	if(!confirm("정말 커뮤니티를 삭제 하시겠습니까?")){
	      return;
	   }
	   
	   var ComBoardId = '<%=ComBoardId%>';
			var loginData = null; //session 값

			/* if(loginData == null){
			   alert("로그인 해주세요.");
			   return;
			}else{
			   if(loginData.memberCode != memCode){
			      alert("본인의 글만 삭제 할 수 있습니다.");
			      return;
			   }
			} */

			var param_data = {};
			param_data.ComBoardID = ComBoardId;
	$.ajax({
		
		url : "comDeleteok.kam",
		data : param_data,
		success : function(data) {

			alert("ok");

			//새로고침
			location.reload();
		},
		error : function(e) {
			alert(e.responseText);
		}
	});
	
}

	function test() {

		console.log(event.key);
	}
</script>
<body>


	<jsp:include page="../Common/Header2.jsp"></jsp:include>
	<section class="community">
		<div class="wrap">
			<figure style="background-image: url('');"></figure>

			<h1>
				<a href="">${comtitle } 커뮤니티 이름</a>
			</h1>

			<p class="description">${comcontents }커뮤니티 설명</p>

			      <a class="button" href="comDeleteok.kam?comboardid=${boardid}">커뮤니티 삭제</a>

			<!-- <p class="details">
            <span class="pick">615</span><span class="category">정보</span>
         </p> -->
		</div>
	</section>

	<div class="articlelist" style="display: block;">


		<div id="container" data-community-id="2697" data-board-id="376565"
			data-article-id="23298710" data-mode="" data-keyword="">
			<div class="buttons"></div>
			<form class="write"></form>
			<div class="articlelist"></div>
			<div class="articleitem" style="display: block;">
				<article class="article">
					<!-- <p class="profile">

						<img class="picture"
							src="https://cf-epi.everytime.kr/1246955/1479727588.png"><span
							class="nickname creator">커뮤니티 제목</span>
						<time>시간</time>

					</p>
					<input type="button" value="커뮤니티 삭제" onclick="CoDelete()"> -->
					<!-- action="comDeleteok.kam" -->

					<hr>
					<figure class="attach">
						<c:forEach var="reply" items="<%=replyResult%>">
							<c:set var="contents" value="${reply.getContents()}" />
							<li class="comment"><p class="text">${contents}</p> <time>04/15
									16:18</time>
								<hr></li>

							<!-- <p class="text">

                        <br>면접 떨림 극복하는 방법,<br>Job Fit 멘토의 어드바이스 바로가기<br>▶<a
                           href="http://joburl.kr/GqbVl">http://joburl.kr/GqbVl</a>
                     </p> -->
							<!-- <p class="status">
                        <span class="votecount">0</span><span class="commentcount">0</span><span
                           class="scrapcount">0</span>
                     </p> -->
							<!--  <p class="attach">
                        <span class="attachcount">1</span>
                     </p> -->
							<hr>
						</c:forEach>
					</figure>

				</article>
				<form class="writecomment" onsubmit="return false;"
					style="padding: 8px; background-color: #fff;">
					<input type="hidden" name="test" />
					<div class="wrap">
						<input id="comment_input" autocomplete="off" type="text"
							style="padding: 8px 40px 8px 8px; width: 100%; height: 40px; line-height: 24px; box-sizing: border-box; color: #292929; font-size: 16px; background-color: transparent;"
							placeholder="댓글을 입력해주세요."
							onkeydown="javascript: if(event.keyCode == 13) fnReplyWrite();">
						<span class="submit" onclick="fnReplyWrite();" style="background: transparent url(/images/community.board.submit.png);"></span>
					</div>
				</form>
				<div class="comments">
					<ol class="group">
						<c:forEach var="reply" items="<%=replyResult%>">
							<c:set var="boardid" value="${reply.getComBoardID() }" />
							<c:set var="memcode" value="${reply.getMemCode() }" />
							<c:set var="contents" value="${reply.getContents()}" />
							<c:set var="communityCn" value="${reply.getCommunityCN()}" />
							<c:set var="enrolldate" value="${reply.getEnrollDate() } " />
							<%-- <c:set var="communityCn" value="${reply.getCommunityCn() } " /> --%>
							<li class="parent"><a class="article"><p class="profile">
										<!-- <img class="picture"
											src="https://cf-epi.everytime.kr/551878/11705103_450833021766194_2626012998751863965_n.png"> -->
										<span class="nickname creator">${memcode}</span><span
											class="more"></span>
										<time>${enrolldate}</time>${reply.getComBoardID()}
									</p>

									<hr>
									<p class="text">${contents}</p> 
									<input type="button" onclick="fnReplyDelete(${communityCn},${memcode})" value="삭제" style="float: right; line-height: 15px; color: #737373; font-size: 12px;">
						</c:forEach>


					</ol>
				</div>

				<div class="comments"></div>
				<div class="loading" style="display: none;">
					<img src="/images/community.loading.svg">
				</div>
				<!-- <div class="bottombar">
               <div class="wrap">
                  <a class="vote inactive"><span class="icon"></span><span
                     class="label">공감하기</span></a><a class="scrap inactive"><span
                     class="icon"></span><span class="label">스크랩</span></a>
               </div>
            </div> -->
			</div>
		</div>

	</div>
	<footer>
		<jsp:include page="../Common/Footer.jsp"></jsp:include>
	</footer>

	<script type="text/javascript">
		var _isLogged = false;
		var _appName = '';
		var _osName = '';
		var _pageView = '';
		var _serverTime = 1492416280094;
		var _clientTime = new Date().getTime();
		var _diffTime = _clientTime - _serverTime;
	</script>
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'https://www.google-analytics.com/analytics.js', 'ga');
		ga('create', 'UA-90020075-2', 'auto');
		ga('send', 'pageview');
	</script>



</body>
</html>