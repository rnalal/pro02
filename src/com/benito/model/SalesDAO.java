package com.benito.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.benito.dto.Order1;
import com.benito.dto.Pay;

public class SalesDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int addSales(Order1 ord, Pay pay, String bn){
		int cnt = 0;		
		try {
			con = Oracle11.getConnection();
			con.setAutoCommit(false);
			pstmt.setString(1, ord.getOcode());
			pstmt.setString(2, ord.getId());
			pstmt.setString(3, ord.getPcode());
			pstmt.setInt(4, ord.getAmount());
			pstmt.setString(5, ord.getOprice());
			pstmt.setString(6, ord.getOstate());
			pstmt.setString(7, ord.getTel());
			pstmt.setString(8, ord.getDname());
			pstmt.setString(9, ord.getAddr());
			pstmt.setString(10, ord.getDcode());
			cnt = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(Oracle11.ADD_PAYMENT);
			pstmt.setString(1, pay.getYn());
			pstmt.setString(2, pay.getId());
			pstmt.setString(3, pay.getOcode());
			pstmt.setString(4, pay.getYpay());
			pstmt.setString(5, pay.getPnumber());
			pstmt.setString(6, pay.getYprice());
			cnt = cnt + pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(Oracle11.SALES_PRODUCT);
			pstmt.setInt(1, ord.getAmount());
			pstmt.setString(2, ord.getPcode());
			cnt = cnt + pstmt.executeUpdate();
			
			if(bn!=null){
				pstmt = con.prepareStatement(Oracle11.DELETE_BASKET);
				pstmt.setString(1, bn);
				cnt = cnt + pstmt.executeUpdate();
			}
			
			con.commit();	//수동 커밋
			con.setAutoCommit(true);	//오토커밋 활성화
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
	
	public String getOcodeGenerator(){
		String ocode = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.OCODE_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ocode = rs.getString("ocode");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		
		int tmp = Integer.parseInt(ocode) + 1;
		ocode = tmp + "";
		return ocode;
	}
	
	public String getPnumGenerator(){
		String pnum = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PNUM_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pnum = rs.getString("pnum");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		
		int tmp = Integer.parseInt(pnum) + 1;
		pnum = tmp + "";
		return pnum;
	}
}

