package it.beije.herse.shop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ManagerCRUD {
	
	private EntityManager manager = ShopEntityManager.newEntityManager();
	private EntityTransaction t = manager.getTransaction();

	public List<Object> select(String query) {	
		return (manager.createQuery(query)).getResultList();	
	}
	
	public Object selectByID(Class<?> typeObject, Integer id) {
		return manager.find(typeObject, id);
	}
	
	public void insert(Object o) {
		manager.persist(o);
	}
	
	public void begin() {
		t.begin();
	}
	
	public void commit() {
		t.commit();
	}
	
	public void close() {
		manager.close();
	}
	
	public void open() {
		manager = ShopEntityManager.newEntityManager();
	}
	
	public boolean isOpen() {
		return manager.isOpen();
	}
	
}
