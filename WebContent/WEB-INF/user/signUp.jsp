<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common.jsp" %>
<title>회원 가입</title>
<style>
	.container-fluid { width:1280px; }
	.agree_fr { width:900px; white-space:pre-wrap; margin: 10px auto;
	padding: 24px; border:2px solid #eee; height:600px; overflow-y:auto; }
	.title { padding-top:36px; padding-bottom:20px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<section class="container-fluid">
		<h2 class="title">회원가입</h2>
		<form name="frm1" id="frm1" action="${path1 }/UserJoinPro.do" method="post" onsubmit="return joinCheck(this)">
		</form>
	</section>
</div>
</body>
</html>













