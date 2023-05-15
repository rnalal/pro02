package com.benito.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.benito.dto.Order1;
import com.benito.dto.Pay;
import com.benito.vo.SalesVO;

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
			pstmt.setString(5, ord.getPrice());
			pstmt.setString(6, ord.getOdate());
			pstmt.setString(7, ord.getOstate());
			pstmt.setString(8, ord.getTel());
			pstmt.setString(9, ord.getDname());
			pstmt.setString(10, ord.getAddr());
			pstmt.setString(11, ord.getDcode());
			cnt = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(Oracle11.ADD_PAYMENT);
			pstmt.setString(1, pay.getYn());
			pstmt.setString(2, pay.getId());
			pstmt.setString(3, pay.getOcode());
			pstmt.setString(4, pay.getYpay());
			pstmt.setString(5, pay.getPnumber());
			pstmt.setString(6, pay.getYprice());
			pstmt.setString(7, pay.getYdate());
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
		String yn = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PNUM_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				yn = rs.getString("yn");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		
		int tmp = Integer.parseInt(yn) + 1;
		yn = tmp + "";
		return yn;
	}		

		//관리자의 판매+결제 전체 목록 로딩
		public ArrayList<SalesVO> getSalesList(){
			ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.SALES_LIST);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesVO sale = new SalesVO();
					sale.setOcode(rs.getString("ocode"));
					sale.setId(rs.getString("id"));
					sale.setPcode(rs.getString("pcode"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setOdate(rs.getString("odate"));
					sale.setOstate(rs.getString("ostate"));
					sale.setTel(rs.getString("tel"));
					sale.setDname(rs.getString("dname"));
					sale.setAddr(rs.getString("addr"));
					sale.setDcode(rs.getString("dcode"));
					sale.setYn(rs.getString("yn"));
					sale.setYpay(rs.getString("ypay"));
					sale.setPnumber(rs.getString("pnumber"));
					salesList.add(sale);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return salesList;
		}
		
		//관리자의 특정 판매 데이터 로딩 
		public SalesVO getSales(String ocode){
			SalesVO sale = new SalesVO();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.SALES_INFO);
				pstmt.setString(1, ocode);
				rs = pstmt.executeQuery();
				if(rs.next()){
					sale.setOcode(rs.getString("ocode"));
					sale.setId(rs.getString("id"));
					sale.setPcode(rs.getString("pcode"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setOdate(rs.getString("odate"));
					sale.setOstate(rs.getString("ostate"));
					sale.setTel(rs.getString("tel"));
					sale.setDname(rs.getString("dname"));
					sale.setAddr(rs.getString("addr"));
					sale.setDcode(rs.getString("dcode"));
					sale.setYn(rs.getString("yn"));
					sale.setYpay(rs.getString("ypay"));
					sale.setPnumber(rs.getString("pnumber"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return sale;
		}
		
		//특정 사용자의 구매 목록 로딩
		public ArrayList<SalesVO> getByIdSalesList(String id){
			ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_SALES_LIST);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesVO sale = new SalesVO();
					sale.setOcode(rs.getString("ocode"));
					sale.setId(rs.getString("id"));
					sale.setPcode(rs.getString("pcode"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setOdate(rs.getString("odate"));
					sale.setOstate(rs.getString("ostate"));
					sale.setTel(rs.getString("tel"));
					sale.setDname(rs.getString("dname"));
					sale.setAddr(rs.getString("addr"));
					sale.setDcode(rs.getString("dcode"));
					sale.setYn(rs.getString("yn"));
					sale.setYpay(rs.getString("ypay"));
					sale.setPnumber(rs.getString("pnumber"));
					salesList.add(sale);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return salesList;
		}
		
		//특정 사용자의 특정 판매 데이터 로딩
		public SalesVO getByIdSales(String id, String ocode){
			SalesVO sale = new SalesVO();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_SALES_LIST);
				pstmt.setString(1, id);
				pstmt.setString(2, ocode);
				rs = pstmt.executeQuery();
				while(rs.next()){
					sale.setOcode(rs.getString("ocode"));
					sale.setId(rs.getString("id"));
					sale.setPcode(rs.getString("pcode"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setOdate(rs.getString("odate"));
					sale.setOstate(rs.getString("ostate"));
					sale.setTel(rs.getString("tel"));
					sale.setDname(rs.getString("dname"));
					sale.setAddr(rs.getString("addr"));
					sale.setDcode(rs.getString("dcode"));
					sale.setYn(rs.getString("yn"));
					sale.setYpay(rs.getString("ypay"));
					sale.setPnumber(rs.getString("pnumber"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return sale;
		}
		
		//판매 정보 목록만 로딩
		public ArrayList<Order1> getByIdBuyList(String id){
			ArrayList<Order1> ordList = new ArrayList<Order1>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_BUY_LIST);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Order1 ord = new Order1();
					ord.setOcode(rs.getString("ocode"));
					ord.setId(rs.getString("id"));
					ord.setPcode(rs.getString("pcode"));
					ord.setAmount(rs.getInt("amount"));
					ord.setPrice(rs.getString("price"));
					ord.setOdate(rs.getString("odate"));
					ord.setOstate(rs.getString("ostate"));
					ord.setTel(rs.getString("tel"));
					ord.setDname(rs.getString("dname"));
					ord.setAddr(rs.getString("addr"));
					ord.setDcode(rs.getString("dcode"));
					ordList.add(ord);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return ordList;
		}
		
		//모든 결제 정보 목록만 로딩
		public ArrayList<Pay> getByPayList(){
			ArrayList<Pay> payList = new ArrayList<Pay>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.PAY_LIST);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Pay pay = new Pay();
					pay.setYn(rs.getString("yn"));
					pay.setId(rs.getString("id"));
					pay.setOcode(rs.getString("ocode"));
					pay.setYpay(rs.getString("ypay"));
					pay.setPnumber(rs.getString("pnumber"));
					pay.setYprice(rs.getString("yprice"));
					pay.setYdate(rs.getString("ydate"));
					payList.add(pay);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return payList;
		}
		
		
		//특정 사용자의 특정 결제 정보
		public Pay getByIdPay(String ocode){
			Pay pay = new Pay();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYOCODE_PAY);
				pstmt.setString(1, ocode);
				rs = pstmt.executeQuery();
				if(rs.next()){
					pay.setYn(rs.getString("yn"));
					pay.setId(rs.getString("id"));
					pay.setOcode(rs.getString("ocode"));
					pay.setYpay(rs.getString("ypay"));
					pay.setPnumber(rs.getString("pnumber"));
					pay.setYprice(rs.getString("yprice"));
					pay.setYdate(rs.getString("ydate"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return pay;
		}
		
		public int surveyUpdate(Order1 ord){
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.UPDATE_SURVEY);
				pstmt.setString(1, ord.getDname());
				pstmt.setString(2, ord.getDcode());
				pstmt.setString(3, ord.getOstate());
				pstmt.setString(4, ord.getOcode());
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

		public int cancleSales(String ocode, String pcode, int amount) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				con.setAutoCommit(false);	//트랜잭션 처리시 오토커밋을 비활성화
				pstmt = con.prepareStatement(Oracle11.DELETE_BUY);
				pstmt.setString(1, ocode);
				cnt = pstmt.executeUpdate();

				pstmt = con.prepareStatement(Oracle11.DELETE_PAYMENT);
				pstmt.setString(1, ocode);
				cnt = cnt + pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(Oracle11.RETURN_PRODUCT);
				pstmt.setInt(1, amount);
				pstmt.setString(2, pcode);
				cnt = cnt + pstmt.executeUpdate();
				
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

		public int returnSales(String ocode) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.RETURN_SALES);
				pstmt.setString(1, ocode);
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

		public int okSales(String ocode) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.OK_SALES);
				pstmt.setString(1, ocode);
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
	}