package com.benito.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Latter;
import com.benito.model.LatterDAO;

@WebServlet("/AddReviewPro.do")
public class AddReviewProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		LatterDAO dao = new LatterDAO();
		Latter rev = new Latter();
		rev.setLn(dao.getRcodeGenerator());
		
		String id = request.getParameter("id"); 
		rev.setId(id);
		
		String ocode = request.getParameter("ocode");
		rev.setOcode(ocode);
		rev.setLlatter(request.getParameter("llatter"));
		rev.setLstar(Integer.parseInt(request.getParameter("lstar")));
		
		int cnt = dao.addReview(rev);
		if(cnt>0){
			response.sendRedirect("MySalesList.do?id="+id);
		} else {
			response.sendRedirect("AddResultUserReview.do?ocode="+ocode);
		}
	}
}
