package book.mvc.model.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import static common.JDBCTemp.*;
import book.mvc.model.vo.Book;

public class BookDAO {
	private Properties prop;
	public BookDAO(){
		prop = new Properties();
		try {
			prop.load(new BufferedReader(new FileReader("query.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertBook(Book b){
		int result = 0;
		Connection con = getConnect();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(prop.getProperty("insert"));
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getPrice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return result;
	}
	
	public int updateBook(Book b){
		int result = 0;
		Connection con = getConnect();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(prop.getProperty("update"));
			pstmt.setInt(1, b.getPrice());
			pstmt.setInt(2, b.getBookId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return result;
	}
	
	
	public int deleteBook(int bid){
		int result = 0;
		Connection con = getConnect();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(prop.getProperty("delete"));
			pstmt.setInt(1, bid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return result;
	}

	public ArrayList<Book> selectAllBooks() {
		ArrayList<Book> bList = new ArrayList<Book>();
		Connection con = getConnect();
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(prop.getProperty("selectAll"));
			if(rset != null){
				while(rset.next()){
					bList.add(new Book(rset.getInt("BOOK_ID"),
							rset.getString("TITLE"),
							rset.getString("AUTHOR"),
							rset.getString("PUBLISHER"),
							rset.getDate("PUBLISH_DATE"),
							rset.getInt("PRICE")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(con);
		}
		return bList;
	}
	
	public ArrayList<Book> searchBookTitle(String bookTitle){
		ArrayList<Book> bList = new ArrayList<Book>();
		Connection con = getConnect();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = con.prepareStatement(prop.getProperty("selectTitle"));
			pstmt.setString(1, bookTitle);
			rset = pstmt.executeQuery();
			if(rset != null){
				while(rset.next()){
					bList.add(new Book(rset.getInt("BOOK_ID"),
							rset.getString("TITLE"),
							rset.getString("AUTHOR"),
							rset.getString("PUBLISHER"),
							rset.getDate("PUBLISH_DATE"),
							rset.getInt("PRICE")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(con);
		}
		return bList;
	}
	
	public Book selectBook(int bookId){
		Connection con = getConnect();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Book b = null;
		
		try {
			pstmt = con.prepareStatement(prop.getProperty("selectId"));
			pstmt.setInt(1, bookId);
			rset = pstmt.executeQuery();
			if(rset.next()){
					b = new Book(rset.getInt("BOOK_ID"),
						rset.getString("TITLE"),
						rset.getString("AUTHOR"),
						rset.getString("PUBLISHER"),
						rset.getDate("PUBLISH_DATE"),
						rset.getInt("PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(con);
		}
		return b;
	}
}
