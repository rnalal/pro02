package com.benito.sales;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Product;
import com.benito.model.LatterDAO;
import com.benito.model.SalesDAO;
import com.benito.vo.SalesVO;

@WebServlet("/AddResultUserReview.do")
public class AddResultUserReviewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ocode = request.getParameter("ocode");
		
		LatterDAO dao = new LatterDAO();
		Product pro = dao.getProduct(ocode);
		
		SalesDAO sdao = new SalesDAO();
		SalesVO sale = sdao.getSales(ocode);
		
		String msg = "이용후기를 작성합니다.";
		request.setAttribute("msg", msg);
		request.setAttribute("pro", pro);
		request.setAttribute("sale", sale);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/review/addReview.jsp");
		view.forward(request, response);
	}

}
