package com.benito.notice;

import java.io.IOException;
import java.net.URLEncoder;
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

import com.benito.dto.Notice;
import com.benito.dto.User;
import com.benito.model.NoticeDAO;
import com.benito.model.UserDAO;
import com.benito.service.KoreanPro;

@WebServlet("/GetNotice.do")
public class GetNoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n_no = Integer.parseInt(request.getParameter("n_no"));
		NoticeDAO ndao = new NoticeDAO();
		Notice noti = new Notice();
		noti = ndao.getNotice(n_no);
		String file1 = ""; 
		String filepath1 = "";		
		
		if(noti.getFile1()!=null){
			file1 = noti.getFile1().substring(5);
			filepath1 = noti.getFile1().substring(0,4);
			file1 = URLEncoder.encode(file1, "UTF-8");
		}		
		request.setAttribute("file1", file1);
		request.setAttribute("filepath1", filepath1);
		request.setAttribute("noti", noti);
		
		HttpSession ses = request.getSession();
		String id = (String) ses.getAttribute("sid");
		
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
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/notice/getNotice.jsp");
		view.forward(request, response);
	}
}

