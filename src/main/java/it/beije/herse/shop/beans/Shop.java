package it.beije.herse.shop.beans;

import javax.persistence.EntityManager;

public class Shop {

	public static void main(String[] args) {
		
		EntityManager manager = ShopEntityManager.newEntityManager();
		
		User user = manager.find(User.class, 1);
		
		System.out.println(user);
		
		manager.close();
	}

}
