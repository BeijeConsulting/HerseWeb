package ShopSession;

import Ecommerce.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
	
	public static List<Product> getProductsById(int productId){
		String selectP = "SELECT p FROM Product as p WHERE id=" + productId;
		Query query = manager.createQuery(selectP);
		List<Product> products =  query.getResultList();
		return products;
	}
	
	public static boolean checkQuantity(int productId, int quantity, int userId) {
		int orderId=  new Shop().insertOrder(userId);
		
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
			
			//insertOrderItem(orderId, productId, product.getQuantity(), quantity);
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
	
	public static void insertOrderItem(int order, int product, int quantity) {
		EntityTransaction transaction = manager.getTransaction();
		Product prod = manager.find(Product.class, product);
		transaction.begin();
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(order);
		orderItem.setProductId(product);
		orderItem.setSellPrice(prod.getPrice());
		orderItem.setQuantity(quantity);
		manager.persist(orderItem);
		transaction.commit();
	}
	
	public static Order changeOrder(int orderId) {
		String selectP = "SELECT p FROM OrderItem as p WHERE orderId=" + orderId;
		Query query = manager.createQuery(selectP);
		List<OrderItem> orders =  query.getResultList();
		
		Double total = 0.0;
		
		for(OrderItem o: orders) {
			System.out.println(o);
			total += o.getSellPrice() * o.getQuantity();
		}
		
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.find(Order.class, orderId).setAmount(total);
		transaction.commit();
		
		Order order = manager.find(Order.class, orderId);
		
		return order;
	}
	
	public static List<OrderItem> findOrderItem(int orderId){
		String selectP = "SELECT o FROM OrderItem as o WHERE orderId=" + orderId;
		Query query = manager.createQuery(selectP);
		List<OrderItem> orders =  query.getResultList();
		return orders;
	}
	
	public static int checkCredential(String email, String password) {
		String selectU = "SELECT u FROM User as u"; // WHERE email=" + email;
		Query query = manager.createQuery(selectU);
		List<User> user =  query.getResultList();
		
		for(User u: user) {
			if(u.getPassword().equals(password) && u.getEmail().equalsIgnoreCase(email)) {
				return u.getId();
			} 
		}
		return 0;
	}
	
	
	
}
