<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%
	String sid = "";
	if(session!=null) sid = (String) session.getAttribute("sid");
%>
<header id="hd">
	<div class="hd_wrap">
		<nav id="tnb">
			<c:if test="${empty sid }">
				<li><a href="">로그인</a></li>
				<li><a href="">회원가입</a></li>
			</c:if>
			<c:if test="${!empty sid }">
				<li><a href="">마이페이지</a></li>
				<li><a href="">장바구니</a></li>
				<li><a href="">로그아웃</a></li>
			</c:if>
			<c:if test="${sid=='young' }">
				<li><a href="">관리자 페이지로</a></li>
			</c:if>		
		</nav>
	</div>
</header>