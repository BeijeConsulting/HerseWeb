package it.beije.herse.db;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class Singleton {

	private static EntityManager entityManager = null;
	private static String path;

	private Singleton(String path) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(path);
		entityManager = entityManagerFactory.createEntityManager();

	}

	public static EntityManager createEntity(String path2) {

		if(entityManager==null || path.equals(path2)) {

			Singleton s = new Singleton(path2);
			path=path2;
			
			return entityManager;

		} else return entityManager;
	}
}