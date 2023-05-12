package com.benito.sales;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Product;
import com.benito.dto.User;
import com.benito.model.ProductDAO;
import com.benito.model.UserDAO;

@WebServlet("/AddSales.do")
public class AddSalesCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pcode = request.getParameter("pcode");
		String id = request.getParameter("id");
		
		//장바구니에서 넘어온 데이터 처리
		String bn = "";
		int bcount = 1;
		String pcs = request.getParameter("bcount");
		if(request.getParameter("bn")!=null){
			bn = request.getParameter("bn");
			bcount = Integer.parseInt(pcs);
			request.setAttribute("bn", bn);
			request.setAttribute("bcount", bcount);
		}
		String msg = "제품을 구매합니다.";
		ProductDAO dao = new ProductDAO();
		Product pro = dao.getProduct(pcode);
		
		UserDAO udao = new UserDAO();
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
		request.setAttribute("pro", pro);
		
		request.setAttribute("id", id);
		request.setAttribute("msg", msg);
		
		RequestDispatcher view = request.getRequestDispatcher("/sales/addSales.jsp");
		view.forward(request, response);		
	}
}
