<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
    <div class="wrap">
      <a href="<%=request.getContextPath()%>/index.jsp" class="logo">캠거루 - 대학생 SNS</a>
      <nav>
        <a href="<%=request.getContextPath()%>/Community/comlist.kam">커뮤니티</a>
        <a href="<%=request.getContextPath()%>/GroupBoard/GroupBoardList.kam">동아리</a>
        <a href="<%=request.getContextPath()%>/ActivityBoard/activityboardlist.kam">대외활동</a>
        <a href="<%=request.getContextPath()%>/Common/ContestListAction.kam">공모전</a> 
      </nav>
      <div class="account">
		<%
			if(session.getAttribute("memcode")!=null){
				if(session.getAttribute("memcode").equals("1")){
					out.print("<a href='"+ request.getContextPath() + "/Common/AdminListAction.kam' class='white'>관리자화면</a>");
					out.print("<a href='"+ request.getContextPath() + "/Login/Logout.jsp' class='blue'>로그아웃</a>");
				}else{
					out.print("<a href='"+ request.getContextPath() + "/Login/Mypage.jsp' class='white'>마이페이지</a>");
					out.print("<a href='"+ request.getContextPath() + "/Login/Logout.jsp' class='blue'>로그아웃</a>");
				}
			}else{
				out.print("<a href='"+ request.getContextPath() + "/Login/Login.jsp' class='white'>로그인</a>");
				out.print("<a href='"+ request.getContextPath() + "/Login/Join.jsp' class='blue'>회원가입</a>");
			}
			String memberid=(String)session.getAttribute("memcode");		 
		%>
      </div>
    </div>
  </header>