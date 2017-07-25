<%@page import="kr.kamgaru.DAO.DAO"%>
<%@page import="kr.kamgaru.service.Group.GroupBoardViewAction"%>
<%@page import="kr.kamgaru.DTO.GroupDTO"%>
<%@page import="kr.kamgaru.DTO.GroupReplyDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/groupBoardView.css">
<link rel="stylesheet" type="text/css" href="../css/club.view.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 

   String BoardId = (String) request.getAttribute("boardid");
   GroupDTO dto = (GroupDTO) request.getAttribute("groupdto");
   ArrayList<GroupReplyDTO> replyResult =(ArrayList)request.getAttribute("replyResult");
   String memberid=(String)session.getAttribute("memcode");
   
    
%>
<script type="text/javascript">

$(document).ready(function(){
   
   var BoardId = '<%=BoardId%>';
   var loginData = <%=memberid%>; //session 값
   var type = "";
    /* if(loginData == null){
      alert("로그인 해주세요.");
      return;
   }  */
   
   var param_data = {};
   param_data.BoardID = BoardId;
   param_data.MemCode = loginData; // loginData.MemCode; 여기 6은?
   
   $.ajax({
      url:"GroupBoardLikeCheck.kam",
      data:param_data,
      dataType: 'json',
      success:function(data){
         
         if(data.likecount > 0){
            $("#like_bt").addClass("voted");
         }else{
            $("#like_bt").removeClass("voted");
         }
         
      },
      error:function(e){
         alert(e.responseText);
      }
   });
   
}); 
//댓글작성
function fnReplyWrite(){
   
   var BoardId = '<%=BoardId%>';
   var loginData = <%=memberid%>; //session 값
   
   var comment_input = $("#comment_input").val();
   
   if(comment_input == ""){
      alert("내용을 입력해주세요.");
      return;
   }
   
    if(loginData == null){
      alert("로그인 해주세요.");
      return;
   } 
   
   var param_data = {};
   param_data.BoardID = BoardId;
   param_data.MemCode = loginData; // loginData.MemCode;
   param_data.Contents = encodeURIComponent(comment_input);
   
   $.ajax({
      url:"GroupBoardReplyok.kam",
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

//찜클릭시..이벤트
function fnLikeClick(){
   
   var BoardId = '<%=BoardId%>';
   var loginData ='<%=memberid%>';
   var type = "";

   
   if($("#like_bt").hasClass("voted")){
      type = "delete";
   }else{
      type = "insert";
   }
   
   var param_data = {};
   param_data.BoardID = BoardId;
   param_data.MemCode = loginData;// 
   param_data.type = type;
   
   $.ajax({
      url:"GroupBoardLike.kam",
      data:param_data,
      dataType: 'json',
      success:function(data){
         
         alert("ok");
         
         if(data.result > 0){
            //성공
            
            if(type == "insert"){
               $("#like_bt").addClass("voted");
            }else{
               //delete
               $("#like_bt").removeClass("voted");
            }
            
         }else{
            alert("실패");
         }
         
      
      },
      error:function(e){
         alert(e.responseText);
      }
   });
   
   
    //hart가 눌려졌으면 .voted 추가
   //jquery 함수로 쓰는거여서 
    if($("#like_bt").hasClass("voted")){
      //voted 클래스를 가지고 있다면
      $("#like_bt").removeClass("voted");
   }else{
      //voted 클래스를 가지고 있지 않다면
      $("#like_bt").addClass("voted");
   } 
   
} 

//댓글 
function fnReplyDelete(groupCn , memCode){
   
   if(!confirm("댓글을  하시겠습니까?")){
      return;
   }
   
   var BoardId = '<%=BoardId%>';
   var loginData = '<%=memberid%>';
   
    if(loginData == null){
      alert("로그인 해주세요.");
      return;
   }else{
      if(loginData!= memCode){
         alert("본인의 글만  할 수 있습니다.");
         return;
      }
   } 
   
   var param_data = {};
   param_data.BoardID = BoardId;
   param_data.GroupCn = groupCn;
   
   $.ajax({
      url:"GroupBoardReplyDelete.kam",
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

   <jsp:include page="../Common/Header2.jsp"></jsp:include>

   <section class="community">
   <div class="wrap">
      <figure
         style="background-image: url('https://cf-ccp.campuspick.com/cp_circle.jpg');"></figure>

      <h1>
         <a href="GroupBoardList.kam">동아리</a>
      </h1>

      <p class="description">이 커뮤니티를 PICK하고 모집 공고를 확인하여 관심있는 동아리에
         지원해보세요!</p>

      <p class="details">
         <span class="pick">494318</span><span class="category">정보</span>
      </p>
   </div>
   </section>
   
    <!-- group board header -->
    <c:import url="GroupBoardHeader.jsp" charEncoding="urf-8"/>
    
   <div id="container" data-recruit-id="287">
      <div class="section title">
         <div class="image" style="background-image: url('https://cf-cpi.campuspick.com/club/1487843339450794.jpg');"></div>
         <h1><%=dto.getTitle()%></h1>
         <!-- <div> -->
            
            <a class="votebutton" id="like_bt" onclick="fnLikeClick();"></a>
            <p class="votebubble">마음에 드는 동아리에<br>좋아요를 눌러주세요.</p>
         <!-- </div> -->
         <%-- <span class="viewcount" data-view-count="302">${Hit}</span> --%>
         <hr>
      </div>
      <div class="zzzzzz">
         <%-- <h2>${boardId}</h2> -->
         <%-- <span class="dday active"></span><span class="date">${BoardId}</span> --%>
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
            <c:if test="${groupdto.getFieldCode()=='F01'}"><span>국내봉사</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F02'}"><span>해외봉사/탐방</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F03'}"><span>서포터즈</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F04'}"><span>기자단</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F05'}"><span>마케터</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F06'}"><span>기획/아이디어</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F07'}"><span>브랜드/네이밍</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F08'}"><span>광고/마케팅</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F09'}"><span>사진/영상/UCC</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F10'}"><span>디자인</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F11'}"><span>예체능</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F14'}"><span>영화</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F15'}"><span>공연</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F16'}"><span>전시</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F17'}"><span>기타</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F12'}"><span>문학/수기</span></c:if>
            <c:if test="${groupdto.getFieldCode()=='F13'}"><span>IT/게임</span></c:if>
            
            
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
   <!--    <div class="section size">
         <h2>규모</h2>
         <p data-size="2">
            <span>10~30명</span>
         </p>
         <hr>
      </div> -->
   <%--    <div class="section">
         <h2>동아리 소개</h2>
         <hr>
         <article>${Contents}</article>
      </div> --%>

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
                  <c:set var="groupCn" value="${reply.getGroupCN()}" />
                  <c:set var="memCode" value="${reply.getMemCode()}" />
                  
               <li class="comment"><p class="text">${contents}</p>
                  <time>${enrollDate}</time>
                  <a class="remove" onclick="fnReplyDelete(${groupCn},${memCode});">삭제</a>
                  <hr>
               </li>
            </c:forEach>
         </ol>
      </div>
      <!--  애가 범인 생각해보니까 폼이 아바보였어 input창 포커스 맞춰져 있는상태에서 엔터눌러야지 실행하는건데 -->
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