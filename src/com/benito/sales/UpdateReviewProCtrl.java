package com.benito.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Latter;
import com.benito.model.LatterDAO;

@WebServlet("/UpdateReviewPro.do")
public class UpdateReviewProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		LatterDAO dao = new LatterDAO();
		Latter rev = new Latter();
		
		String ln = request.getParameter("ln");		
		rev.setLn(ln);
		
		String id = request.getParameter("id"); 
		rev.setId(id);
		
		String ocode = request.getParameter("ocode");
		rev.setOcode(ocode);
		rev.setLlatter(request.getParameter("Llateer"));
		if(request.getParameter("new_lstar")==null) {
			rev.setLstar(Integer.parseInt(request.getParameter("lstar")));
		} else {
			rev.setLstar(Integer.parseInt(request.getParameter("new_lstar")));	
		}
		
		int cnt = dao.updateReview(rev);
		if(cnt>0){
			response.sendRedirect("MySalesList.do?id="+id);
		} else {
			response.sendRedirect("UpdateReview.do?ln="+ln);
		}
	}
}

