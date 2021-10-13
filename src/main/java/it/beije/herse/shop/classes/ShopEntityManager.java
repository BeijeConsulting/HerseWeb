package it.beije.herse.shop.classes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ShopEntityManager {
	
	private ShopEntityManager() {}
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager newEntityManager() {
		if (entityManagerFactory == null) 
			entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		
		return entityManagerFactory.createEntityManager();
	}

}
