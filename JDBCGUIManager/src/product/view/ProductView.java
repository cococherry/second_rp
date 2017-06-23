package product.view;

import java.util.ArrayList;
import java.util.Scanner;

import product.controller.ProductController;
import product.model.vo.Product;

public class ProductView {
	private Scanner sc = new Scanner(System.in);
	
	public ProductView(){}
	
	public void displayMenu() {
		int no;
		ProductController controller = new ProductController();
		
		do{
			System.out.println("\n상품 관리 프로그램\n");
			System.out.println("1. 전체 조회");
			System.out.println("2. 상품 추가");
			System.out.println("3. 상품 수정");
			System.out.println("4. 상품 삭제");
			System.out.println("5. 상품 검색");
			System.out.println("6. 끝내기");
			System.out.print("메뉴 선택 : ");
			no = sc.nextInt();
			switch(no){
			case 1: printAll(controller.selectAll()); break;
			case 2: controller.insertProduct(insertP()); break;
			case 3: controller.updateProduct(updateP()); break;
			case 4: controller.deleteProduct(deleteP()); break;
			case 5: printOne(controller.selectID(inputId())); break;
			case 6: System.out.print("정말 종료하실 건가여? (Y/N) : ");
				if(sc.next().toUpperCase().charAt(0) == 'Y') return;
				else break;
			default: System.out.println("잘못 선택하셨습니다.\n메뉴를 다시 불러옵니다.");
			}
		}while(true);
	}
	
	private String inputId() {
		System.out.print("검색할 ID : ");
		return sc.next();
	}

	public void printOne(Product p){
		System.out.println(p.printOne());
	}
	
	public void printAll(ArrayList<Product> pList){
		for(Product p : pList)
			System.out.println(p);
	}
	
	public Product insertP(){
		Product p = new Product();
		
		System.out.print("상품 ID : ");
		p.setProductId(sc.next());
		System.out.print("상품 명 : ");
		p.setpName(sc.next());
		System.out.print("상품 가격 : ");
		p.setPrice(sc.nextInt());
		sc.nextLine(); // 엔터값 제거
		System.out.print("상품 설명 : ");
		p.setDescription(sc.nextLine());
		
		return p;
	}
	
	public Product updateP(){
		Product p = new Product();
		
		System.out.print("수정할 상품 ID : ");
		p.setProductId(sc.next());
		System.out.print("수정할 상품 가격 : ");
		p.setPrice(sc.nextInt());
		
		return p;
	}
	
	public String deleteP(){
		System.out.print("삭제할 상품 ID : ");
		return sc.next();
	}
}