package it.beije.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.beije.bean.OrderItems;
import it.beije.bean.Orders;
import it.beije.bean.Products;
import it.beije.bean.SingletonEntityManagerFactory;
import it.beije.bean.Users;

public class OrderModel {

	private EntityManager entityManager;
	private String queryRequest;
	
	public OrderModel() {
		this.entityManager = SingletonEntityManagerFactory.newEntityManager();
	}
	
	public int placeOrder(Users users,int quantity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Orders order = new Orders();
		
		order.setId(null);
		order.setDateTime(LocalDateTime.now());
		order.setUserId(users.getId());
		order.setAmount(quantity);
		
		entityManager.persist(order);
		transaction.commit();
		
		return order.getId();
	}
	
	public void placeOrderItems(OrderItems orderItems, int orderId) {
		ProductsModel productsModel = new ProductsModel();
		List<Products> products = productsModel.getProducts();
	
		//qui piazzo l'orderItem
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		orderItems.setOrderId(orderId);

		entityManager.persist(orderItems);
		transaction.commit();
		
		//A questo punto scalo la quantita
		int itemId = orderItems.getProductId();
		double quantity = orderItems.getQuantity();
		
		for(Products p : products) {
			if(p.getId() == itemId) {
				updateAvaibleQuantity(itemId,(int)quantity);
			}
		}
	}
	
	public void updateAvaibleQuantity(int itemId, int quantity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Products product = entityManager.find(Products.class, itemId);
		int oldQuantity = product.getQuantity();
		int newQuantity = oldQuantity - quantity;
		product.setQuantity(newQuantity);
		
		
		entityManager.persist(product);
		transaction.commit();
	}
}
