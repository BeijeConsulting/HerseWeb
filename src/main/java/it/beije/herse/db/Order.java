package it.beije.herse.db;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "`order`")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="creation_datetime")
	private LocalDateTime creationDateTime;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="user_id")
	private int userId;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Order() {}
	public Order(int userId, Double amount) {
	
		LocalDateTime dateTime = LocalDateTime.now();
		
		this.creationDateTime=dateTime;
		this.amount=amount;
		this.userId=userId;
		
		EntityManager entityManager = Singleton.createEntity("herse-shop");
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(this);

		transaction.commit();
		entityManager.close();
		
	}
	
	@Override
	public String toString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
		String s= creationDateTime.format(formatter);
		String s2= creationDateTime.format(formatter2);
		
		return "Ordine numero: " + id + ", del " + s + " ore "+ s2 + ". Spesa totale= " + amount + ";";
	}
	
	
	

}
