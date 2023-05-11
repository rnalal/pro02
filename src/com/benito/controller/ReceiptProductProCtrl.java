package com.benito.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.model.ProductDAO;

@WebServlet("/ReceiptProductPro.do")
public class ReceiptProductProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pcode = request.getParameter("pcode");
		int pcount = Integer.parseInt(request.getParameter("pcount"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		ProductDAO dao = new ProductDAO();
		int cnt = dao.receiptProduct(pcode, pcount, price);
		
		if(cnt==0){
			String msg = "상품을 입고하지 못했습니다.";
			request.setAttribute("msg", msg);
			
			RequestDispatcher view = request.getRequestDispatcher("ReceiptProduct.do?pcode="+pcode);
			view.forward(request, response);
		} else {
			response.sendRedirect("ProductList.do");
		}
	}

}
