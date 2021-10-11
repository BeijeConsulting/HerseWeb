package it.beije.herse.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name = "`user`")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="email")
	private String email;

	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="password")
	private String password;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public User() {}
	public User(String email,String name,String surname, String password) {

		this.email=email;
		this.name=name;
		this.surname=surname;
		this.password=password;
		
		
		EntityManager entityManager = Singleton.createEntity("herse-shop");
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(this);

		transaction.commit();
		entityManager.close();
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", surname=" + surname + ", password="
				+ password + "]";
	}
	
	
	


}
