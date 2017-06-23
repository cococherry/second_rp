package product.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import product.model.dao.*;
import product.model.vo.Product;

public class ProductController {
	public ProductController(){}
	
	public Product selectID(String id){
		Product p = new ProductDAO2().selectID(id);
		if(p == null) errors(-1,"데이터를 정상적으로 조회하지 못했습니다.");
		return p;
	}
	
	public Product selectName(String name){
		Product p = new ProductDAO2().selectName(name);
		if(p == null) errors(-1,"데이터를 정상적으로 조회하지 못했습니다.");
		return p;
	}
	
	public ArrayList<Product> selectAll(){
		ArrayList<Product> pList = new ProductDAO2().selectAll();
		if(pList == null) errors(-1,"데이터를 정상적으로 조회하지 못했습니다.");
		return pList;
	}
	
	public int insertProduct(Product p){
		int result = new ProductDAO2().insertProduct(p);
		errors(result, "데이터를 정상적으로 추가하지 못했습니다.");
		return result;
	}
	
	public int updateProduct(Product p){
		int result = new ProductDAO2().updateProduct(p);
		errors(result, "데이터를 정상적으로 수정하지 못했습니다.");
		return result;
	}
	
	public int deleteProduct(String id){
		int result = new ProductDAO2().deleteProduct(id);
		errors(result, "데이터를 정상적으로 삭제하지 못했습니다.");
		return result;
	}
	
	public Object[][] tableRow(ArrayList<Product> pList){
		Object[][] aRow = null;
		if(pList != null){
			aRow = new Object[pList.size()][4];
			
			for(int i = 0 ; i < aRow.length ; i++){
				//System.out.println(pList.get(i));
				aRow[i][0] = pList.get(i).getProductId();
				aRow[i][1] = pList.get(i).getpName();
				aRow[i][2] = pList.get(i).getPrice();
				aRow[i][3] = pList.get(i).getDescription();
			}
			
			
			
			/*int i = 0;
			for(Product p : pList){
				int j = 0;
				Object[] tmp = p.toString().split(", ");
				aRow[i][j] = tmp[j++];
				aRow[i][j] = tmp[j++];
				aRow[i][j] = tmp[j++];
				aRow[i][j] = tmp[j];
				aRow[i][0] = p.getProductId();
				aRow[i][1] = p.getpName();
				aRow[i][2] = p.getPrice();
				aRow[i++][3] = p.getDescription();
			}*/
		}
		return aRow;
	}
	
	public Object[][] tableRow(Product p){
		Object[][] aRow = null;
		if(p != null){
			aRow = new Object[1][4];
			aRow[0][0] = p.getProductId();
			aRow[0][1] = p.getpName();
			aRow[0][2] = p.getPrice();
			aRow[0][3] = p.getDescription();
		}
		return aRow;
	}
	
	// 오버로딩한 에러로만 가져 오자!!
	public void errors(int result, String msg){
		if(result > 0) JOptionPane.showMessageDialog(null, "데이터 처리 완료!!!");
		else JOptionPane.showMessageDialog(null, msg);
	}
	public void errors(int a){
		if(a > 0) System.out.println(a+"행의 추가/수정/삭제에 성공하였습니다.");
		else System.out.println("실패하였습니다.");
	}
	public void errors(Object a){
		if(a != null) System.out.println("성공하였습니다.");
		else System.out.println("실패하였습니다.");
	}
}