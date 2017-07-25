<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String imagePath = (String) request.getAttribute("Image");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/Write.css">
<script src="http://code.jquery.com/jquery-2.1.1.js"></script>
<script src="../js/write_request.js"></script>

</head>
<body>
	<jsp:include page="../Common/Header2.jsp"></jsp:include>

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

	<form id="container" action="writeok.kam" method="POST"
		enctype="multipart/form-data">
		<h2>담당자 정보</h2>
		<p>
			<input type="text" name="manager_name" placeholder="이름 *"
				maxlength="10" autocomplete="off" class="text" id="name">
		</p>
		<p>
			<input type="text" name="manager_phone"
				placeholder="연락처 * (- 없이 숫자만)" maxlength="100" class="text" id="phone">
		</p>
		<h2>활동 개요</h2>
		<p>
			<input type="text" name="title" placeholder="제목 *" maxlength="100"
				autocomplete="off" class="text" id="title">
				
		</p>
		<p>
			<input type="text" name="website" placeholder="웹사이트 (http:// 포함)"
				autocomplete="off" class="text" id="website">
		</p>
		<div>
			<h3>종류 *</h3>
			<select id="category" name="category">
				<option value="">카테고리선택</option>
				<option value="C01">동아리</option>
				<option value="C02">대외활동</option>
				<option value="C03">공모전</option>
				<option value="C04">문화정보</option>
			</select>

		</div>
		<div class="field" data-targetfor="1">
			<h3>분야 *</h3>
			<label><input type="radio" name="fieldCode" value="F01" checked>국내봉사</label>
			<label><input type="radio" name="fieldCode" value="F02">해외봉사/탐방</label>
			<label><input type="radio" name="fieldCode" value="F03">서포터즈</label>
			<label><input type="radio" name="fieldCode" value="F04">기자단</label>
			<label><input type="radio" name="fieldCode" value="F05">마케터</label>
			<label><input type="radio" name="fieldCode" value="F06">기획/아이디어</label>
			<label><input type="radio" name="fieldCode" value="F07">브랜드/네이밍</label>
			<label><input type="radio" name="fieldCode" value="F08">광고/마케팅</label>
			<label><input type="radio" name="fieldCode" value="F09">사진/영상/UCC</label>
			<label><input type="radio" name="fieldCode" value="F10">디자인</label>
			<label><input type="radio" name="fieldCode" value="F11">예체능</label>
			<label><input type="radio" name="fieldCode" value="F12">문학/수기/시나리오</label>
			<label><input type="radio" name="fieldCode" value="F13">IT/소프트웨어/게임</label>
			<label><input type="radio" name="fieldCode" value="F14">영화</label>
			<label><input type="radio" name="fieldCode" value="F15">공연</label>
			<label><input type="radio" name="fieldCode" value="F16">전시</label>
			<label><input type="radio" name="fieldCode" value="F17">기타</label>
		</div>
		<div>
			<h3>접수기간 *</h3>
			<input type="date" name="start_date" autocomplete="off" class="date">
			<span>부터</span> <input type="date" name="end_date" autocomplete="off"
				class="date" id="date"> <span>까지</span>
			<hr>
		</div>
		<h2>장소(기관명)</h2>
		<p>
			<input type="text" name="place" placeholder="행사장소 / 주최 기관명 *"
				maxlength="100" autocomplete="off" class="text" id="place">
		</p>
		<h2>이벤트(혜택) / 시상금액</h2>
		<p>
			<input type="text" name="prizemoney" placeholder="이벤트(혜택) / 시상금액"
				autocomplete="off" class="text" id="prizemoney">
		</p>
		<h2>상세 내용</h2>
		<p>
			<textarea name="contents" id="contents"
				placeholder="※ 개요, 주제, 일정, 참가자격, 시상내역 및 혜택, 활동내용, 유의사항, 접수방법, 문의처 등 상세 내용을 자유롭게 적어주세요."></textarea>
		</p>

		<h2>이미지</h2>
		<ul>
			<li>본문 : 게시물 내에 삽입되는 원본 이미지, 1200×1710px 권장</li>
		</ul>
		<p class="wrap">
<!-- 		<input type="text" name="attach_filename" placeholder="본문 *" class="text" > -->
		 	<input type="text" class="text" id="fileName" placeholder="이미지(모집포스터, 1200x1710px 권장)" readonly>			
			<input type = "button"  name = "attach_button" value = "파일 선택" class = "attach">
			<input type="file" name="attach" class="file" id="fileuploade" onchange="javascript: document.getElementById('fileName').value = this.value">
		 </p>

		<input type="submit" value="무료 게시 요청하기" name="file_submit" id="btnsubmit"
			class="submit">
	</form>

	<footer>
		<a href="/" class="copyright">캠퍼스픽</a> <a href="/page/faq">도움말</a> <a
			href="/page/partner">제휴/협력</a> <a href="/page/rules">커뮤니티이용규칙</a> <a
			href="/page/privacy">개인정보취급방침</a>
	</footer>

</body>
</html>