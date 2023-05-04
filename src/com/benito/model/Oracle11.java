package com.benito.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oracle11 {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String user = "test1";
	static String pass = "1234";
	
	final static String NOTICE_SELECT_ALL = "select * from notice";
	final static String NOTICE_SELECT_ONE = "select * from notice where n_no=?";
	final static String NOTICE_READNO_UPDATE = "update notice set readno=readno+1 where n_no=?";
	final static String INSERT_NOTICE="insert into notice values (no_seq.nextval, ?, ?, ?, ?, default)";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	public static void close(PreparedStatement pstmt, Connection con){
		if(pstmt!=null){
			try{	
				pstmt.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(con!=null){
			try{
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
	}
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con){
		if(rs!=null){
			try{
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try{
				pstmt.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(con!=null){
			try{
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
