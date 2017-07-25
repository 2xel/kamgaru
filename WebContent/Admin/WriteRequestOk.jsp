<%@page import="kr.kamgaru.DTO.AdminDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/Write.css">
<script src="../js/write_request.js"></script>

</head>


<body>
	<%
		/*   int cpage = (Integer)request.getAttribute("cpage");
		  int pagesize = (Integer)request.getAttribute("pagesize");
		  int pagecount = (Integer)request.getAttribute("pagecount");
		  int totalboardCount = (Integer)request.getAttribute("totalboardCount"); */
			AdminDTO list = (AdminDTO) request.getAttribute("list");
		  System.out.println("jspBoardID"+list.getBoardId());
		  
	%>
	<jsp:include page="../Common/Header2.jsp"></jsp:include>
	<c:set var="list" value="<%=list%>" />
	<div class="title">
		<div class="wrap">
			<h1>
				동아리/대외활동/공모전/문화정보 <strong>무료</strong> 게시 요청
			</h1>
			<p>
				캠퍼스픽은 국내 <strong>최다 이용자 수</strong>를 보유한 대학생 SNS입니다.<br>대학생에게
				유익한 맞춤 정보를 캠퍼스픽에서 효과적으로 홍보하세요.
			</p>
			<p>
				동아리(대외활동/공모전/문화정보) 게시는 <strong>무료로 진행</strong>되며,<br>담당자의 검토 및
				승인 절차를 걸쳐 48시간 이내 게시됩니다.
			</p>
			<p class="notice">* 별표 표시는 필수 항목입니다.</p>
		</div>
	</div>
	<form id="container" action="writeRequestConfirm.kam?categoryCode=${list.getCategoryCode()}&boardId=${list.getBoardId()}" method="POST">

		<h2>담당자 정보</h2>
		<p>
			<input type="text" name="manager_name"
				maxlength="10" autocomplete="off" class="text" value="${list.getManager_name()}" readonly>
		</p>
		<p>
			<input type="text" name="manager_phone"
				maxlength="100" class="text" value="${list.getManager_phone()}" readonly>
		</p>
		<h2>활동 개요</h2>
		<p>
			<input type="text" name="title" value="${list.getTitle()}" maxlength="100"
				autocomplete="off" class="text">
		</p>
		<p>
			<input type="text" name="website" value="${list.getWebsite()}"
				autocomplete="off" class="text">
		</p>

		<div>
			<h3>종류 *</h3>
			<select id="category" name="category" disabled="disabled">
				<option value="">카테고리선택</option>
				<option value="C01"<c:if test="${list.getCategoryCode()=='C01' }"> 
                           selected 
                           </c:if>>동아리</option>
				<option value="C02"<c:if test="${list.getCategoryCode()=='C02' }"> 
                           selected 
                           </c:if>>대외활동</option>
				<option value="C03" <c:if test="${list.getCategoryCode()=='C03' }"> 
                           selected 
                           </c:if>>공모전</option>
				<option value="C04"<c:if test="${list.getCategoryCode()=='C04' }"> 
                           selected 
                           </c:if>>문화정보</option>
			</select>
		</div>

		<div class="field" data-targetfor="1">
			<h3>분야 *</h3>
			<label><input type="radio" name="fieldCode" value="F01"<c:if test="${list.getFieldCode()=='F01' }"> 
                           checked 
                           </c:if>>국내봉사</label>
			<label><input type="radio" name="fieldCode" value="F02" <c:if test="${list.getFieldCode()=='F02' }"> 
                           checked 
                           </c:if>>해외봉사/탐방</label>
			<label><input type="radio" name="fieldCode" value="F03" <c:if test="${list.getFieldCode()=='F03' }"> 
                           checked 
                           </c:if>>서포터즈</label>
			<label><input type="radio" name="fieldCode" value="F04" <c:if test="${list.getFieldCode()=='F04' }"> 
                           checked 
                           </c:if>>기자단</label>
			<label><input type="radio" name="fieldCode" value="F05" <c:if test="${list.getFieldCode()=='F05' }"> 
                           checked 
                           </c:if>>마케터</label>
			<label><input type="radio" name="fieldCode" value="F06" <c:if test="${list.getFieldCode()=='F06' }"> 
                           checked 
                           </c:if>>기획/아이디어</label>
			<label><input type="radio" name="fieldCode" value="F07" <c:if test="${list.getFieldCode()=='F07' }"> 
                           checked 
                           </c:if>>브랜드/네이밍</label>
			<label><input type="radio" name="fieldCode" value="F08" <c:if test="${list.getFieldCode()=='F08' }"> 
                           checked 
                           </c:if>>광고/마케팅</label>
			<label><input type="radio" name="fieldCode" value="F09" <c:if test="${list.getFieldCode()=='F09' }"> 
                           checked 
                           </c:if>>사진/영상/UCC</label>
			<label><input type="radio" name="fieldCode" value="F10"<c:if test="${list.getFieldCode()=='F10' }"> 
                           checked 
                           </c:if>>디자인</label>
			<label><input type="radio" name="fieldCode" value="F11"<c:if test="${list.getFieldCode()=='F11' }"> 
                           checked 
                           </c:if>>예체능</label>
			<label><input type="radio" name="fieldCode" value="F12" <c:if test="${list.getFieldCode()=='F12' }"> 
                           checked 
                           </c:if>>문학/수기/시나리오</label>
			<label><input type="radio" name="fieldCode" value="F13" <c:if test="${list.getFieldCode()=='F13' }"> 
                           checked 
                           </c:if>>IT/소프트웨어/게임</label>
			<label><input type="radio" name="fieldCode" value="F14" <c:if test="${list.getFieldCode()=='F14' }"> 
                           checked 
                           </c:if>>영화</label>
			<label><input type="radio" name="fieldCode" value="F15" <c:if test="${list.getFieldCode()=='F15' }"> 
                           checked 
                           </c:if>>공연</label>
			<label><input type="radio" name="fieldCode" value="F16" <c:if test="${list.getFieldCode()=='F16' }"> 
                           checked 
                           </c:if>>전시</label>
			<label><input type="radio" name="fieldCode" value="F17" <c:if test="${list.getFieldCode()=='F17' }"> 
                           checked 
                           </c:if>>기타</label>
		</div>

		<div>
			<h3>접수기간 *</h3>
			<input type="date" name="start_date" autocomplete="off" class="date" value="${list.getStartDate()}" readonly>
			<span>부터</span> <input type="date" name="end_date" autocomplete="off" value="${list.getEndDate()}"
				class="date" readonly> <span>까지</span>
			<hr>
		</div>

		<h2>장소(기관명)</h2>
		<p>
			<input type="text" name="place" placeholder="행사장소 / 주최 기관명 *"
				maxlength="100" autocomplete="off" class="text" value="${list.getPlace()}">
		</p>
		<h2>이벤트(혜택) / 시상금액</h2>
		<p>
			<input type="text" name="prizemoney" placeholder="이벤트(혜택) / 시상금액"
				autocomplete="off" class="text" value="${list.getPrizeMoney()}" readonly>
		</p>
		<h2>상세 내용</h2>
		<p>
			<textarea name="contents">${list.getContents()}</textarea>
		</p>

		<h2>이미지</h2>
		<ul>
			<li>본문 : 게시물 내에 삽입되는 원본 이미지, 1200×1710px 권장</li>
		</ul>
		<p class="wrap">
			<input type="text" name="attach_filename" placeholder="본문 *"
				class="text" value="${list.getImage()}">
			<!--  <input type="file" name="attach" class="file"> -->
			<input type="button" name="attach_button" value="파일 선택"
				class="attach">
		</p>

		<input type="submit" value="확인" class="submit" >
	</form>

	<footer>
		<a href="/" class="copyright">캠퍼스픽</a> <a href="/page/faq">도움말</a> <a
			href="/page/partner">제휴/협력</a> <a href="/page/rules">커뮤니티이용규칙</a> <a
			href="/page/privacy">개인정보취급방침</a>
	</footer>

</body>
</html>