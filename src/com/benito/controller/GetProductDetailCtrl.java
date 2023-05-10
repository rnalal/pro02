package com.benito.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Product;
import com.benito.model.ProductDAO;

@WebServlet("/ProductDetail.do")
public class GetProductDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pcode = request.getParameter("pcode");
		ProductDAO dao = new ProductDAO();
		Product pro = dao.getProduct(pcode);
		
		String cate = pro.getCate();
		HashMap<String, String> cateMap = dao.getCategory(cate);
		
		request.setAttribute("pro", pro);
		request.setAttribute("cateMap", cateMap);
		
		RequestDispatcher view = request.getRequestDispatcher("/product/proDetail.jsp");
		view.forward(request, response);		
	}
}
