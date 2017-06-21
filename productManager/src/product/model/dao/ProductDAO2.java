package product.model.dao;

import java.sql.*;
import java.util.ArrayList;

import static common.JDBCTemplete.*;
import product.model.vo.Product;

public class ProductDAO2 {
	public ProductDAO2(){}
	
	public Product selectOne(String id){
		Product p = null;
		Connection conn = getConnection();
		// import 뒤에 static을 붙여 실제 코드에서 메소드를 그냥 불러올 수 있다.
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(
					"SELECT * FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			if(rset != null){
				while(rset.next()){
					p = new Product(rset.getString("PRODUCT_ID"),rset.getString("P_NAME"),
							rset.getInt("PRICE"),rset.getString("DESCRIPTION"));
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
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
			rset = stmt.executeQuery("SELECT * FROM PRODUCT");
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
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(
					"INSERT INTO PRODUCT "
					+"VALUES('"+p.getProductId()+"', '"
					+p.getpName()+"', "+p.getPrice()+", '"
					+p.getDescription()+"')");
			if(result > 0) commit(conn);
			else rollback(conn);
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close(stmt);
			close(conn);
		}
		
		return result;
	}
	
	public int updateProduct(String id, int price){
		int result = 0;
		Connection conn = getConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(
					"UPDATE PRODUCT SET "
					+"PRICE = "+price
					+" WHERE PRODUCT_ID = '"+id+"'");
			if(result > 0) commit(conn);
			else rollback(conn);
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close(stmt);
			close(conn);
		}
		
		return result;
	}
	
	public int deleteProduct(String id){
		int result = 0;
		Connection conn = getConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(
					"DELETE FROM PRODUCT WHERE PRODUCT_ID = '"+id+"'");
			if(result > 0) commit(conn);
			else rollback(conn);
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close(stmt);
			close(conn);
		}
		return result;
	}
}
