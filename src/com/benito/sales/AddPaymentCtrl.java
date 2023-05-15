package com.benito.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Order1;
import com.benito.dto.Pay;
import com.benito.model.SalesDAO;

@WebServlet("/AddPayment.do")
public class AddPaymentCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int amount = Integer.parseInt(request.getParameter("amount"));
		String id = request.getParameter("id");
		String pcode = request.getParameter("pcode");
		String bn = request.getParameter("bn");
		String cate = request.getParameter("cate");
		
		Pay pay = new Pay();
		Order1 ord = new Order1();
		SalesDAO dao = new SalesDAO();
		
		ord.setOcode(dao.getOcodeGenerator());
		ord.setId(id);
		ord.setPcode(pcode);
		ord.setAmount(amount);
		ord.setPrice(request.getParameter("payamount"));
		ord.setTel(request.getParameter("tel"));
		ord.setAddr(request.getParameter("address1")+" "+request.getParameter("address2"));
		ord.setOstate("배송전");
		ord.setDname("");
		ord.setDcode("");
		
		pay.setYn(dao.getPnumGenerator());
		pay.setId(request.getParameter("id"));
		pay.setOcode(ord.getOcode());
		pay.setYpay(request.getParameter("ypay"));
		pay.setPnumber(request.getParameter("pnumber"));
		pay.setYprice(request.getParameter("payamount"));
		
		int cnt = dao.addSales(ord, pay, bn);
		if(cnt>=3){
			System.out.println("트랜잭션 처리 성공");
			response.sendRedirect("ProductList.do?cate="+cate);
		} else {
			System.out.println("트랜잭션 처리 실패");
			response.sendRedirect("AddSales.do?bn"+bn+"&pcode="+pcode+"&amount="+amount+"&id="+id);
		}
	}

}
