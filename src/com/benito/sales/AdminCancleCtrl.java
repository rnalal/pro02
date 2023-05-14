package com.benito.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.model.SalesDAO;
import com.benito.vo.SalesVO;

@WebServlet("/AdminCancle.do")
public class AdminCancleCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ocode = request.getParameter("ocode");
		
		SalesDAO dao = new SalesDAO();
		SalesVO sale = dao.getSales(ocode);
		String pcode = sale.getPcode();
		int amount = sale.getAmount();
		
		int cnt = dao.cancleSales(ocode, pcode, amount);
		
		if(cnt>=2){
			System.out.println("주문 취소 성공");
			response.sendRedirect("Survey.do");
		} else {
			System.out.println("주문 취소 실패");
			response.sendRedirect("SurveyLoad.do?ocode="+ocode);
		}
	}
}