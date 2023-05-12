package com.benito.basket;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.model.BasketDAO;
import com.benito.vo.BasketVO;

@WebServlet("/MyBasket.do")
public class MyBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		BasketDAO dao = new BasketDAO();
		
		ArrayList<BasketVO> basList = dao.getByIdBasketList(id);
		request.setAttribute("basList", basList);
		String username = "";
		for(BasketVO bas : basList){
			username = bas.getName();
		}
		request.setAttribute("username", username);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/basket/myBasket.jsp");
		view.forward(request, response);
	}

}
