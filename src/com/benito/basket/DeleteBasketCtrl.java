package com.benito.basket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Basket;
import com.benito.model.BasketDAO;

@WebServlet("/DeleteBasket.do")
public class DeleteBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bn = request.getParameter("bn");
		
		BasketDAO dao = new BasketDAO();
		Basket bas = dao.getBasketDetail(bn);
		String id = bas.getId();
		int cnt = dao.deleteBasket(bn);
		if(cnt==1){
			response.sendRedirect("MyBasket.do?id="+id);
		} else {
			response.sendRedirect("MyBasket.do?id="+id);
		}
	}

}
