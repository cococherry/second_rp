package book.mvc.view;
import java.util.*;

import book.mvc.controller.BookController;
import book.mvc.model.vo.Book;

public class BookMenu {
	private Scanner sc = new Scanner(System.in);
	public BookMenu(){}
	
	public void displayMenu(){
		BookController ctr = new BookController();
		do{
			System.out.println("\n\n------------------------------");
			System.out.println("Book Management System");
			System.out.println("------------------------------");
			System.out.println("1. Insert new Book");
			System.out.println("2. Update the Book Info");
			System.out.println("3. Delete the Book Info");
			System.out.println("4. Search Book Info to ID");
			System.out.println("5. Search Book Info to Title");
			System.out.println("6. Search All Book Info");
			System.out.println("9. Exit the System");
			System.out.print("Select Menu : ");
			int no = sc.nextInt();
			switch(no){
			case 1: ctr.insertBook(inputBook()); break;
			case 2: ctr.updateBook(inputBook(new Book())); break;
			case 3: ctr.deleteBook(inputBookId()); break;
			case 4: ctr.searchBook(inputBookId()); break;
			case 5: ctr.searchBookTitle(inputBookTitle()); break;
			case 6: ctr.selectAll(); break;
			case 9: System.out.print("Are you really exit this program? (Y/N) : ");
				if(sc.next().toUpperCase().charAt(0) == 'Y') return;
				else {System.out.println("Then rechoice the right Menu number!!"); break;}
			default:
				System.out.println("Wrong Menu....\nChoose the right Answer!");
			}
		}while(true);
	}
	
	public Book inputBook(){
		sc.nextLine();// remove Enter value
		Book b = new Book();
		System.out.print("Book Title : ");
		b.setTitle(sc.nextLine());
		System.out.print("Book Author : ");
		b.setAuthor(sc.next());
		sc.nextLine(); // remove Enter value
		System.out.print("Book Publisher : ");
		b.setPublisher(sc.nextLine());
//		System.out.print("Book Publish Date (YY/MM/DD) : ");
//		sc.next(); -- Testing
		System.out.print("Book Price : ");
		b.setPrice(sc.nextInt());
		
		return b;
	}
	public Book inputBook(Book b){
		//수정도서정보 키보드 입력용
		System.out.print("Book ID for Update : ");
		b.setBookId(sc.nextInt());
//		System.out.println("Current Book Price : "+b.getPrice());
		System.out.print("Update Price : ");
		b.setPrice(sc.nextInt());
		return b;
	}
	public int inputBookId(){
		System.out.print("Input Book ID : ");
		return sc.nextInt();
	}
	public String inputBookTitle(){
		sc.nextLine(); // remove Enter value
		System.out.print("Input Book Title : ");
		return sc.nextLine();
	}
	public void displayBooks(List<Book> list){
		for(Book b : list)
			System.out.println(b);
	}
	public void displayBook(Book b){
		if(b != null) System.out.println(b.toStringOne());
		else displayError("There is no Book Info that you found");
	}
	public void displayError(String string) {
		System.out.println(string);
	}
}