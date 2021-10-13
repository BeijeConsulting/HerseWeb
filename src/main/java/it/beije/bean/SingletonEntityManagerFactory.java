package it.beije.bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonEntityManagerFactory {

	private SingletonEntityManagerFactory() {
	}

	private static EntityManagerFactory entityManagerFactory;

	public static EntityManager newEntityManager() {

		if (entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("HerseWeb");

		return entityManagerFactory.createEntityManager();
	}
}
