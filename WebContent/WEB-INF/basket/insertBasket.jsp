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
<title>제품 등록 하기</title>
<style>
.container-fluid {width:1280px;}
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>장바구니 담기</h2>
		<p>${msg }</p>
		<form action="${path1 }/InsertBasketPro.do" method="POST">
			<table class="table">
				<tbody>
					<tr>
						<th><label for="pname">상품명</label></th>
						<td>
							<input type="hidden" name="id" id="id" value="${sid }">
							<input type="hidden" name="pcode" id="pcode" value="${pro.pcode }">
							<input type="text" name="pname" id="pname" value="${pro.pname }" title="40자 내로 작성" placeholder="40자 내로 작성" class="form-control" disabled>
						</td>
					</tr>
					<tr>
						<th><label for="price">가격</label></th>
						<td>
							<input type="number" name="price" id="price" value="${pro.price }" min="0" max="5000000" step="100" title="0~5000000" class="form-control" disabled>
						</td>
					</tr>
					<tr>
						<th><label for="pcount">수량</label></th>
						<td>
							<input type="number" name="bcount" id="bcount" value="1" min="0" max="${pro.pcount }" title="1~500" class="form-control">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="장바구니 담기" class="btn btn-info">
							<a href="${path1 }/MyBasket.do?id=${sid }" class="btn btn-default">장바구니 가기</a>				
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>
</body>
</html>