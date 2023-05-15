package com.benito.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.benito.dto.Latter;
import com.benito.dto.Product;

public class LatterDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//해당 상품의 이용후기 불러오기
	public Product getProduct(String ocode){
		Product pro = new Product();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.REVIEW_PRODUCT);
			pstmt.setString(1, ocode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pro.setPcode(rs.getString("pcode"));
				pro.setPname(rs.getString("pname"));
				pro.setPsize(rs.getInt("psize"));
				pro.setPrice(rs.getString("price"));
				pro.setPcontent(rs.getString("pcontent"));
				pro.setAmount(rs.getInt("amount"));
				pro.setPic1(rs.getString("pic1"));
				pro.setPic2(rs.getString("pic2"));
				pro.setPic3(rs.getString("pic3"));
				pro.setCate(rs.getString("cate"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return pro;
	}
	
	//결제 번호 생성
	public String getRcodeGenerator(){
		String ln = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.RCODE_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ln = rs.getString("ln");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		
		int tmp = Integer.parseInt(ln) + 1;
		ln = tmp + "";
		return ln;
	}
	
	//리뷰 등록하기
	public int addReview(Latter rev){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.ADD_REVIEW);
			pstmt.setString(1, rev.getLn());
			pstmt.setString(2, rev.getId());
			pstmt.setString(3, rev.getOcode());
			pstmt.setString(4, rev.getLlatter());
			pstmt.setInt(5, rev.getLstar());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		SalesDAO dao = new SalesDAO();
		cnt = cnt + dao.okSales(rev.getOcode());
		return cnt;
	}
	
	
	//해당 상품의 리뷰 불러오기
	public ArrayList<Latter> getPcodeByLatter(String pcode){
		ArrayList<Latter> rList = new ArrayList<Latter>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PCODEBY_REVIEW);
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Latter rev = new Latter();
				rev.setLn(rs.getString("ln"));
				rev.setId(rs.getString("id"));
				rev.setOcode(rs.getString("ocode"));
				rev.setLdate(rs.getString("ldate"));
				rev.setLlatter(rs.getString("llatter"));
				rev.setLstar(rs.getInt("lstar"));
				rList.add(rev);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return rList;
	}
	
	//리뷰 한 건 불러오기
	public Latter getLnByLatter(String ln){
		Latter rev = new Latter();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.RCODEBY_REVIEW);
			pstmt.setString(1, ln);
			rs = pstmt.executeQuery();
			if(rs.next()){
				rev.setLn(rs.getString("ln"));
				rev.setId(rs.getString("id"));
				rev.setOcode(rs.getString("ocode"));
				rev.setLdate(rs.getString("ldate"));
				rev.setLlatter(rs.getString("llatter"));
				rev.setLstar(rs.getInt("lstar"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return rev;
	}
	
	//리뷰 수정하기
	public int updateReview(Latter rev){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_REVIEW);
			pstmt.setString(1, rev.getLlatter());
			pstmt.setInt(2, rev.getLstar());
			pstmt.setString(3, rev.getId());
			pstmt.setString(4, rev.getLn());
			
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
	
	public ArrayList<Latter> getAllReview(){
		ArrayList<Latter> rList = new ArrayList<Latter>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.ALL_REVIEW);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Latter rev = new Latter();
				rev.setLn(rs.getString("ln"));
				rev.setId(rs.getString("id"));
				rev.setOcode(rs.getString("ocode"));
				rev.setLdate(rs.getString("ldate"));
				rev.setLlatter(rs.getString("llatter"));
				rev.setLstar(rs.getInt("lstar"));
				rList.add(rev);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return rList;
	}
}

