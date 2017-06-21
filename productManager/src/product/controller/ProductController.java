package product.controller;

import java.util.ArrayList;

import product.model.dao.*;
import product.model.vo.Product;

public class ProductController {
	public ProductController(){}
	
	public Product selectOne(String id){
		Product p = new ProductDAO2().selectOne(id);
		if(p != null) System.out.println("데이터 조회에 성공하였습니다.");
		else  System.out.println("데이터 조회에 실패하였습니다.");
		return p;
	}
	
	public ArrayList<Product> selectAll(){
		ArrayList<Product> pList = new ProductDAO2().selectAll();
		if(pList != null) System.out.println("데이터 조회에 성공하였습니다.");
		else  System.out.println("데이터 조회에 실패하였습니다.");
		return pList;
	}
	
	public int insertProduct(Product p){
		int result = new ProductDAO2().insertProduct(p);

		errors(result);
		return result;
	}
	
	public int updateProduct(Product p){
		int result = new ProductDAO2().updateProduct(p.getProductId(), p.getPrice());
		
		errors(result);
		return result;
	}
	
	public int deleteProduct(String id){
		int result = new ProductDAO2().deleteProduct(id);
		
		errors(result);
		return result;
	}
	
	// 오버로딩한 에러로만 가져 오자!!
	public void errors(int a){
		if(a > 0) System.out.println(a+"행의 추가/수정/삭제에 성공하였습니다.");
		else System.out.println("실패하였습니다.");
	}
	public void errors(Object a){
		if(a != null) System.out.println("성공하였습니다.");
		else System.out.println("실패하였습니다.");
	}
}