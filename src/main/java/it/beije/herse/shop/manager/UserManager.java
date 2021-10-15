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

import it.beije.herse.shop.beans.*;

public class UserManager {
	
//	public static void printUsers(List<User> users) {
//		for(User u : users) System.out.println(u);
//	}
	
	public List<User> selectUsers(){
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
	
	public List<User> selectUsers(Integer id){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<User> users = new ArrayList<>();
		
		User u = manager.find(User.class, id);
		users.add(u);
		
		manager.close();
		
		return users;
	}
	
	public List<User> selectUser(String email, String password){
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		String loginQuery = "SELECT u FROM User as u WHERE email= '"+email+"' AND password= '"+password+"'";
		List<User> userList = manager.createQuery(loginQuery).getResultList();;
		
//		System.out.println("TEST PRINT");
//		for(User u : userList) System.out.println(u);
		
		manager.close();
		
		return userList;
	}
	
	private void insertUsers(List<User> users) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		for (User u : users) manager.persist(u);
		
		transaction.commit();
		manager.close();
	}
	
	public void updateUsers(String col, String colVal, Integer id) {
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
	
//	public static void printOrderHistory(Integer id) {
//		EntityManager manager = ShopEntityManager.newEntityManager();
//		
//		User u = manager.find(User.class, id);
//		
//		// Criteria Query
//		CriteriaBuilder cb = manager.getCriteriaBuilder();
//		CriteriaQuery<Order> query = cb.createQuery(Order.class);
//		Root<Order> order = query.from(Order.class);
//		query.select(order).where(order.get("userId").in(u.getId()));
//				
//		List<Order> orderHistory = manager.createQuery(query).getResultList();
//		
//		//SQL
////		String orderHistoryQuery = "SELECT o FROM Order as o WHERE userId= "+id;
////		List<Order> orderHistory = manager.createQuery(orderHistoryQuery).getResultList();
//		
//		System.out.println("USER: "+u);
//		System.out.println("ORDER: ");
//		OrderManager.printOrders(orderHistory);
//		
//		manager.close();
//	}
	
	public List<Order> getOrders(Integer userId){
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		User u = manager.find(User.class, userId);
		
		// Criteria Query
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Order> query = cb.createQuery(Order.class);
		Root<Order> order = query.from(Order.class);
		query.select(order).where(order.get("userId").in(u.getId()));
				
		List<Order> orderHistory = manager.createQuery(query).getResultList();
		
		//SQL
//		String orderHistoryQuery = "SELECT o FROM Order as o WHERE userId= "+id;
//		List<Order> orderHistory = manager.createQuery(orderHistoryQuery).getResultList();
		
		manager.close();
		
		return orderHistory;
	}
	
	public Boolean loginUser(String email, String password) {
		List<User> userList = selectUser(email, password);
		
		if(userList.size()==0) return false;
		else if(userList.size()>1) {
			System.out.println("ERROR");
			System.exit(0);
		}
		User u = userList.get(0);
		if(u.getName()!=null && u.getSurname()!=null) System.out.println("WELCOME "+u.getName()+" "+u.getSurname());
		else System.out.println("WELCOME "+u.getEmail());
//		ShopVecchia.setLoggedUser(u);
		return true;
	}
	
	public void signIn(String email, String password) {
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		List<User> uList = new ArrayList<>();
		uList.add(u);
		insertUsers(uList);
		System.out.println("SIGNED IN");
		System.out.println();
	}
}
