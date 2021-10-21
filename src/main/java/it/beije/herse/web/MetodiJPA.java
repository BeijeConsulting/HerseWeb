package it.beije.herse.web;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.beije.herse.web.ShopEntityManager;

public class MetodiJPA {
	
	
	//Inserisci un utente
	public static void insertUser() {
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Scanner input=new Scanner(System.in);
		User user=new User();
		List<User> users = new ArrayList<>();
		//sets
					System.out.println("Inserisci il tuo nome: ");
					user.setName(input.next());
					System.out.println("Inserisci il tuo cognome: ");	
					user.setSurname(input.next());
					System.out.println("Inserisci la tua email:  ");
					user.setEmail(input.next());
					System.out.println("Inserisci una password: ");
					user.setPassword(input.next());
					users.add(user);
	

		for (User u : users) manager.persist(u);

		transaction.commit();
		manager.close();
	}

	
	
//	//stampa degli utenti utilizzando criteria
//	public static List<User> printUsers(){
//		EntityManager manager=ShopEntityManager.newEntityManager();
//		manager.getTransaction().begin();
//		
//		//crea un  criteriaBuilder
//		CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
//		
//		//creazione oggetto query
//		CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
//		
//		Root<User> root=criteriaQuery.from(User.class);
//		
//		criteriaQuery.select(root.get("name"));
//		
//		CriteriaQuery<User> select=criteriaQuery.select(root);
//		
//		//specifica il tipo di risultato della query
//		TypedQuery<User> query=manager.createQuery(select);
//		
//	
//		//manager.close();
//		return query.getResultList();
//		
//	}
	
	
	//stampa di un utente dato il suo ID
	
	public static User printUser(int id) throws Exception{
			
		
		EntityManager manager = ShopEntityManager.newEntityManager();
		User user=manager.find(User.class, id);
		manager.close();
		return user;
		
	}
	
	//delete users inserendo id
		public static void deleteUser() {
			EntityManager manager=ShopEntityManager.newEntityManager();
			manager.getTransaction().begin();
			Scanner input=new Scanner(System.in);
	
			System.out.println("Inserisci l'id da eliminare ");
			manager.remove(manager.find(User.class, input.nextInt()));
			
			manager.getTransaction().commit();
			System.out.println("User eliminato!");
			manager.close();
		}
	
	//inserisci prodotto
	public static void insertProducts() {
		Scanner input=new Scanner(System.in);
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
				Product product=new Product();
				List<Product> products = new ArrayList<>();
			
				System.out.println("Inserisci descrizione prodotto");
				String description=input.next();
				product.setDescription(description);
				
				System.out.println("Inserisci il nome: ");
				product.setName(input.next());
				System.out.println("Inserisci il prezzo: ");
				product.setPrice(input.nextDouble());
				System.out.println("Inserisci la quantità: ");
				product.setQuantity(input.nextInt());
				products.add(product);
				
		for (Product pro : products) manager.merge(pro);

		transaction.commit();
		System.out.println("Prodotto inserito!");
		manager.close();
	}

	//stampa prodotti
	public static List<Product> printProducts(){
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<Product> products =manager.createQuery("select u from Product as u").getResultList();

		manager.close();
		return products;
	}

	//stampa di un prodotto dato il suo ID
	
		public static Product printProductFromID(int id) throws Exception{
				if(id<=0) {
					throw new Exception();
				}
			EntityManager manager = ShopEntityManager.newEntityManager();
			Product product=manager.find(Product.class, id);
			manager.close();
			
			return product;
		}
		
		public static List<Cart> printCartFromID(int id) throws Exception{
			if(id<=0) {
				throw new Exception();
			}
		EntityManager manager = ShopEntityManager.newEntityManager();
		List<Cart> c =manager.createQuery("select u from Cart as u where u.idUser="+id+"").getResultList();
		manager.close();
		
		return c;
	}
		
		

	//updates descrizione e prezzo di product
		public static void updateProduct(String description,double price, int id) {
			EntityManager manager = ShopEntityManager.newEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();

			Product p = manager.find(Product.class, id);
			p.setDescription(description);
			p.setPrice(price);
			manager.persist(p);

			transaction.commit();
			manager.close();

		}
		
		
		//lista ordini
		public static List<Order> printOrders(){
			EntityManager manager = ShopEntityManager.newEntityManager();
			List<Order> orders=manager.createQuery("select u from Order as u").getResultList();
//			for(Order u:orders)
//				System.out.println(u);
			
			manager.close();
			return	orders;
		}
	
		public static List<User> printUsers(){
			EntityManager manager = ShopEntityManager.newEntityManager();
			List<User> users=manager.createQuery("select u from User as u").getResultList();
//			for(Order u:orders)
//				System.out.println(u);
			
			manager.close();
			return	users;
		}
		
		
		
	
		public static void insertOrder() {
				EntityManager manager = ShopEntityManager.newEntityManager();
				EntityTransaction transaction = manager.getTransaction();
				transaction.begin();
				
				Scanner input=new Scanner(System.in);
				List<Order> orders=new ArrayList<>();
				Order order=new Order();
				
			//controllo User
				System.out.println("Inserisci la tua email:");
				String email=input.next();
				
				if(login(email)!=null) {
					
					//entro nell'ordine	
						System.out.println("Ecco qui la lista dei prodotti disponibili:");
						printProducts();
						System.out.println("Inserisci l'id del prodotto che vuoi comprare:");
						int id_inserito_utente=input.nextInt();
						System.out.println("Inserisci la quantità che vuoi comprare: ");
						double amount=input.nextDouble();
						
								try {
										if(controlQuantityProduct(id_inserito_utente,amount)) {
											order.setAmount(amount);
											int amount_product=printProductFromID(id_inserito_utente).getQuantity();
											printProductFromID(id_inserito_utente).setQuantity((int) (amount_product-amount));
										}
								} catch (Exception e) {
										System.out.println("problemi con l'id!");
										e.printStackTrace();
									}
								
						order.setDateTime(LocalDateTime.now());
						order.setUserId((login(email)).getId());
						orders.add(order);
						
						for (Order o : orders) manager.merge(o);
						
														
										
										
					//fix order-item
										
										
										List<OrderItem> listOrderItem=new ArrayList<>();
										OrderItem orderItem=new OrderItem();
										
										orderItem.setOrderId(order.getId());
										orderItem.setProductId(id_inserito_utente);
										try {
											orderItem.setSellPrice((printProductFromID(id_inserito_utente)).getPrice());
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										listOrderItem.add(orderItem);
										
										
										for (OrderItem o :  listOrderItem) 
											manager.merge(o);				
										
										transaction.commit();
										manager.close();						
					
				}
				
				
//				List<Order> orders=new ArrayList<>();
//				Order order=new Order();
//				order.setAmount((double) 1);
//				order.setDateTime(LocalDateTime.now());
//				order.setId(1);
//				order.setUserId(1);
//				orders.add(order);
//				
//				for (Order o : orders) manager.merge(o);
//
//				transaction.commit();
//				manager.close();
			}
			
		

		
		
//		//controllo quantity product
		public static boolean controlQuantityProduct(int id, double amount) throws Exception {
			EntityManager manager = ShopEntityManager.newEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
//			
//			Scanner input=new Scanner(System.in);
//			Order order=new Order();
//			List<Order> orders=new ArrayList<>();
			
			
		
						if(printProductFromID(id).getQuantity()>amount) {
									
									return true;
						}else {
							System.out.println("Il prodotto non è piu disponibile/ nella quantità desiderata!");
							return false; }
			
		}
		
		
		//controllo esistenza email-User nel DB
		public static User login(String email) {
			EntityManager manager = ShopEntityManager.newEntityManager();
			List<User> users=manager.createQuery("select u from User as u").getResultList();
			
			for(User u:users) {
				if(u.getEmail().equalsIgnoreCase(email)) {
					System.out.println("Login effettuato!");
					return u;
				}
				
				}
			
			return null;
			
			
		}
}

