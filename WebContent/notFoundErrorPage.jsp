<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
 <%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>해당 페이지를 찾을 수 없습니다.</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style>
	.title { text-align:center; }
	#tb1, .msg {width:600px; margin: 0 auto;}
	.btn { margin-top:20px; }
</style>
</head>
<body>
	<div class="msg">
		<h1 class="title">요청하신 주소와 페이지를 찾을 수 없습니다.</h1>
		<img src="./img/ep404.jpg" alt="찾을 수 없는 페이지">
	</div>
</body>
</html>