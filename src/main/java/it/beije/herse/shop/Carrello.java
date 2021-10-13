package it.beije.herse.shop;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
	
	private List<OrderItem> items = new ArrayList<>();

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(OrderItem item) {
		this.items.add(item);
	}
	
}
