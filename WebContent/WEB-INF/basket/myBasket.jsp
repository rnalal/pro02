<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />  
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common.jsp" %>
<title>장바구니 정보</title>
<style>
.container-fluid {width:1280px;}
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2 class="title">${user.name }님의 장바구니 정보</h2>
		<table class="table">
			<thead>
				<tr><th>연번</th><th>상품명</th><th>가격</th><th>수량</th><th>&nbsp;</th></tr>
			</thead>
			<tbody>
				<c:forEach var="bas" items="${basList }" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>
						<span title="${bas.pcode }">${bas.pname }</span>
					</td>
					<td>${bas.price }</td>
					<td>${bas.bcount }</td>
					<td>
						<a href="${path1 }/AddSales.do?bn=${bas.bn }" class="btn btn-info">구매</a>
						<a href="${path1 }/DeleteBasket.do?bn=${bas.bn }" class="btn btn-default">삭제</a>
						<a href="javascript:history.go(-1)" class="btn btn-default">뒤로 가기</a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty basList }">
				<tr>
					<td colspan="4">장바구니 정보가 존재하지 않습니다.</td>
				</tr>
				</c:if>
			</tbody>
			</table>
		</div>
	</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>