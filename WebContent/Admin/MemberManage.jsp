<%@page import="kr.kamgaru.DTO.MemberDTO"%>
<%@page import="kr.kamgaru.DTO.AdminDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/Paging.css">
	<link rel="stylesheet" type="text/css" href="../css/assets/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../css/assets/dataTables.bootstrap.css">

</head>
	<script type = "text/javascript">
	function delConfrim() {
		if(confirm("삭제된 데이터는 복구되지 않습니다. 그래도 지우시겠습니까?") == true){
			alert("삭제되었습니다");
		}else{
			return;
		}
	}
	</script>
<body>

		<%  
        int cpage = (Integer)request.getAttribute("cpage");
        int pagesize = (Integer)request.getAttribute("pagesize");
        int pagecount = (Integer)request.getAttribute("pagecount");
        int totalboardCount = (Integer)request.getAttribute("totalboardCount");
        ArrayList<MemberDTO> list = (ArrayList)request.getAttribute("list");
		%>
		<c:set var="pagesize" value="<%=pagesize%>" />
		<c:set var="cpage" value="<%=cpage%>" />
		<c:set var="pagecount" value="<%=pagecount%>" />
		<c:set var="list" value="<%=list%>"/>
		<c:set var="totalboardCount" value="<%=totalboardCount%>"/>

		<jsp:include page="../Common/Header2.jsp"></jsp:include>
	
		<section class="community">
			<div class="wrap">
				<figure tyle="background-image: url('https://cf-ccp.campuspick.com/cp_circle.jpg');"></figure>
				<h1><a href="/community?id=2572">Admin Page</a></h1>
				<p class="description">관리자 페이지입니다.</p>
			</div>
		</section>
		
		<section class="menu">
			<div class="wrap">
				<a href="AdminListAction.kam" >게시요청 관리</a>
				<a href="AdminMemberList.kam" class="active">회원 관리</a>
				<a href="WriteRequestList.jsp">방문자 통계</a>
			</div>
		</section>

		<form name="list" style="margin-left:180px">
			Page설정
				<select name="ps" onchange="submit()">		
					<c:forEach var="i" begin="5" end="20" step="5">
					   	<c:choose>
							<c:when test="${pagesize == i}">
				                <option value='${i}' selected>${i}건</option>
				            </c:when>
							<c:otherwise>
				                <option value='${i}'>${i}건</option>
				            </c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
		</form>


	<div class="container" align="center">
			<table id="table" class="table table-striped table-bordered">
				<tr align = "center">
					<th align="center">이름</th>
					<th align="center">닉네임</th>
					<th align="center">이메일</th>
					<th align="center">학교</th>
					<th align="center">탈퇴</th>
				</tr>
				
				<!-- 데이터가 한건도 없는 경우  -->
				<%
		     	if(list == null || list.size() == 0){
		     		out.print("<tr><td>데이터가 없습니다</td></tr></table>");
		     		return;
		     	} 
		       %>
		       
				<c:forEach var="member" items="<%=list%>">
					<c:set var="name" value="${member.getName()}" />
					<c:set var="email" value="${member.getEmail()}" />			
					<c:set var="nickname" value="${member.getNickname()}" />
					<c:set var="university" value="${member.getUniversity()}" />
					
					<tr>
						<td align="center">${name}</td>
						<td align="center">${nickname}</td>
						<td align="center">${email}</td>
						<td align="center">${university}</td>
						<td align="center"><button type = button id ="delbtn" onclick="delConfrim();"><a href="AdminManageDelete.kam?nickname=${nickname}">탈퇴</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="page_nb" id="list_page">
		<ul>
			<li>
				<span>
					<c:if test="${cpage>1}">
						<a href="AdminMemberList.kam?cp=${cpage-1}&ps=${pagesize}"><</a>
					</c:if> 
					<c:forEach var="i" begin="1" end="${pagecount}" step="1">
							<c:choose>
								<c:when test="${cpage==i}">
									<font color='#1DCDFF'>[${i}]</font>
								</c:when>
								<c:otherwise>
									<a href="AdminMemberList.kam?cp=${i}&ps=${pagesize}">[${i}]</a>
								</c:otherwise>
							</c:choose>
					</c:forEach> 
						<c:if test="${cpage<pagecount}">
							<a href="AdminListAction.kam?cp=${cpage+1}&ps=${pagesize}">></a>
						</c:if>
				</span>
			</li>
		</ul>
	</div>	



	<footer><jsp:include page="../Common/Footer.jsp"></jsp:include></footer>

</body>
</html>