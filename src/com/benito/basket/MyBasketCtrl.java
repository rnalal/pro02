package com.benito.basket;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.User;
import com.benito.model.BasketDAO;
import com.benito.model.UserDAO;
import com.benito.vo.BasketVO;

@WebServlet("/MyBasket.do")
public class MyBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		BasketDAO dao = new BasketDAO();
		UserDAO udao = new UserDAO();
		
		ArrayList<BasketVO> basList = dao.getByIdBasketList(id);
		request.setAttribute("basList", basList);
		
		User user = new User();
		
		try {
			user = udao.myInfo(id);
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/basket/myBasket.jsp");
		view.forward(request, response);
	}

}
