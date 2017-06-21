package product.model.dao;

import java.sql.*;
import java.util.ArrayList;

import product.model.vo.Product;

public class ProductDAO {
	public ProductDAO(){}
	
	public Product selectOne(String id){
		Product p = null;
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(
//					"SELECT * FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			String query = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset != null){
				while(rset.next()){
					p = new Product(rset.getString("PRODUCT_ID"),rset.getString("P_NAME"),
							rset.getInt("PRICE"),rset.getString("DESCRIPTION"));
				}
			}
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return p;
	}
	
	public ArrayList<Product> selectAll(){
		ArrayList<Product> pList = new ArrayList<Product>();
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery("SELECT * FROM PRODUCT");
			pstmt = conn.prepareStatement("SELECT * FROM PRODUCT");
			rset = pstmt.executeQuery();
			if(rset != null){
				while(rset.next()){
					pList.add(new Product(rset.getString("PRODUCT_ID"),rset.getString("P_NAME"),
							rset.getInt("PRICE"),rset.getString("DESCRIPTION")));
				}
			}
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return pList;
	}
	
	public int insertProduct(Product p){
		int result = 0;
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement prestmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
			//stmt = conn.createStatement();
//			result = stmt.executeUpdate(
//			"INSERT INTO PRODUCT "
//			+"VALUES('"+p.getProductId()+"', '"
//			+p.getpName()+"', "+p.getPrice()+", '"
//			+p.getDescription()+"')");
			
			String query = "INSERT INTO PRODUCT VALUES(?, ?, ?, ?)";
			prestmt = conn.prepareStatement(query);
			// 객체 생성 후에 쿼리 완성
			prestmt.setString(1, p.getProductId());
			prestmt.setString(2, p.getpName());
			prestmt.setInt(3, p.getPrice());
			prestmt.setString(4, p.getDescription());
			
			// 실행은 이거 딸랑 하나
			result = prestmt.executeUpdate();
			
			if(result > 0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				prestmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int updateProduct(String id, int price){
		int result = 0;
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
			//stmt = conn.createStatement();
//			result = stmt.executeUpdate(
//					"UPDATE PRODUCT SET "
//					+"PRICE = "+price
//					+" WHERE PRODUCT_ID = '"+id+"'");
			pstmt = conn.prepareStatement("UPDATE PRODUCT SET PRICE = ? WHERE PRODUCT_ID = ?");
			pstmt.setInt(1, price);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			
			if(result > 0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteProduct(String id){
		int result = 0;
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe",
							"student", "student");
//			stmt = conn.createStatement();
//			result = stmt.executeUpdate(
//					"DELETE FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			pstmt = conn.prepareStatement("DELETE FROM PRODUCT WHERE PRODUCT_ID = ?");
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			if(result > 0) conn.commit();
			else conn.rollback();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				//stmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return result;
	}
}