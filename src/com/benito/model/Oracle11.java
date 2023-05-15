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
	final static String USER_VISIT_COUNT = "update user1 set visited=visited+1 where id=?";
	final static String INSERT_USER = "insert into user1(id, pw, name, tel, addr, email) values (?,?,?,?,?,?)";
	final static String UPDATE_USER	= "update user1 set pw=?, name=?, tel=?, addr=?, email=? where id=?";
	final static String UPDATE_USER2 = "update user1 set name=?, tel=?, addr=?, email=? where id=?";
	final static String DELETE_USER = "delete from user1 where id=?";
	final static String USER_SELECT_TEL = "select tel from user1 where id=?";
	final static String UPDATE_PW_RESET = "update user1 set pw=? where id=?";
	
	final static String PRODUCT_CATENAME_SELECT = "select * from category where cate=?";
	final static String PRODUCT_SELECT_ALL = "select * from product order by cate desc";
	final static String PRODUCT_SELECT = "select * from product where pcode=?";
	final static String PRODUCT_SOLDOUT_SELECT = "select * from product where amount<=0";
	final static String PRODUCT_CATE_SELECT = "select * from product where cate=?";
	final static String PRODUCT_CATE_SELECT2 = "select * from product where cate like ?||'%'";
	final static String PRODUCT_CATE_SELECT3 = "select * from product where cate like concat(?, '%')";
	
	final static String FIRST_CATEGORY_SELECT = "select distinct substr(cate,1,2) as ct, catagroup from category group by substr(cate,1,2), catagroup order by ct";
	final static String SECOND_CATEGORY_SELECT = "select cate, catename from category where cate like ?||'%' order by cate";
	final static String PCODE_GENERATOR = "select pcode from (select * from product where cate=? order by pcode desc) where rownum = 1";
	
	final static String INSERT_PRODUCT = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
	final static String RECEIPT_PRODUCT = "update product set amount=amount+?, price=? where pcode=?";
	final static String UPDATE_PRODUCT = "update product set amount=amount+?, price=? where pcode=?";
	final static String UPDATE_PRODUCT2 = "update product set pname=?, psize=?, price=?, pcontent=?, amount=?, pic1=?, pic2=?, pic3=? where pcode=?";
	final static String SALES_PRODUCT = "update product set amount=amount-? where pcode=?";
	final static String DELETE_PRODUCT = "delete from product where pcode=?";
	
	final static String BASKET_SELECT_ALL = "select * from basket order by bn desc";
	final static String BASKET_SELECT_ALL2 = "select basket.bn as bn, basket.id as id, user1.name as name, basket.pcode as pcode, product.pname as pname, basket.amount as amount, product.price as price from basket, user1, product where basket.id=user1.id and basket.pcode=product.pcode";
	final static String BASKET_SELECT_BYID = "select * from basket where id=?";
	final static String BASKET_SELECT_BYID2 = "select basket.bn as bn, basket.id as id, user1.name as name, basket.pcode as pcode, product.pname as pname, basket.amount as amount, product.price as price from basket, user1, product where basket.id=user1.id and basket.pcode=product.pcode and basket.id=?";
	final static String BASKET_SELECT_BYPRODUCT = "select * from basket where pcode=?";
	final static String BASKET_SELECT_BYBNUM = "select * from basket where bn=?";
	final static String INSERT_BASKET = "insert into basket values(?,?,?,?)";
	final static String DELETE_BASKET = "delete from basket where bn=?";
	final static String BN_GENERATOR = "select bn from (select bn from basket order by bn desc) where rownum = 1";
	
	final static String OCODE_GENERATOR = "select ocode from (select * from order1 order by ocode desc) where rownum = 1";
	final static String PNUM_GENERATOR = "select yn from (select * from pay order by yn desc) where rownum = 1";
	final static String ADD_SALES = "insert into order1 values (?,?,?,?,?,default,?,?,?,?,?)";
	final static String ADD_PAYMENT = "insert into pay values (?,?,?,?,?,?,default)";
	final static String BUY_TRANS_BASKET = "delete from basket where bn=?";
	
	final static String BYID_BUY_LIST = "select * from order1 where id=? order by ocode desc";
	final static String BYID_BUY = "select * from order1 where id=? and ocode=?";
	final static String PAY_LIST = "select * from pay order by yn desc";
	final static String BYOCODE_PAY = "select * from pay where ocode=? order by yn desc";
	final static String SALES_LIST = "select order1.ocode as ocode, order1.id as id, order1.pcode as pcode, order1.amount as amount, order1.price as price, order1.odate as odate, order1.ostate as ostate, order1.tel as tel, order1.dname as dname, order1.addr as addr, order1.dcode as dcode,pay.yn as yn, pay.ypay as ypay, pay.pnumber as pnumber from order1, pay where pay.ocode=order1.ocode order by order1.ocode";
	final static String SALES_INFO = "select order1.ocode as ocode, order1.id as id, order1.pcode as pcode, order1.amount as amount, order1.price as price, order1.odate as odate, order1.ostate as ostate, order1.tel as tel, order1.dname as dname, order1.addr as addr, order1.dcode as dcode,pay.yn as yn, pay.ypay as ypay, pay.pnumber as pnumber from order1, pay where pay.ocode=order1.ocode and order1.ocode=? order by order1.ocode";
	final static String BYID_SALES_LIST = "select order1.ocode as ocode, order1.id as id, order1.pcode as pcode, order1.amount as amount, order1.price as price, order1.odate as odate, order1.ostate as ostate, order1.tel as tel, order1.dname as dname, order1.addr as addr, order1.dcode as dcode,pay.yn as yn, pay.ypay as ypay, pay.pnumber as pnumber from order1, pay where pay.ocode=order1.ocode and order1.id=? order by order1.ocode";
	final static String BYID_GET_SALE = "select order1.ocode as ocode, order1.id as id, order1.pcode as pcode, order1.amount as amount, order1.price as price, order1.odate as odate, order1.ostate as ostate, order1.tel as tel, order1.dname as dname, order1.addr as addr, order1.dcode as dcode, pay.yn as yn, pay.ypay as ypay, pay.pnumber as pnumber from order1, pay where pay.ocode=order1.ocode and id=? and order1.ocode=?";
	final static String UPDATE_SURVEY = "update order1 set dname=?, dcode=?, ostate=? where ocode=?";
	final static String DELETE_BUY = "delete from order1 where ocode=?";
	final static String DELETE_PAYMENT = "delete from pay where ocode=?";
	final static String RETURN_PRODUCT = "update product set amount=amount+? where pcode=?";
	final static String RETURN_SALES = "update order1 set ostate='반품요청' where ocode=?";
	final static String OK_SALES = "update order1 set ostate='구매완료' where ocode=?";
	
	final static String REVIEW_PRODUCT = "select * from product where pcode = (select pcode from order1 where ocode=?)";
	final static String RCODE_GENERATOR = "select ln from (select * from latter order by ln desc) where rownum = 1";
	final static String ADD_REVIEW = "insert into latter values (?,?,?,default,?,?)";
	final static String PCODEBY_REVIEW = "select * from latter where ocode=(select ocode from order1 where pcode=?) order by ln desc";
	final static String RCODEBY_REVIEW = "select * from latter where ln=?";
	final static String ALL_REVIEW = "select * from latter order by ln desc";
	final static String UPDATE_REVIEW = "update latter set ldate=sysdate, llatter=?, lstar=? where id=? and ln=?";
	
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
