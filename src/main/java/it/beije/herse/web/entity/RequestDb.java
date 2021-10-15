package it.beije.herse.web.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class RequestDb {
	
	 public List<Product> selectProducts() {
	    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
			EntityManager entityManager = entityManagerFactory.createEntityManager();

			List<Product> products = new ArrayList<Product>();
			
			String productSelect = "SELECT p FROM Product AS p";
			Query query = entityManager.createQuery(productSelect);
			products = query.getResultList();
			
			entityManager.close();
			return products;
	    }
}
