package com.benito.product;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benito.dto.Product;
import com.benito.model.ProductDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UpdateProductPro.do")
public class UpdateProductProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String savePath = "/product/img_1";
		int uploadFileSizeLimit = 10 * 1024 * 1024;
		String encType="UTF-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("지정된 업로드 디렉토리:"+savePath);
		System.out.println("서버 상의 실제 업로드되는 디렉토리 : "+uploadFilePath);
		
		int n = 0;
		String pcode = "";
		String[] fileName = new String[3];
		String[] oriFileName = new String[3];
		ProductDAO dao = new ProductDAO();
		Product pro = new Product();
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath,
				uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()){
				String file = (String) files.nextElement();
				fileName[n] = multi.getFilesystemName(file);
				oriFileName[n] = multi.getOriginalFileName(file);
				n++;
			}
			
			String ori_pic1 = multi.getParameter("ori_pic1");
			String ori_pic2 = multi.getParameter("ori_pic2");
			String ori_pic3 = multi.getParameter("ori_pic3");
			
			if (fileName[0] == null) {
				pro.setPic1(ori_pic1);
			} else {
				pro.setPic1("img_1/"+fileName[0]);
			}
			if (fileName[1] == null) {
				pro.setPic2(ori_pic2);
			} else {
				pro.setPic2("img_1/"+fileName[1]);
			}
			if (fileName[2] == null) {
				pro.setPic3(ori_pic3);
			} else {
				pro.setPic3("img_1/"+fileName[2]);
			}
			pcode = multi.getParameter("pcode");
			pro.setPcode(pcode);
			pro.setPname(multi.getParameter("pname"));
			pro.setPsize(Integer.parseInt(multi.getParameter("psize")));
			pro.setPcontent(multi.getParameter("pcontent"));
			pro.setCate(multi.getParameter("cate"));
			pro.setAmount(Integer.parseInt(multi.getParameter("amount")));
			pro.setPrice(multi.getParameter("price"));
		} catch (Exception e) {
			System.out.print("예외 발생 : " + e);
		}
		int cnt = dao.UpdateProduct(pro);
		if(cnt==0){
			String msg = "상품 정보를 수정하지 못했습니다.";
			request.setAttribute("msg", msg);
			
			RequestDispatcher view = request.getRequestDispatcher("UpdateProduct.do?pcode="+pcode);
			view.forward(request, response);
		} else {
			response.sendRedirect("ProductList.do");
		}
	}

}
