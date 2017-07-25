<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.kamgaru.DTO.Activityreply"%>
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
<link rel="stylesheet" type="text/css" href="../css/groupBoardView.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<% 

		String BoardId = (String)request.getAttribute("boardid");
		ActivityDTO dto = (ActivityDTO) request.getAttribute("dto");
		ArrayList<Activityreply> replyResult =(ArrayList)request.getAttribute("replyResult");
		ArrayList<ActivityDTO> list = (ArrayList) request.getAttribute("list");
		ActivityDTO list2 = (ActivityDTO) request.getAttribute("list");
		String memberid=(String)session.getAttribute("memcode");
	
	%>
<script type="text/javascript">
function fnLikeClick(){
	//hart가 눌려졌으면 .voted 추가
	//jquery 함수로 쓰는거여서 
	if($("#like_bt").hasClass("voted")){
		//voted 클래스를 가지고 있다면
		//
		$("#like_bt").removeClass("voted");
	}else{
		//voted 클래스를 가지고 있지 않다면
		$("#like_bt").addClass("voted");
		console.log("add voted");
	}
	
}
//댓글작성
function fnReplyWrite(){
	
	var BoardId = '<%=BoardId%>';
	var loginData = <%=memberid%>; //session 값
	
	var comment_input = $("#comment_input").val();
	
	if(comment_input == ""){
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
	param_data.BoardID = BoardId;
	param_data.MemCode = loginData; // loginData.MemCode;
	param_data.Contents = encodeURIComponent(comment_input);
	
	$.ajax({
		url:"activityBoardReplyok.kam",
		data:param_data,
		success:function(data){
			//alert("ok");
			
			location.reload();
		},
		error:function(e){
			alert(e.responseText);
		}
	});
	
}
//댓글 삭제
function fnReplyDelete(activityCn , memCode){
	
	if(!confirm("댓글을 삭제 하시겠습니까?")){
		return;
	}
	
	var BoardId = '<%=BoardId%>';
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
	param_data.BoardID = BoardId;
	param_data.ActivityCn = activityCn;
	
	$.ajax({
		url:"activityBoardReplyDelete.kam",
		data:param_data,
		success:function(data){
			
			alert("ok");
			
			location.reload();
		},
		error:function(e){
			alert(e.responseText);
		}
	});
	
}	
	</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css"
	href="../css/ActivityBoardView.css">
</head>
<body>
	
	<jsp:include page="../Common/Header2.jsp"></jsp:include>

	<section class="community">
	<div class="wrap">
		<figure
			style="background-image: url('https://cf-ccp.campuspick.com/cp_circle.jpg');"></figure>

		<h1>
			<a href="../ActivityBoard/activityboardlist.kam"">대외활동</a>
		</h1>

		<p class="description">집 보내줘</p>

		<p class="details">
			<span class="pick">494318</span><span class="category">정보</span>
		</p>
	</div>
	</section>
	<section class="menu">
	<div class="wrap">
		<a href="../ActivityBoard/activityboardlist.kam" class="active">전체 대외활동</a> <a
			href="../ActivityBoard/activityBoardTeam.kam?boardid=${list2.getBoardId()}">팀원모집</a>
 	<a href="<%=request.getContextPath() %>/Common/Write.jsp"">게시 요청</a>
	</div>
	</section>
	<div id="container" data-recruit-id="287">
		<div class="section title">
			<h1><%=dto.getTitle()%></h1>
			<%-- <span class="viewcount" data-view-count="302">${Hit}</span> --%>
			<hr>
		</div>
		<div class="zzzzzz">
			<%-- <h2>${boardId}</h2> -->
			<%-- <span class="dday active"></span><span class="date">${BoardId}</span> --%>
			<hr>
		</div>
		 <div class="section website">
      <h2>웹사이트</h2>
      <p><a href="${list.getWebsite()}" class="link">바로가기</a></p>
      <hr>
    </div>
		<div class="section date">
			<h2>모집 기간</h2>
			 <p data-start-date="2017-04-15" data-end-date="2017-04-21">
				<span class="dday active"></span>
				<span class="date"><%=dto.getStartDate()%> ~ <%=dto.getEndDate()%></span> 
			</p>
			<hr>
		</div>
		<div class="section category">
			<h2>분야</h2>
			 <p data-category-id="8"> 
				<c:if test="${dto.getFieldCode()=='F01'}"><span>국내봉사</span></c:if>
				<c:if test="${dto.getFieldCode()=='F02'}"><span>해외봉사/탐방</span></c:if>
				<c:if test="${dto.getFieldCode()=='F03'}"><span>서포터즈</span></c:if>
				<c:if test="${dto.getFieldCode()=='F04'}"><span>기자단</span></c:if>
				<c:if test="${dto.getFieldCode()=='F05'}"><span>마케터</span></c:if>
				<c:if test="${dto.getFieldCode()=='F06'}"><span>기획/아이디어</span></c:if>
				<c:if test="${dto.getFieldCode()=='F07'}"><span>브랜드/네이밍</span></c:if>
				<c:if test="${dto.getFieldCode()=='F08'}"><span>광고/마케팅</span></c:if>
				<c:if test="${dto.getFieldCode()=='F09'}"><span>사진/영상/UCC</span></c:if>
				<c:if test="${dto.getFieldCode()=='F10'}"><span>디자인</span></c:if>
				<c:if test="${dto.getFieldCode()=='F11'}"><span>예체능</span></c:if>
				<c:if test="${dto.getFieldCode()=='F14'}"><span>영화</span></c:if>
				<c:if test="${dto.getFieldCode()=='F15'}"><span>공연</span></c:if>
				<c:if test="${dto.getFieldCode()=='F16'}"><span>전시</span></c:if>
				<c:if test="${dto.getFieldCode()=='F17'}"><span>기타</span></c:if>
				<c:if test="${dto.getFieldCode()=='F12'}"><span>문학/수기</span></c:if>
				<c:if test="${dto.getFieldCode()=='F13'}"><span>IT/게임</span></c:if>
							
			</p>
			<hr>
		</div>
		<div class="section local">
			<h2>활동 지역</h2>
			<p data-local-id="1">
			 <span><%=dto.getPlace()%></span>
			</p>
			<hr>
		</div>
	
		<div class="section">
			<h2>동아리 소개</h2>
			<hr>
			<%-- <article>${Contents}</article> --%>
		</div>

		<div class="section description">
			<h2>상세 내용</h2>
			<hr>
			<article><%=dto.getContents()%></article>
		</div>
		
		<div class="comments">
			<h2>댓글목록</h2>
			<ol class="group">
				<!--  for문 -->
				<c:forEach var="reply" items="<%=replyResult%>">
         			<c:set var="contents" value="${reply.getContents()}" />
         			<c:set var="enrollDate" value="${reply.getEnrollDate()}" />
         			<c:set var="activityCn" value="${reply.getActivityCN()}" />
         			<c:set var="memCode" value="${reply.getMemCode()}" />
         			
					<li class="comment"><p class="text">${contents}</p>
						<time>${enrollDate}</time>
						<a class="remove" onclick="fnReplyDelete(${activityCn},${memCode});">삭제</a>
						<hr>
					</li>
				</c:forEach>
			</ol>
		</div>
		<form class="writecomment"  onsubmit="return false;">
			<input type="hidden" name="test"/>
			<div class="wrap">
				<input id="comment_input" autocomplete="off" type="text"  placeholder="댓글을 입력해주세요." onkeydown="javascript: if(event.keyCode == 13) fnReplyWrite();">
				 <span class="submit" onclick="fnReplyWrite();" ></span>
			</div>
		</form>

	</div>
	
	<footer> <jsp:include page="../Common/Footer.jsp"></jsp:include>
	</footer>
</body>
</html>
