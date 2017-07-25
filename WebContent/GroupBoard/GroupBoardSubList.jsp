<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.kamgaru.DTO.GroupDTO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/GroupBoardSubList.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<%  
		 int cpage = (Integer)request.getAttribute("cpage");
		 int pagesize = (Integer)request.getAttribute("pagesize");
		 int pagecount = (Integer)request.getAttribute("pagecount");
		 int pageGroupSize = (Integer)request.getAttribute("pageGroupSize");
		 int numPageGroup = (Integer)request.getAttribute("numPageGroup");
		 int pageGroupCount = (Integer)request.getAttribute("pageGroupCount");
		 int totalboardCount = (Integer)request.getAttribute("totalboardCount"); 
	     ArrayList<GroupDTO> groupRanklist = (ArrayList)request.getAttribute("groupRanklist");
         String select_category = (String)request.getAttribute("select_category");
  	
  	/*     GroupDTO dto = (GroupDTO) request.getAttribute("groupdto"); */
     
%>
<script type="text/javascript"> 

//jsp 들어오면 자동실행
$(document).ready(function(){

	var select_category = '<%=select_category%>';
	
    if(select_category != "null"){
		$("#select_category").val(select_category);
	}
});

//검색
function fnSearch(){
	var select_category = $("#select_category").val(); //샐랙박스도 val() 쓰면 선택한 값을 가져와줘
	
	
	var param_data = {};
	param_data.select_category = select_category;
	
	location.href="GroupBoardRankList.kam?select_category="+select_category;
}

function fnSearchPage(currentPage){
	var select_category = $("#select_category").val(); 
	
	
	location.href="GroupBoardRankList.kam?cp="+currentPage+"&select_category="+select_category;
}
function fnSearchPagebar(cp,ps){
	
	var select_category = $("#select_category").val(); //샐랙박스도 val() 쓰면 선택한 값을 가져와줘
	
	
	location.href="GroupBoardRankList.kam?cp="+cp+"&ps="+ps+"&select_category="+select_category;
}
function fnSearchGroupPagebar(cp){
	
	var select_category = $("#select_category").val(); //샐랙박스도 val() 쓰면 선택한 값을 가져와줘

	
	location.href="GroupBoardRankList.kam?cp="+cp+"&select_category="+select_category;
}



	
</script>
  <jsp:include page="../Common/Header2.jsp"></jsp:include>
  <section class="community">
    <div class="wrap">
      <figure style="background-image: url('https://cf-ccp.campuspick.com/cp_circle.jpg');"></figure>


      <h1><a href="">동아리</a></h1>

      <p class="description">이 커뮤니티를 PICK하고 모집 공고를 확인하여 관심있는 동아리에 지원해보세요!</p>

      <p class="details"><span class="pick">총 ${totalboardCount}건의</span><span class="category">찜~♡ 랭킹</span></p>
    </div>
  </section>

   <!-- group board header -->
   <section class="menu">
	<div class="wrap">
	  <a id="group_list" href="GroupBoardList.kam" >모집 공고</a>
      <a id="group_rank_list" class="active" href="GroupBoardRankList.kam">동아리 랭킹</a> 
      <a id="group_write" href="">모집공고 작성</a>
	</div>
</section>

  <div id="container">
    <div class="filter">
      <select id="select_category">
            <option value="">전체</option>
				<option value="F01">국내봉사</option>
				<option value="F02">해외봉사/탐방</option>
				<option value="F03">서포터즈</option>
				<option value="F04">기자단</option>
				<option value="F05">마케터</option>
				<option value="F06">기획/아이디어</option>
				<option value="F07">브랜드/네이밍</option>
				<option value="F08">광고/마케팅</option>
				<option value="F09">사진/영상/UCC</option>
				<option value="F10">디자인</option>
				<option value="F11">예체능</option>
				<option value="F12">영화</option>
				<option value="F13">공연</option>
				<option value="F14">전시</option>
				<option value="F15">기타</option>
				<option value="F16">문학/수기</option>
				<option value="F17">IT/게임</option>
			</select>
      <!-- <a class="select local">활동지역</a> -->
    <!--   <a class="select sort">인기순</a> -->
      <a class="search"  onclick="fnSearch();"></a>
      <hr>
    </div>
    <form class="search">
      <div>
        <input type="search" name="keyword" autocomplete="off">
        <span class="button"></span>
      </div>
      <span class="cancel">취소</span>
    </form>
  <ol class="group">
    <c:forEach var="board" items="<%=groupRanklist%>">
         <c:set var="boardid" value="${board.getBoardId()}" />
         <c:set var="categorycode" value="${board.getCategoryCode()}" />
         <c:set var="FieldCode" value="${board.getFieldCode()}" />
         <c:set var="title" value="${board.getTitle()}" />
         <c:set var="contents" value="${board.getContents()}" />
         <c:set var="flag" value="${board.getFlag()}" />
         <c:set var="hit" value="${board.getHit()}" />
         <c:set var="dDay" value = "${board.getStartDate()}"/>
         <c:set var="likecount" value = "${board.getLikeCount()}"/>
         <c:set var="place" value = "${board.getPlace()}"/>
       <li>
        <a class="item" href="GroupBoardView.kam?boardid=${boardid}">
           <div class="image" data-image="https://cf-cpi.campuspick.com/club/1487843339450794.jpg" style="background-image: "></div>
           <span class="vote">${likecount}</span>
           <h2>${title}</h2>
           <p class="description">${contents}</p>
         <p class="small">
            <span><c:if test="${FieldCode=='F01'}"><span>국내봉사</span></c:if>
            <c:if test="${FieldCode=='F02'}"><span>해외봉사/탐방</span></c:if>
            <c:if test="${FieldCode=='F03'}"><span>서포터즈</span></c:if>
            <c:if test="${FieldCode=='F04'}"><span>기자단</span></c:if>
            <c:if test="${FieldCode=='F05'}"><span>마케터</span></c:if>
            <c:if test="${FieldCode=='F06'}"><span>기획/아이디어</span></c:if>
            <c:if test="${FieldCode=='F07'}"><span>브랜드/네이밍</span></c:if>
            <c:if test="${FieldCode=='F08'}"><span>광고/마케팅</span></c:if>
            <c:if test="${FieldCode=='F09'}"><span>사진/영상/UCC</span></c:if>
            <c:if test="${FieldCode=='F10'}"><span>디자인</span></c:if>
            <c:if test="${FieldCode=='F11'}"><span>예체능</span></c:if>
            <c:if test="${FieldCode=='F14'}"><span>영화</span></c:if>
            <c:if test="${FieldCode=='F15'}"><span>공연</span></c:if>
            <c:if test="${FieldCode=='F16'}"><span>전시</span></c:if>
            <c:if test="${FieldCode=='F17'}"><span>기타</span></c:if>
            <c:if test="${FieldCode=='F12'}"><span>문학/수기</span></c:if>
            <c:if test="${FieldCode=='F13'}"><span>IT/게임</span></c:if>
            </span>
            <span>${place}</span>
         </p>
         <hr>
      </a>
   </li>
   </c:forEach>
  </ol>
</div>

	<!-- 페이지 뷰   :::: 소스 참고 http://dualist.tistory.com/147   -->
	<div class="page_nb" id="list_page">
		<ul>
			<c:if test="${totalboardCount>0}">
			    <c:set var="startPage" value="${pageGroupSize*(numPageGroup-1)+1}"/>
			    <c:set var="endPage" value="${startPage + pageGroupSize-1}"/>
			
				<c:if test="${endPage > pagecount}" >
			     	<c:set var="endPage" value="${pagecount}" />
			    </c:if>
				
				<c:if test="${numPageGroup > 1 }">
					<li onclick="fnSearchGroupPagebar(${(numPageGroup-2)*pageGroupSize+1})">&lt;&lt;</li>
				</c:if>
				<c:if test="${cpage>1}">
					<li onclick="fnSearchPagebar(${cpage-1},${pagesize})">&lt;</li> 
				</c:if> 
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" varStatus="status">
					<c:choose>
				    	<c:when test="${status.index eq cpage}">
				        	<li><span>${status.index}</span></li>
				    	</c:when>
						<c:otherwise>
							<li onclick="fnSearchPagebar(${status.index},${pagesize});">${status.index}</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${cpage<pagecount}">
					<li onclick="fnSearchPagebar(${cpage+1},${pagesize})">&gt;</li>
				</c:if>
				<c:if test="${numPageGroup < pageGroupCount}">
					<li onclick="fnSearchGroupPagebar(${numPageGroup*pageGroupSize+1})">&gt;&gt;</li>
				</c:if>	 
			</c:if>
		</ul>
	</div>   
   

  <footer>
    <jsp:include page="../Common/Footer.jsp"></jsp:include>
  </footer>

</body>
</html>