package it.beije.herse.web.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class RequestDb {

	public Order insertOrder(int idUser) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Order order = new Order();
		order.setDateTime(LocalDateTime.now());
		order.setUserId(idUser);
		order.setAmount(new Double(0));
//		for (Map.Entry<Integer, Integer> product : products.entrySet()) {
//			System.out.println(product);
//	        insertOrderItem(order.getId(), product.getKey(), product.getValue());
//	    }

		entityManager.persist(order);
		transaction.commit();
		entityManager.close();
		return order;
	}
}
