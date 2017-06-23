package common;
import java.sql.*;
import java.util.*;
import java.io.*;

// Singleton 디자인 패턴
// 프로그램이 실행되는 내내 해당 클래스에 대한 객체를 한 개만
// 만들어서 사용하도록 처리하는 방식
// 해당 클래스에 모든 메소드를 Static 메소드로 작성한다!
public class JDBCTemplete {
	//private JDBCTemplete(){}

	public static Connection getConnection(){
		Connection conn = null;
		Properties prop = new Properties();
		try {
			prop.load(new BufferedReader(new FileReader("driver.properties")));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(
					prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("pwd"));
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","student","student");*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection c){
		try {
			if(!c.isClosed())
				c.close();
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		try {
			if(!stmt.isClosed())
				stmt.close();
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset){
		try {
			if(!rset.isClosed())
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con){	
		try {
			if(!con.isClosed())
				con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con){	
		try {
			if(!con.isClosed())
				con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
