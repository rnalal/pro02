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
	final static String INSERT_NOTICE = "insert into notice values (no_seq.nextval, ?, ?, ?, ?, default, default)";
	final static String UPDATE_NOTICE = "update notice set title=?, content=?, file1=?, ndate=sysdate where n_no=?";
	final static String UPDATE_NOTICE2 = "update notice set title=?, content=?, ndate=sysdate where n_no=?";
	final static String DELETE_NOTICE = "delete from notice where n_no=?";
	
	final static String USER_SELECT_ALL	= "select * from user1 order by udate desc";
	final static String USER_LOGIN = "select * from user1 where id=?";
	final static String USER_VISIT_COUNT = "update user1 set visited=viseite+1 where id=?";
	final static String INSERT_USER = "insert into user1(id, pw, name, tel, addr, email) values (?,?,?,?,?,?)";
	final static String UPDATE_USER	= "update user1 set pw=?, name=?, tel=?, addr=?, email=? where id=?";
	final static String UPDATE_USER2 = "update user1 set name=?, tel=?, addr=?, email=? where id=?";
	final static String DELETE_USER = "delete from user1 where id=?";
	
	final static String PRODUCT_CATENAME_SELECT = "select * from category where cate=?";
	final static String PRODUCT_SELECT_ALL = "select * from product order by cate desc";
	final static String PRODUCT_SELECT = "select * from product where pcode=?";
	final static String PRODUCT_SOLDOUT_SELECT = "select * from product where pcount<=0";
	final static String PRODUCT_CATE_SELECT = "select * from product where cate=?";
	final static String PRODUCT_CATE_SELECT2 = "select * from product where cate like ?||'%'";
	final static String PRODUCT_CATE_SELECT3 = "select * from product where cate like concat(?, '%')";
	
	final static String FIRST_CATEGORY_SELECT = "select distinct substr(cate,1,2) as ct, catagroup from category group by substr(cate,1,2), catagroup order by ct";
	final static String SECOND_CATEGORY_SELECT = "select cate, catename from category where cate like ?||'%' order by cate";
	final static String PCODE_GENERATOR = "select pcode from (select * from product where cate=? order by pcode desc) where rownum = 1";
	
	final static String INSERT_PRODUCT = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
	final static String RECEIPT_PRODUCT = "update product set pcount=pcount+?, price=? where pcode=?";
	final static String UPDATE_PRODUCT = "update product set pcount=pcount+?, price=? where pcode=?";
	final static String UPDATE_PRODUCT2 = "update product set pname=?, psize=?, price=?, pcontent=?, pcount=?, pic1=?, pic2=?, pic3=? where pcode=?";
	final static String SALES_PRODUCT = "update product set pcount=pcount-? where pcode=?";
	final static String DELETE_PRODUCT = "delete from product where pcode=?";
	
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
