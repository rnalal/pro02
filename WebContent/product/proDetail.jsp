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
<title>상품 상세 보기</title>
<style>
	.container-fluid {width:1280px;}
	.pi img{clear:both; display:block; width:1000px; margin: 0 auto;}
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h4>상품 > <a href="${path1 }/ProductList.do?cate=${pro.cate }">${cateMap.catename }</a></h4>
		<br><br>
		<fmt:setLocale value="ko_kr" />
			<h3><span style="background-color:rgb(200, 230, 255);">${pro.pname }</span></h3>
			<table class="table">
				<tbody>
					<tr>
						<td colspan="2">
							<div class="pi">
								<img src='${path1 }/product/${pro.pic1 }' alt="${pro.pname }" />
								<img src='${path1 }/product/${pro.pic2 }' alt="${pro.pname }" />
								<img src='${path1 }/product/${pro.pic3 }' alt="${pro.pname }" />
							</div>
						</td>
					</tr>	
					<tr>
						<th>상품명(상품코드)</th>
						<td>${pro.pname }(${pro.pcode })</td>
					</tr>	
					<tr>
						<th>총길이(cm)</th>
						<td>${pro.psize }</td>
					</tr>
					<tr>
						<th>설명</th>
						<td>${pro.pcontent }</td>
					</tr>
					<tr>
						<th>가격</th>
						<td>${pro.price }</td>
					</tr>
					<tr>
						<th>남은 수량</th>
						<td>${pro.pcount }</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="btn-group">
								<c:if test="${pro.pcount>0 && !sid.equals('young')}">
									<a href="${path1 }/InsertBastet.do?pcode=${pro.pcode }" class="btn btn-default" role="button">장바구니</a>
									<a href="${path1 }/InsertSales.do?pcode=${pro.pcode }" class="btn btn-default" role="button">구매</a>
									<a href="${path1 }/ProductList.do?cate=${pro.cate }" class="btn btn-default" role="button">목록</a>
								</c:if>
								<c:if test="${sid.equals('young') }">
									<a href="${path1 }/ReceiptProduct.do?pcode=${pro.pcode }" class="btn btn-default" role="button">입고</a>
									<a href="${path1 }/UpdateProduct.do?pcode=${pro.pcode }" class="btn btn-default" role="button">수정</a>
									<a href="${path1 }/DeleteProduct.do?pcode=${pro.pcode }" class="btn btn-default" role="button">삭제</a>
									<a href="${path1 }/ProductList.do?cate=${pro.cate }" class="btn btn-default" role="button">목록</a>
								</c:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<c:if test="${sid.equals('young') }">
				<div class="btn-group">
					<a href="${path1 }/InsertProduct.do" class="btn btn-info">상품 등록</a>
				</div>
			</c:if>
		</div>
	</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>





