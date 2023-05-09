	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="common_path" value="${pageContext.request.contextPath }" />
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8");
%>
	
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <meta name="subject" content="Benito">
    <meta name="keywords" content="Benito, 가디건, 블라우스, 슬랙스, 옷, 쇼핑몰">
    <meta name="description" content="Benito는 가디건, 블라우스, 슬랙스 등 다양한 옷을 판매하고 있는 쇼핑몰입니다.">
    <meta name="author" content="jy">

    <link rel="shortcut icon" href="${common_path }/img/favicon/ico">

    <meta name="og:site_name" content="Benito">
    <meta name="og:title" content="Benito">
    <meta name="og:description" content="Benito는 가디건, 블라우스, 슬랙스 등 다양한 옷을 판매하고 있는 쇼핑몰입니다.">
    <meta name="og:url" content="https://www.benito.com">
    <meta name="og:image" content="${common_path }/img/thumbnail.png">

    <!--기본 폰트 및 초기화 로딩-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Pen+Script&family=Noto+Sans+KR&display=swap" rel="stylesheet">
    
    <!-- 스타일 초기화 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
    
 	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
