package it.beije.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.bean.SingletonEntityManagerFactory;
import it.beije.bean.Users;

public class UserModel {
	private EntityManager entityManager;
	private String queryRequest;
	
	public UserModel() {
		this.entityManager = SingletonEntityManagerFactory.newEntityManager();
	}
	
	public List<Users> getUsers() {
		this.queryRequest = "SELECT x FROM Users as x";
		Query query = entityManager.createQuery(queryRequest);
		List<Users> result = query.getResultList();
		return result;
	}
	
	public boolean existUser(Users user) {
		boolean exist = false;
		List<Users> users = getUsers();
		for (Users u : users) {
			if(u.getEmail().equalsIgnoreCase(user.getEmail())&&u.getPassword().equals(user.getPassword())) {
				exist = true;
				return exist;
			}
		}
		
		return exist;
	}

	public void createUser(Users user) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(user);
		
		transaction.commit();
	}
	
}
