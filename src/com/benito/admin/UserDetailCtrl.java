package com.benito.admin;

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
import javax.servlet.http.HttpSession;

import com.benito.dto.User;
import com.benito.model.UserDAO;

@WebServlet("/UserDetail.do")
public class UserDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wid = request.getParameter("id");
		UserDAO dao = new UserDAO();
		User user = new User();
		String msg = "";
		try {
				user = dao.userDetail(wid);
			} catch (InvalidKeyException | NoSuchPaddingException
					| NoSuchAlgorithmException | InvalidKeySpecException
					| InvalidAlgorithmParameterException
					| BadPaddingException | IllegalBlockSizeException e) {
				e.printStackTrace();
			}
	msg = "회원 정보를 로딩하였습니다.";
		request.setAttribute("user", user);
		request.setAttribute("msg", msg);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/userDetail.jsp");
		view.forward(request, response);
	}
}

