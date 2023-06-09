package com.benito.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.benito.dto.Basket;
import com.benito.vo.BasketVO;

public class BasketDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//관리자 장바구니 보기
	public ArrayList<BasketVO> getBasketList(){
		ArrayList<BasketVO> basList = new ArrayList<BasketVO>();
		try{
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BASKET_SELECT_ALL2);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BasketVO bas = new BasketVO();
				bas.setBn(rs.getString("bn"));
				bas.setId(rs.getString("id"));
				bas.setName(rs.getString("name"));
				bas.setPcode(rs.getString("pcode"));
				bas.setPname(rs.getString("pname"));
				bas.setAmount(rs.getInt("amount"));
				bas.setPrice(rs.getInt("price"));
				basList.add(bas);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return basList;
	}
	
	//해당 사용자 장바구니 보기
	public ArrayList<BasketVO> getByIdBasketList(String id){
		ArrayList<BasketVO> basList = new ArrayList<BasketVO>();
		try{
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BASKET_SELECT_BYID2);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BasketVO bas = new BasketVO();
				bas.setBn(rs.getString("bn"));
				bas.setId(rs.getString("id"));
				bas.setName(rs.getString("name"));
				bas.setPcode(rs.getString("pcode"));
				bas.setPname(rs.getString("pname"));
				bas.setAmount(rs.getInt("amount"));
				bas.setPrice(rs.getInt("price"));
				basList.add(bas);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return basList;
	}
	
	//해당 상품별 장바구니 정보 조히
	public ArrayList<BasketVO> getByProductBasketList(String pcode){
		ArrayList<BasketVO> basList = new ArrayList<BasketVO>();
		try{
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BASKET_SELECT_BYPRODUCT);
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BasketVO bas = new BasketVO();
				bas.setBn(rs.getString("bn"));
				bas.setId(rs.getString("id"));
				bas.setPcode(rs.getString("pcode"));
				bas.setAmount(rs.getInt("amount"));
				basList.add(bas);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return basList;
	}
	
	//장바구니 상세보기
	public Basket getBasketDetail(String bn){
		Basket bas = new Basket();
		try{
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BASKET_SELECT_BYBNUM);
			pstmt.setString(1, bn);
			rs = pstmt.executeQuery();
			while(rs.next()){
				bas.setBn(rs.getString("bn"));
				bas.setId(rs.getString("id"));
				bas.setPcode(rs.getString("pcode"));
				bas.setAmount(rs.getInt("amount"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return bas;
	}
	
	//장바구니 담기
	public int insertBasket(Basket bas){
		int cnt = 0;
		String bn = createBn();
		try{
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.INSERT_BASKET);
			pstmt.setString(1, bn);
			pstmt.setString(2, bas.getId());
			pstmt.setString(3, bas.getPcode());
			pstmt.setInt(4, bas.getAmount());
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

	private String createBn() {
		String bn = "";
		try{
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BN_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bn = rs.getString("bn");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		int tmp = Integer.parseInt(bn) + 1;
		bn = tmp + "";
		return bn;
	}
	
	//장바구니 삭제
	public int deleteBasket(String bn){
		int cnt = 0;
		try{
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DELETE_BASKET);
			pstmt.setString(1, bn);
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
