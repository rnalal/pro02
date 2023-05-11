package com.benito.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.model.ProductDAO;

@WebServlet("/DeleteProduct.do")
public class DeleteProductCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pcode = request.getParameter("pcode");
		
		ProductDAO dao = new ProductDAO();
		int cnt = dao.deleteProduct(pcode);
		if(cnt==0){
			String msg = "상품 정보를 삭제하지 못했습니다.";
			request.setAttribute("msg", msg);
			
			RequestDispatcher view = request.getRequestDispatcher("UpdateProduct.do?pcode="+pcode);
			view.forward(request, response);
		} else {
			response.sendRedirect("ProductList.do");
		}
	}
}
