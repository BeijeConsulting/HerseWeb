package it.beije.herse.web.entity;

import java.util.HashMap;
import java.util.List;

public class Carrello {

//	private List<Product> listProducts;
//	private Integer quantity;
	private HashMap<Product, Integer> carrello = new HashMap<>();

	public HashMap<Product, Integer> getCarrello() {
		return carrello;
	}

	public void setCarrello(HashMap<Product, Integer> carrello) {
		this.carrello = carrello;
	}

		

//	public List<Product> getListProducts() {
//		return listProducts;
//	}
//
//	public void setListProducts(List<Product> listProducts) {
//		this.listProducts = listProducts;
//	}
//	
//	public Integer getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}

	
}
