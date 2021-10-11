package Ecommerce;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Shop {
	
	final static EntityManager manager = ShopEntityManager.newEntityManager();
	
	public static boolean existUser(int userId) {
		
		User user = manager.find(User.class, userId);
		if(user != null) {
//			new Shop().setUserId(userId);
//			new Shop().createOrder(userId);
			return true;
		} else {
			return false;
		}
	}
	
	public static List<Product> getProducts(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		
		Root<Product> productRoot = criteriaQuery.from(Product.class);
		
//		criteriaQuery.select(productRoot.get("id"));
		
		CriteriaQuery<Product> select = criteriaQuery.select(productRoot);
		TypedQuery<Product> query = manager.createQuery(select);
		
		return query.getResultList();
	}
	
	public static boolean checkQuantity(int productId, int quantity) {
//		Order order= new Order();
		
		Product product = manager.find(Product.class, productId);
		
		if(product.getQuantity() < quantity) {
			System.out.println("Quantità maggiore rispetto a quella disponibile");
			return false;
		} else {
			
			System.out.println(product);
			
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			product.setQuantity(product.getQuantity()-quantity); //settatto la quantità nuova di product
			transaction.commit();
			
//			insertOrderItem(new Shop().getOrderId(), productId, product.getQuantity(), quantity);
			
			return true;
		}
	}
	
	public static int insertOrder(int userId) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Order order = new Order();
		order.setUserId(userId);
		order.setAmount(0.0);
		order.setDateTime(LocalDateTime.now());
		manager.persist(order);
		transaction.commit();
		
		return order.getId();
	}
	
	public static void insertOrderItem(int order, int product, double price, int quantity) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		OrderItem orderItem = new OrderItem();
		System.out.println(order);
		orderItem.setOrderId(order);
		orderItem.setProductId(product);
		orderItem.setSellPrice(price);
		orderItem.setQuantity(quantity);
		manager.persist(orderItem);
		transaction.commit();
	}
	
//	public static Order changeOrder() {
////		int orderId = new Shop().getOrderId();
//		
////		String selectP = "SELECT p FROM OrderItem as p WHERE orderId=" + orderId;
////		Query query = manager.createQuery(selectP);
////		List<OrderItem> orders =  query.getResultList();
//		
//		Double total = 0.0;
//		
////		for(OrderItem o: orders) {
////			System.out.println(o);
////			total += o.getSellPrice() * o.getQuantity();
////		}
//		
//		EntityTransaction transaction = manager.getTransaction();
//		transaction.begin();
////		manager.find(Order.class, orderId).setAmount(total);
//		transaction.commit();
//		
////		Order order = manager.find(Order.class, orderId);
//		
////		return order;
//	}
	

}
