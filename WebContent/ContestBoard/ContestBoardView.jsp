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
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/ContestBoardView.css">
<!-- <link rel="stylesheet" type="text/css" href="../css/groupBoardView.css">
<link rel="stylesheet" type="text/css" href="../css/club.view.css"> -->
	<% 

		String boardid = (String)request.getAttribute("boardid");
		ContestDTO list = (ContestDTO) request.getAttribute("list");
		ArrayList<ContestDTO> comment = (ArrayList)request.getAttribute("comment");
		System.out.println("boardID: "+ boardid);
		String memberid=(String)session.getAttribute("memcode");
		//ArrayList<ContestDTO> replyResult =(ArrayList)request.getAttribute("replyResult");
		String realPath = list.getImage();
		// 이거 오류나면 아래 지우기
		int totalboardCount = (Integer)request.getAttribute("totalboardCount");
	%>
	
	<script type="text/javascript">
	//댓글작성
	function fnReplyWrite(){
		var Boardid='<%=boardid%>';
		//var BoardId = '${list.getBoardId()}';
		
		var loginData = <%=memberid%>; //session 값
		
		var contents = $("#contents").val();
		
		if(contents == null){
			alert("내용을 입력해주세요.");
			return;
		}
		
		if(loginData== null){
			if(confirm("로그인 필요") == true) {
				location.href="../Login/Login.jsp";
			}
			return;
		}
		var param_data = {};
		param_data.BoardId = Boardid;
		param_data.MemCode = loginData; // loginData.MemCode;
		param_data.Contents = encodeURIComponent(contents);
		
		$.ajax({
			url:"ContestReplyAction.kam",
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
	
	function fnReplyDelete(contestCN , memCode){
		
		if(!confirm("댓글을 삭제 하시겠습니까?")){
			return;
		}
		
		var BoardId = '<%=boardid%>';
		var loginData = '<%=memberid%>';
		
		 if(loginData == null){
			alert("로그인 해주세요.");
			return;
		}else{
			if(loginData!= memCode){
				alert("본인의 글만 삭제 할 수 있습니다.");
				return;
			}
		} 
		
		var param_data = {};
		param_data.BoardID = BoardId;
		param_data.contestCN = contestCN;
		
		$.ajax({
			url:"ContestBoardReplyDelete.kam",
			data:param_data,
			success:function(data){
				
				alert("ok");
				
				//새로고침
				location.reload();
			},
			error:function(e){
				alert(e.responseText);
			}
		});
		
	}

	function test(){
		
		console.log(event.key);
	}
	</script>
</head>
<body>
<c:set var="list" value="<%=list%>" />
<c:set var="comment" value="<%=comment%>" />
<%-- 
<c:set var="BoardId" value="<%=BoardId%>" /> --%>
<%-- <c:set var="BoardId" value="<%=BoardId%>" /> --%>
  <jsp:include page="../Common/Header2.jsp"></jsp:include>
  <section class="community">
    <div class="wrap">
      <figure style="background-image: url('https://cf-ccp.campuspick.com/cp_contest.jpg');"></figure>

      <a data-community-id="2570" class="button unpick">UNPICK</a>

		<h1>
			<a href="">공모전</a>
		</h1>

      <p class="description">이 커뮤니티를 PICK하고 인기있는 공모전 소식을 확인해보세요!</p>

      <p class="details"><span class="pick">${totalboardCount }</span><span class="category">정보</span></p>
    </div>
  </section>

  <section class="menu">
    <div class="wrap">

      <a href="<%=request.getContextPath() %>/Common/ContestListAction.kam"class="active">전체 공모전</a>
      <a href="ContestBoardTeam.kam?boardid=${list.getBoardId()}">팀원 모집</a>
      <a href="<%=request.getContextPath() %>/Common/Write.jsp">게시 요청</a>

    </div>
  </section>


  <div id="container" data-activity-id="320">
    <div class="section title">
      <h1>${list.getTitle()}</h1>
   <!--    <span class="viewcount" data-view-count="104">104</span> -->
      <hr>
    </div>
    		<div class="section date">
			<h2>모집 기간</h2>
			<p>
				<span class="date">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=list.getStartDate()%> ~ <%=list.getEndDate()%></span>
			</p>
			<hr>
		</div>
    <div class="section category">
      <h2>분야</h2>
      <ol><li>
     			<c:if test="${list.getFieldCode()=='F01'}"><span>국내봉사</span></c:if>
				<c:if test="${list.getFieldCode()=='F02'}"><span>해외봉사/탐방</span></c:if>
				<c:if test="${list.getFieldCode()=='F03'}"><span>서포터즈</span></c:if>
				<c:if test="${list.getFieldCode()=='F04'}"><span>기자단</span></c:if>
				<c:if test="${list.getFieldCode()=='F05'}"><span>마케터</span></c:if>
				<c:if test="${list.getFieldCode()=='F06'}"><span>기획/아이디어</span></c:if>
				<c:if test="${list.getFieldCode()=='F07'}"><span>브랜드/네이밍</span></c:if>
				<c:if test="${list.getFieldCode()=='F08'}"><span>광고/마케팅</span></c:if>
				<c:if test="${list.getFieldCode()=='F09'}"><span>사진/영상/UCC</span></c:if>
				<c:if test="${list.getFieldCode()=='F10'}"><span>디자인</span></c:if>
				<c:if test="${list.getFieldCode()=='F11'}"><span>예체능</span></c:if>
				<c:if test="${list.getFieldCode()=='F14'}"><span>영화</span></c:if>
				<c:if test="${list.getFieldCode()=='F15'}"><span>공연</span></c:if>
				<c:if test="${list.getFieldCode()=='F16'}"><span>전시</span></c:if>
				<c:if test="${list.getFieldCode()=='F17'}"><span>기타</span></c:if>
				<c:if test="${list.getFieldCode()=='F12'}"><span>문학/수기</span></c:if>
				<c:if test="${list.getFieldCode()=='F13'}"><span>IT/게임</span></c:if>
		</li></ol>
      <hr>
    </div>

    <div class="section company">
      <h2>기관</h2>
      <dl>
        <!-- <dt>주최</dt> -->
        <dd>${list.getPlace()}</dd>
        <hr>
      </dl>
      <hr>
    </div>
    <div class="section prize">
      <h2>시상</h2>
      <dl>
        <!-- <dt>시상금</dt> -->
        <dd>${list.getPrizeMoney()}</dd>
        <hr>
      </dl>
      <hr>
    </div>

    <div class="section website">
      <h2>웹사이트</h2>
      <p><a href="${list.getWebsite()}" class="link">바로가기</a></p>
      <hr>
    </div>
		<div class="section poster">
			<hr>
 			<%
	 			System.out.println(realPath);
	 			int realPathidx = realPath.lastIndexOf('/');
				String fileName = realPath.substring(realPathidx+1, realPath.length());
				if(!(list.getImage() == null)){ %>
  				<img src="../upload/<%=fileName %>" class="poster"> 
			<%} %>
		</div>
    <div class="section description">
      <h2>상세 내용</h2>
      <hr>
      <a class="item">
						<time>${enrolldate}</time>
						<h3>${contents}</h3>
					</a>
      <article>${list.getContents()}</article>
    </div>

    
    <!-- *************************리플********************* -->
    <div class="articles team" data-target="1" data-board="1">
      <h2>팀원 모집</h2>
      
 
      <ol class="group">
	     <c:forEach var="comment" items="<%=comment%>">
		      		<c:set var="enrolldate" value="${comment.getReplyEnrollDate()}" />
					<c:set var="contents" value="${comment.getReplyContents()}" />
					<c:set var="contestCN" value="${comment.getContestCn()}" />
					<c:set var="memCode" value="${comment.getMemcode()}" />

				<li class="comment">
						<p class="text">${contents}</p>
						<time>${enrollDate}</time>
						<button id="remove" onclick="fnReplyDelete(${contestCN},${memCode});">삭제</button>
						<hr>
					</li>
	      </c:forEach>
       <!--  <li class="empty">아직 작성된 글이 없습니다.</li> -->
       
      </ol>
      </div>
      <form class="writecomment">
				<div class="wrap">
					<input id="contents" type="text" placeholder="댓글을 입력해주세요."
						autocomplete="off">
						 <span class="submit" onclick="fnReplyWrite();"></span> 
						<!-- <input type="button" value="등록" onclick="fnReplyWrite();">댓글쓰기</button> -->
				</div>
	  </form>
    
    </div>
  </div>


  <footer>
    <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

</body>
</html>