package it.beije.herse.shop;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
