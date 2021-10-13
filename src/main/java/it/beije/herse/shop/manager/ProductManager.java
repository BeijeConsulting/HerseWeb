package it.beije.herse.shop.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.beije.herse.shop.classes.*;

public class ProductManager {
	
	public static void printProducts(List<Product> products) {
		for(Product p : products) System.out.println(p);
	}
	
	public static List<Product> selectProducts(){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<Product> products = new ArrayList<>();
		
		// Criteria Query
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Product> query = cb.createQuery(Product.class);
		Root<Product> prod = query.from(Product.class);
		query.select(prod);
				
		products = manager.createQuery(query).getResultList();
				
		// SQL
//		int id=1;
//		Product p = manager.find(Product.class, id);
//		while(p!=null) {
//			products.add(p);
//			p = manager.find(Product.class, ++id);
//		}
//		Query selectAllQuery = manager.createQuery("SELECT p FROM Product as p");
//		products = selectAllQuery.getResultList();
				
		manager.close();
		
		return products;
	}
	
	public static List<Product> selectProducts(Integer id){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<Product> products = new ArrayList<>();
		
		Product p = manager.find(Product.class, id);
		products.add(p);
		
		manager.close();
		
		return products;
	}
	
	public static void insertProducts(List<Product> products) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		for (Product p : products) manager.persist(p);
		
		transaction.commit();
		manager.close();
	}
	
	public static void updateProducts(String col, String colVal, Integer id) {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Product p = manager.find(Product.class, id);
		switch(col.toUpperCase()) {
		case "NAME":
			p.setName(colVal);
			break;
		case "DESCRIPTION":
			p.setDescription(colVal);
			break;
		case "PRICE":
			p.setPrice(Double.valueOf(colVal));
			break;
		case "QUANTITY":
			p.setQuantity(Integer.valueOf(colVal));
			break;
		default:
			System.out.println("No columns found");
			break;
		}
		manager.persist(p);
		
		transaction.commit();
		manager.close();
	}
}
