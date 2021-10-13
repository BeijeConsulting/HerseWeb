package it.beije.herse.shop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class MyShop {

	public static ManagerCRUD manager = new ManagerCRUD();

	public static User getUser(String email, String password) {

		User user = null;
		String query = "SELECT U FROM User as U WHERE email = '" + email + "' AND password = '" + password + "'";
		
		try {
			user = (User)manager.select(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Email o password errate");
		}

		return user;
		
	}
	
	public static Product getProduct(Integer id) {

		Product prod = null;
		String query = "SELECT P FROM Product as P WHERE id = " + id;
		
		try {
			prod = (Product)manager.select(query).get(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Email o password errate");
		}

		return prod;
		
	}

	public static List<Product> getProducts() {

		String query = "SELECT P FROM Product as P";

		List<Object> orders = manager.select(query);
		List<Product> list = new ArrayList<>();

		for(Object o : orders)
			list.add((Product)o);

		return list;

	}

	public static LocalDateTime getDateTime() {
		DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		return LocalDateTime.parse(LocalDateTime.now().format(f), f);
	}

	public static Order initOrder(Integer userId) {
		
		Order o = new Order();
		o.setUserId(userId);
		o.setDateTime(getDateTime());
		o.setAmount(0.0);
		
		return o;
		
	}

	public static OrderItem setOrderItem(Integer prodId, Integer qta, Double sellPrice, Integer orderId) {

		OrderItem item = new OrderItem();
		item.setProductId(prodId);
		item.setQuantity(qta);
		item.setSellPrice(sellPrice);
		item.setOrderId(orderId);

		return item;

	}
	
	public static OrderItem setOrderItem(Integer prodId, Integer qta, Double sellPrice) {

		OrderItem item = new OrderItem();
		item.setProductId(prodId);
		item.setQuantity(qta);
		item.setSellPrice(sellPrice);

		return item;

	}
	
	public static OrderItem setOrderItem(Integer prodId, Double sellPrice) {

		OrderItem item = new OrderItem();
		item.setProductId(prodId);
		item.setSellPrice(sellPrice);
		item.setQuantity(1);

		return item;

	}

	public static Product searchOrderId(String prodId, List<Product> prod) {

		for(Product p : prod)
			if(p.getId().equals(Integer.valueOf(prodId)))
				return p;

		return null;
		
	}

	public static boolean validateQta(String qta, Product p) {
		
		boolean flag = false;
		
		try {
			if(Double.valueOf(qta) > 0 && Double.valueOf(qta) <= p.getQuantity())
				flag = true;
		}catch(NumberFormatException e) {
			System.out.println("Valore non valido");
		}
		
		return flag;
		
	}
	
	public static Double calcAmountOrder(List<OrderItem> items) {
		
		Double amount = 0.0;
		
		for(OrderItem item : items)
			amount += item.getSellPrice();
		
		return amount;
		
	}

	public static List<OrderItem> setListOrderItemId(List<OrderItem> items, Integer orderId) {
		
		for(OrderItem item : items)
			item.setOrderId(orderId);
		
		return items;
		
	}

}
