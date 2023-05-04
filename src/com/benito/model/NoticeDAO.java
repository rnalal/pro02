package com.benito.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.benito.dto.Notice;

public class NoticeDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public ArrayList<Notice> noticeListAll(){
		ArrayList<Notice> notiList = new ArrayList<Notice>();
		//notice 테이블에서 모든 레코드를 검색하여 반환된 ResultSet를 notiList에 add한다.
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.NOTICE_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Notice noti = new Notice();
				noti.setN_no(rs.getInt(columnLabel:"n_no"));
				noti.setTitle(rs.getString(columnLabel:"title"));
				noti.setContent(rs.getString(columnLabel:"content"));
				noti.setAuthor(rs.getString(columnLabel:"author"));
				noti.setFile1(rs.getString(columnLabel:"file1"));
				noti.setNdate(rs.getString(columnLabel:"ndate"));
				noti.setReadcnt(rs.getInt(columnLabel:"readcnt"));
				notiList.add(noti);
			}
		} catch (ClassNotFoundException e) {	//오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();
		} catch (Exception e){		//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(rs, pstmt, con);
		return notiList;
	}



public Notice getNotice(int n_no){
	updateReadCount(n_no);
	Notice noti = new Notice();
	//n_no를 매개변수로 던져서 그에 해당하는 레코드 한 건만 반환받아 noti에 저장
	try {
		con = Oracle11.getConnection();
		pstmt = con.prepareStatement(Oracle11.NOTICE_SELECT_ONE);
		rs = pstmt.executeQuery();
		while(rs.next()){
			noti.setN_no(rs.getInt(columnLabel: "n_no"));
			noti.setTitle(rs.getString(columnLabel: "title"));
			noti.setContent(rs.getString(columnLabel: "content"));
			noti.setAuthor(rs.getString(columnLabel: "author"));
			noti.setFile1(rs.getString(columnLabel: "file1"));
			noti.setNdate(rs.getString(columnLabel: "ndate"));
			noti.setReadcnt(rs.getInt(columnLabel:"readcnt"));
		}
	} catch (ClassNotFoundException e) {	//오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
		e.printStackTrace();
	} catch (SQLException e){	//sql 구문이 틀린 경우 발생
		e.printStackTrace();
	} catch (Exception e){		//알 수 없는 예외인 경우 발생
		e.printStackTrace();
	}
	Oracle11.close(rs, pstmt, con);
	return noti;
	}

public void updateReadCount(int n_no){
	try {
		con = Oracle11.getConnection();
		pstmt = con.prepareStatement(Oracle11.NOTICE_READNO_UPDATE);
		pstmt.setInt(1, n_no);
		pstmt.executeUpdate();
	} catch (ClassNotFoundException e){
		e.printStackTrace();
	} catch (SQLException e){	//sql 구문이 틀린 경우 발생
		e.printStackTrace();
	} catch (Exception e){		//알 수 없는 예외인 경우 발생
		e.printStackTrace();
	}
	Oracle11.close(pstmt, con);
	}
}

		
		










