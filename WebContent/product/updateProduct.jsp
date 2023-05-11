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
<title>제품 정보 수정하기</title>
<style>
.container-fluid {width:1280px;}
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px;">
	<div class="container-fluid">
		<h2>제품 등록</h2>
		<p>${msg }</p>
		<form action="${path1 }/UpdateProductPro.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th>
							<label for="cate1">상품 분류(상품분류코드)</label><br>
						</th>
						<td>
							<span id="pcodetxt">${catename } (${pro.cate })</span>
						</td>	
					</tr>
					<tr>
						<th><label for="pname">상품명</label></th>
						<td>
							<input type="text" name="pname" id="pname" title="40자 내로 작성" placeholder="40자 내로 작성" class="form-control" required>
							<input type="hidden" name="pcode" id="pcode" value="${pro.pcode }">
						</td>	
					</tr>
					<tr>
						<th><label for="psize">총길이(cm)</label></th>
						<td>
							<input type="text" name="psize" id="psize" title="10자 내로 작성" placeholder="10자 내로 작성" class="form-control">
						</td>	
					</tr>
					<tr>
						<th><label for="pcontent">상품 설명</label></th>
						<td>
							<textarea rows="5" cols="100" name="pcontent" id="pcontent" maxlength="500" title="500자 내로 작성" class="form-control">500자 이내</textarea>
						</td>	
					</tr>
					<tr>
						<th><label for="price">가격</label></th>
						<td>
							<input type="number" name="price" id="price" value="1000" min="0" max="5000000" step="100" title="0~5000000" class="form-control">
						</td>	
					</tr>
					<tr>
						<th><label for="pcount">수량</label></th>
						<td>
							<input type="number" name="pcount" id="pcount" value="1" min="1" max="500" title="1~500" class="form-control">
						</td>	
					</tr>
					<tr>
						<th><label for="pic1">상품 이미지 변경</label></th>
						<td>
							<label for="pic1">이미지 1</label>
							<p id="picAttac1"><img src='${path1 }/product/${pro.pic1 }' alt="${pro.pname }" /></p>
							<p></p>
							<input type="file" accept="image/*" name="pic1" id="pic1" class="form-control file"><br>
							<input type="hidden" name="ori_pic1" id="ori_pic1" value="${pro.pic1 }">
							<br>
							
							이미지 2 : 
							<label for="pic2">이미지 2</label>
							<p id="picAttac2"><img src='${path1 }/product/${pro.pic1 }' alt="${pro.pname }" /></p>
							<p></p>
							<input type="file" accept="image/*" name="pic2" id="pic2" class="form-control file"><br>
							<input type="hidden" name="ori_pic2" id="ori_pic2" value="${pro.pic2 }">
							<br>
							
							이미지 3 : 
							<label for="pic3">이미지 3</label>
							<p id="picAttac3"><img src='${path1 }/product/${pro.pic1 }' alt="${pro.pname }" /></p>
							<p></p>
							<input type="file" accept="image/*" name="pic3" id="pic3" class="form-control file"><br>
							<input type="hidden" name="ori_pic3" id="ori_pic3" value="${pro.pic3 }">
							<br>
						</td>						
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="상품 정보 수정" class="btn btn-info">
							<a href="${path1 }/AdminProductList.do" class="btn btn-default">상품 목록</a>
						</td>
					</tr>						
				</tbody>
			</table>
		</form>
		<script>
		$(document).ready(function(){
			$("#pcom").click(function(){
				if($(this).text()=="500자 이내"){
					$(this).text("");
				}
			});
			$(".file").change(function(){
				var tar = $(this).index();
				if($(this).val()!=""){
					$(this).prev("p").html("<strong>이미지 첨부 성공</strong>");
				}
			});
		});
	</script>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>