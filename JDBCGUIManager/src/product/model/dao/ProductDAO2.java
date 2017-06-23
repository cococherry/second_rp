package product.model.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import static common.JDBCTemplete.*;
import product.model.vo.Product;

public class ProductDAO2 {
	private Properties prop;
	public ProductDAO2(){
		prop = new Properties();
		try {
			prop.load(new BufferedReader(new FileReader("query.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Product selectID(String id){
		Product p = null;
		Connection conn = getConnection();
		// import 뒤에 static을 붙여 실제 코드에서 메소드를 그냥 불러올 수 있다.
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(
//					"SELECT * FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			pstmt = conn.prepareStatement(prop.getProperty("selectID"));
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()){
					p = new Product(rset.getString("PRODUCT_ID"),rset.getString("P_NAME"),
							rset.getInt("PRICE"),rset.getString("DESCRIPTION"));
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close(rset);
			//close(stmt);
			close(pstmt);
			close(conn);
		}
		return p;
	}
	
	public Product selectName(String name){
		Product p = null;
		Connection conn = getConnection();
		// import 뒤에 static을 붙여 실제 코드에서 메소드를 그냥 불러올 수 있다.
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(
//					"SELECT * FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			pstmt = conn.prepareStatement(prop.getProperty("selectName"));
			pstmt.setString(1, name);
			rset = pstmt.executeQuery();
			if(rset.next()){
					p = new Product(rset.getString("PRODUCT_ID"),rset.getString("P_NAME"),
							rset.getInt("PRICE"),rset.getString("DESCRIPTION"));
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close(rset);
			//close(stmt);
			close(pstmt);
			close(conn);
		}
		return p;
	}
	
	public ArrayList<Product> selectAll(){
		ArrayList<Product> pList = new ArrayList<Product>();
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(prop.getProperty("selectAll"));
			if(rset != null){
				while(rset.next()){
					pList.add(new Product(rset.getString("PRODUCT_ID"),rset.getString("P_NAME"),
							rset.getInt("PRICE"),rset.getString("DESCRIPTION")));
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(conn);
		}
		return pList;
	}
	
	public int insertProduct(Product p){
		int result = 0;
		Connection conn = getConnection();
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("insert"));
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getpName());
			pstmt.setInt(3, p.getPrice());
			pstmt.setString(4, p.getDescription());
			result = pstmt.executeUpdate();
			//stmt = conn.createStatement();
//			result = stmt.executeUpdate(
//					"INSERT INTO PRODUCT "
//					+"VALUES('"+p.getProductId()+"', '"
//					+p.getpName()+"', "+p.getPrice()+", '"
//					+p.getDescription()+"')");
			if(result > 0) commit(conn);
			else rollback(conn);
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			//close(stmt);
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	public int updateProduct(Product p){
		int result = 0;
		Connection conn = getConnection();
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
//			stmt = conn.createStatement();
//			result = stmt.executeUpdate(
//					"UPDATE PRODUCT SET "
//					+"PRICE = "+price
//					+" WHERE PRODUCT_ID = '"+id+"'");
			pstmt = conn.prepareStatement(prop.getProperty("update"));
			pstmt.setInt(1, p.getPrice());
			pstmt.setString(2, p.getpName());
			pstmt.setString(3, p.getDescription());
			pstmt.setString(4, p.getProductId());
			
			result = pstmt.executeUpdate();
			if(result > 0) commit(conn);
			else rollback(conn);
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			//close(stmt);
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	public int deleteProduct(String id){
		int result = 0;
		Connection conn = getConnection();
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			//stmt = conn.createStatement();
//			result = stmt.executeUpdate(
//					"DELETE FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			pstmt = conn.prepareStatement(prop.getProperty("delete"));
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			if(result > 0) commit(conn);
			else rollback(conn);
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			//close(stmt);
			close(pstmt);
			close(conn);
		}
		return result;
	}
}
