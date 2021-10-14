package it.beije.herse.shop.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.beije.herse.shop.manager.OrderManager;
import it.beije.herse.shop.manager.ProductManager;

public class Cart {
	private Order order;
	private List<OrderItem> items;
	private Integer quantities[];
	
	public Cart() {
		order = new Order();
		items = new ArrayList<OrderItem>();
		
		List<Product> p = ProductManager.selectProducts();
		quantities = new Integer[p.size()+1];
	}

	public Order getOrder() {
		return order;
	}
	public void setOrderUserId(Integer userId) {
		order.setUserId(userId);
	}
	public void setOrderDateTime(LocalDateTime dateTime) {
		order.setDateTime(dateTime);
	}
	public void confirmOrder() {
		OrderManager.insertOrder(order, items);
	}

	public List<OrderItem> getItems() {
		return items;
	}
	public Double getTotal() {
		Double total = 0.0;
		for(OrderItem i : items){
			Product p = ProductManager.selectProducts(i.getProductId()).get(0);
			total += i.getQuantity()*p.getPrice();
		}
		return total;
	}
	public void addItem(OrderItem item) {
		items.add(item);
	}
	public void removeItem(OrderItem item) {
		items.remove(item);
	}

	public Integer[] getQuantities() {
		return quantities;
	}
	public void addQuantity(Integer index, Integer param) {
		quantities[index] = param;
	}
	public void removeQuantity(Integer index) {
		quantities[index] = null;
	}
}
