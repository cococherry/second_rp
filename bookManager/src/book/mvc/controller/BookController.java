package book.mvc.controller;

import book.mvc.model.dao.BookDAO;
import book.mvc.model.vo.Book;
import book.mvc.view.BookMenu;

public class BookController {
	private BookMenu vBook = new BookMenu();
	private BookDAO bDAO = new BookDAO();
	public BookController(){}

	public void insertBook(Book b){
		int result = bDAO.insertBook(b);
		if(result > 0) System.out.println(result+"행의 정보 추가 완료!!");
		else vBook.displayError("값 추가에 실패하였습니다.");
	}
	
	public void updateBook(Book b){
		int result = bDAO.updateBook(b);
		if(result > 0) System.out.println(result+"행의 정보 수정 완료!!");
		else vBook.displayError("값 수정에 실패하였습니다.");
	}
	
	public void deleteBook(int bookId){
		int result = bDAO.deleteBook(bookId);
		if(result > 0) System.out.println(result+"행의 정보 삭제 완료!!");
		else vBook.displayError("값 삭제에 실패하였습니다.");
	}
	
	public void searchBookTitle(String bookTitle){
		vBook.displayBooks(bDAO.searchBookTitle(bookTitle));
	}
	
	public void searchBook(int bookId){
		vBook.displayBook(bDAO.selectBook(bookId));
	}

	public void selectAll(){
		vBook.displayBooks(bDAO.selectAllBooks());
	}
}