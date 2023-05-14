package com.benito.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Order1;
import com.benito.model.SalesDAO;

@WebServlet("/SurveyPro.do")
public class SurveyProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ocode = request.getParameter("ocode");
		Order1 ord = new Order1();
		ord.setOcode(request.getParameter("ocode"));
		ord.setDname(request.getParameter("dname"));
		ord.setDcode(request.getParameter("dcode"));
		ord.setOstate(request.getParameter("ostate"));
		
		SalesDAO dao = new SalesDAO();
		int cnt = dao.surveyUpdate(ord);
		if(cnt>0){
			response.sendRedirect("Survey.do");
		} else {
			response.sendRedirect("SurveyLoad.do?ocode="+ocode);
		}
	}
}