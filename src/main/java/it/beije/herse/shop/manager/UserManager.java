package it.beije.herse.shop.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import it.beije.herse.shop.classes.*;

public class UserManager {
	
	public static void printUsers(List<User> users) {
		for(User u : users) System.out.println(u);
	}
	
	public static List<User> selectUsers(){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<User> users = new ArrayList<>();
		
		// Criteria Query
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> user = query.from(User.class);
		query.select(user);
		
		users = manager.createQuery(query).getResultList();
		
		// SQL
//		int id=1;
//		User u = manager.find(User.class, id);
//		while(u!=null) {
//			users.add(u);
//			u = manager.find(User.class, ++id);
//		}
//		Query selectAllQuery = manager.createQuery("SELECT u FROM User as u");
//		users = selectAllQuery.getResultList();
		
		manager.close();
		
		return users;
	}
	
	public static List<User> selectUsers(Integer id){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<User> users = new ArrayList<>();
		
		User u = manager.find(User.class, id);
		users.add(u);
		
		manager.close();
		
		return users;
	}
	
	public static void insertUsers(List<User> users) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		for (User u : users) manager.persist(u);
		
		transaction.commit();
		manager.close();
	}
	
	public static void updateUsers(String col, String colVal, Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		User u = manager.find(User.class, id);
		switch(col.toUpperCase()) {
		case "NAME":
			u.setName(colVal);
			break;
		case "SURNAME":
			u.setSurname(colVal);
			break;
		case "EMAIL":
			u.setEmail(colVal);
			break;
		case "PASSWORD":
			u.setPassword(colVal);
			break;
		default:
			System.out.println("No columns found");
			break;
		}
		manager.persist(u);
		
		transaction.commit();
		manager.close();
	}
	
	public static void printOrderHistory(Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		User u = manager.find(User.class, id);
		
		// Criteria Query
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Order> query = cb.createQuery(Order.class);
		Root<Order> order = query.from(Order.class);
		query.select(order).where(order.get("userId").in(u.getId()));
				
		List<Order> orderHistory = manager.createQuery(query).getResultList();
		
		//SQL
//		String orderHistoryQuery = "SELECT o FROM Order as o WHERE userId= "+id;
//		List<Order> orderHistory = manager.createQuery(orderHistoryQuery).getResultList();
		
		System.out.println("USER: "+u);
		System.out.println("ORDER: ");
		OrderManager.printOrders(orderHistory);
		
		manager.close();
	}
	
	public static void printJoin(Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		User u = manager.find(User.class, id);
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
		Root<Order> order = query.from(Order.class);
		Root<Product> product = query.from(Product.class);
		Root<User> user = query.from(User.class);
		Root<OrderItem> item = query.from(OrderItem.class);
		//select *
		query.multiselect(order, user, product, item);
		
		//from ((user as u join `order` as o on u.id=o.user_id) 
		//join order_item as i on i.order_id = o.id) 
		//join product as p on p.id=i.product_id
		query.where(cb.equal(user.get("id"), order.get("userId")),
						cb.equal(order.get("id"), item.get("orderId")),
						cb.equal(product.get("id"), item.get("productId")));
		
		query.orderBy(cb.asc(user.get("id")), cb.asc(order.get("id")), cb.asc(item.get("id")), cb.asc(product.get("id")));
//		query.groupBy(user, order);
		
		List<Tuple> l = manager.createQuery(query).getResultList();
		for(Tuple c : l) {
			
			System.out.println("USER: "+c.get(1));
			System.out.println("\tORDER: "+c.get(0));
			System.out.println("\t\tITEM: "+c.get(3));
			System.out.println("\t\tPRODUCT: "+c.get(2));
		}
	}
	
	public static List<User> selectUser(String email, String password){
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		String loginQuery = "SELECT u FROM User as u WHERE email= '"+email+"' AND password= '"+password+"'";
		List<User> userList = manager.createQuery(loginQuery).getResultList();;
		
//		System.out.println("TEST PRINT");
//		for(User u : userList) System.out.println(u);
		
		manager.close();
		
		return userList;
	}
	
	public static Boolean loginUser(String email, String password) {
		List<User> userList = selectUser(email, password);
		
		if(userList.size()==0) return false;
		else if(userList.size()>1) {
			System.out.println("ERROR");
			System.exit(0);
		}
		User u = userList.get(0);
		if(u.getName()!=null && u.getSurname()!=null) System.out.println("WELCOME "+u.getName()+" "+u.getSurname());
		else System.out.println("WELCOME "+u.getEmail());
		ShopVecchia.setLoggedUser(u);
		return true;
	}
	
	public static void signIn(String email, String password) {
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		List<User> uList = new ArrayList<>();
		uList.add(u);
		UserManager.insertUsers(uList);
		System.out.println("SIGNED IN");
		System.out.println();
	}
	
	public static void main(String... args) {
		printJoin(0);
	}
}
