<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8");
%>
<%
	String sid = "";
	if(session!=null) sid = (String) session.getAttribute("sid");
%>
<header id="hd" class="container">
	<div class="container-fluid">
		<nav id="tnb" class="navbar navbar-default">
			<ul class="nav navbar-nav navbar-right" style="padding-right:40px">
				<c:if test="${empty sid }">
					<li><a href="${path }/UserLogin.do">로그인</a></li>
					<li><a href="${path }/UserTerms.do">회원가입</a></li>
				</c:if>
				<c:if test="${!empty sid }">
					<li><a href="${path }/MyPage.do">${user.name }님 마이페이지</a></li>
					<li><a href="${path1 }/MyBasket.do?id=${sid }">장바구니</a></li>
					<li><a href="${path }/UserLogout.do">로그아웃</a></li>
				</c:if>
				<c:if test="${sid=='young' }">
					<li><a href="">관리자</a></li>
				</c:if>
			</ul>			
		</nav>
	</div>
	<div class="container-fluid" style="padding-right:30px;">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${path1 }">BENITO</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" date-toggle="tab-hover" role="tablist">
						<li class="active"><a href="${path1 }/ShopIntro.do">BENITO<span class="sr-only">(current)</span></a></li>
						<li><a href="${path }/NoticeList.do">공지사항</a></li>
						<li class="dropdown">
							<a href="${path1 }/ProductList.do?cate=01" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">OUTER <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${path1 }/ProductList.do?cate=0101">코트</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0102">패딩</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0103">자켓</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0104">트위드</a></li>
							</ul>
						</li>		
						<li class="dropdown">
							<a href="${path1 }/ProductList.do?cate=02" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">DRESS <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${path1 }/ProductList.do?cate=0201">데일리</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0202">롱</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0203">미니</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0204">레이스</a></li>
							</ul>
						</li>	
						<li class="dropdown">
							<a href="${path1 }/ProductList.do?cate=03" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">BLOUSE <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${path1 }/ProductList.do?cate=0301">무지</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0302">셔츠</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0303">슬리브리스</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0304">프릴</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0305">타이</a></li>
							</ul>
						</li> 
						<li class="dropdown">
							<a href="${path1 }/ProductList.do?cate=04" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">KNIT/TEE <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${path1 }/ProductList.do?cate=0401">에브리데이T</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0402">니트</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0403">가디건</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0404">티</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="${path1 }/ProductList.do?cate=04" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">PANTS <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${path1 }/ProductList.do?cate=0501">데님</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0502">슬랙스</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0503">멜팅트레이닝</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0504">숏팬츠</a></li>
								<li class="divider"></li>
								<li><a href="${path1 }/ProductList.do?cate=0505">점프수트</a></li>
							</ul>
						</li>
					<li class="dropdown">
     		    	<a href="${path }/NoticeList.do"class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">커뮤니티 <span class="caret"></span></a>
			        <ul class="dropdown-menu" role="menu">
			            <li><a href="${path1 }/NoticeList.do">공지사항</a></li>
			            <li class="divider"></li>
			            <li><a href="${path1 }/FaqList.do">자주하는 질문</a></li>
			            <li class="divider"></li>
			            <li><a href="${path1 }/QnaList.do">묻고 답하기</a></li>
			            <li class="divider"></li>
			            <li><a href="${path1 }/ReviewList.do">이용후기</a></li>
			            <li class="divider"></li>
			            <li><a href="${path1 }/DataList.do">자료실</a></li>
			        </ul>
     		    </li>
					</ul>					
					<c:if test="${sid=='young' }">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="${path1 }/MemberList.do">회원 관리</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">상품/판매 관리 <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="AdminCategoryList.do">카테고리 관리</a></li>
									<li class="divider"></li>
									<li><a href="${path1 }/AdminProductList.do">상품 관리</a></li>
									<li class="divider"></li>
									<li><a href="${path1 }/AdminSalesList.do">판매 관리</a></li>
									<li class="divider"></li>
									<li><a href="${path1 }/AdminInventoryList.do">재고 관리</a></li>
									<li class="divider"></li>
									<li><a href="${path1 }/AdminBasketList.do">장바구니 관리</a></li>
									<li class="divider"></li>
									<li><a href="${path1 }/AdminCarryList.do">배송 관리</a></li>
									<li class="divider"></li>
									<li><a href="${path1 }/AdminPayList.do">결제 관리</a></li>
								</ul>
							</li>	
							<li class="dropdown" style="padding-right:30px; margin-right:30px;">
					          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">글 관리 <span class="caret"></span></a>
					          <ul class="dropdown-menu" role="menu">
					            <li><a href="${path1 }/AdminNoticeList.do">공지사항 관리</a></li>
					              <li class="divider"></li>
					            <li><a href="${path1 }/AdminReviewList.do">이용후기 관리</a></li>
					              <li class="divider"></li>
					            <li><a href="${path1 }/AdminQnaList.do">질문 및 답변 관리</a></li>
					            <li class="divider"></li>
					            <li><a href="${path1 }/AdminFaqList.do">자주하는 질문 관리</a></li>
					              <li class="divider"></li>
					            <li><a href="${path1 }/AdminDataList.do">자료실 관리</a></li>
					          </ul>
					        </li>		
						</ul>
					</c:if>		
				</div>				
			</div>
		</nav>
	</div>
</header>	
	