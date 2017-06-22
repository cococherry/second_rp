package common;
import java.sql.*;
import java.util.*;
import java.io.*;

public class JDBCTemp {
	//public JDBCTemp(){}
	
	public static Connection getConnect(){
		Properties prop = new Properties();
		Connection con = null;
		try {
			prop.load(new BufferedReader(new FileReader("driver.properties")));
			Class.forName(prop.getProperty("driver"));
			con = DriverManager.getConnection(
					prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pwd"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection c){
		try {
			if(!c.isClosed())
				c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement s){
		try {
			if(!s.isClosed())
				s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet r){
		try {
			if(!r.isClosed())
				r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection c){
		try {
			if(!c.isClosed())
				c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection c){
		try {
			if(!c.isClosed())
				c.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
