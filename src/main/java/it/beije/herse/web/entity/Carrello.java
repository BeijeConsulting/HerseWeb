package it.beije.herse.web.entity;

import java.util.List;

public class Carrello {

	private List<Product> listProducts;
	private Integer quantity;

	public List<Product> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for ( Product p : listProducts) {
			builder.append("Name: ").append(p.getName()).append(", price: ").append(p.getPrice())
			.append(", quantity: ").append(p.getQuantity());
		}
		return builder.toString();
	}
}
