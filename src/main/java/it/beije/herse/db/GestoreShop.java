package it.beije.herse.db;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


public class GestoreShop {

	private static Scanner scanner = new Scanner(System.in);
	private static User log;


	private static void login() {

		System.out.println("EMAIL:");
		String user = scanner.next();

		EntityManager entityManager = Singleton.createEntity("herse-shop");
		Query q = entityManager.createQuery("SELECT user FROM User as user WHERE email='" + user + "'");
		List<User> feed = q.getResultList();
		if (feed.size()==1) {
			System.out.println("PASSWORD:");
			String password = scanner.next();

			q = entityManager.createQuery("SELECT user FROM User as user WHERE email='" + user + "' AND PASSWORD='" + password + "'");
			feed = q.getResultList();
			if (feed.size()==1) {
				log = feed.get(0);
				System.out.println("LOGIN EFFETTUATO \n");
				menu();
			} else{
				System.out.println("PASSWORD ERRATA");
			}
		} else System.out.println("UTENTE INESISTENTE \n");

		entityManager.close();
	}

	private static void registra() {

		EntityManager entityManager = Singleton.createEntity("herse-shop");

		System.out.println("EMAIL");
		String email = scanner.next();
		Query q = entityManager.createQuery("SELECT user FROM User as user WHERE email='" + email + "'");
		List<User> feed = q.getResultList();
		if (feed.size()==0) {

			System.out.println("NOME");
			String nome = scanner.next();
			System.out.println("COGNOME");
			String cognome = scanner.next();
			System.out.println("PASSWORD");
			String password = scanner.next();

			User utente = new User(email,nome,cognome,password);

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			transaction.commit();
			entityManager.close();
			System.out.println("UTENTE CREATO");
			log = utente;
			menu();

		} else {
			System.out.println("UTENTE ESISTENTE");
		}
	}

	private static void start() {

		while(log==null) {

			System.out.println("0 per login, 1 per registrarsi, -1 per uscire");
			Integer scelta = null;
			scelta = scanner.nextInt();

			if(scelta==0) {
				login();
			} else if(scelta==1) {
				registra();
			} else if(scelta==-1) {
				break;
			}

		}
	}


	private static void visualizza() {

		EntityManager entityManager = Singleton.createEntity("herse-shop");
		Query q = entityManager.createQuery("SELECT product FROM Product as product");
		List<Product> lista = q.getResultList();

		System.out.println("SONO PRESENTI I SEGUENTI PRODOTTI");

		for(Product p : lista) {
			System.out.println(p);
		}

		System.out.println("\n scrivi l'id del prodotto che vuoi acquistare o digita -1 per tornare indietro");
		int scelta = scanner.nextInt();
		if(scelta==-1) {
			menu();
		} else {

			Query c = entityManager.createQuery("SELECT product FROM Product as product WHERE id='"+ scelta +"'");
			lista = c.getResultList();

			if(lista.size()>0) {

				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				Product p = lista.get(0);

				int max = p.getQuantity();
				boolean ok = false;
				String s=null;
				int quanti=1;

				while(!ok) {	
					System.out.println("Digita la quantità di prodotti da acquistare");
					quanti = scanner.nextInt();

					if(max==0) { ok = true; continue; }

					if(quanti<=0) {
						System.out.println("Devi acquistarne almeno uno");
					} else if(quanti>0 && quanti <= max) {
						ok = true;
					} else {
						System.out.println("Puoi acquistarne al massimo " + max);
					}
				}

				if(max>0) {
					p.setQuantity(max-quanti);
				}

				else s = "Il prodotto in questo momento non è in magazzino, ma arriverà";

				entityManager.persist(p);
				transaction.commit();

				Order order = new Order(log.getId(), p.getPrice() * quanti);
				OrderItem orderItem = new OrderItem(order.getId(), p.getId(), p.getPrice(), quanti);

				if(s != null)  System.out.println(s);
				System.out.println("ORDINE REGISTRATO");
				menu();


			} else {
				System.out.println("NON ESISTE UN PRODOTTO CON QUESTO ID");
				visualizza();
			}
		}
		entityManager.close();
	}

	private static void ordini() {

		EntityManager entityManager = Singleton.createEntity("herse-shop");
		Query q = entityManager.createQuery("SELECT order FROM Order as order WHERE user_id='"+ log.getId()+ "'");
		List<Order> orderList = q.getResultList();

		if(orderList.size()>0) {
			System.out.println("Ci sono i seguenti ordini a nome di "+ log.getEmail());
			
			for(Order order : orderList) {
				
				System.out.println(order);
				q = entityManager.createQuery("SELECT OrderItem FROM OrderItem as OrderItem WHERE order_id='"+ order.getId()+ "'");
				List<OrderItem> OrderItemsList = q.getResultList();
				
				for(OrderItem orderItem : OrderItemsList) {
					
					q = entityManager.createQuery("SELECT product FROM Product as product WHERE id='"+ orderItem.getProductId()+ "'");
					List<Product> OrderProductList = q.getResultList();
					
					for(Product product : OrderProductList) {
						System.out.println(orderItem.getQuantity() + " " + product.getName() + " a " + orderItem.getSellPrice() + "€");
					}
				}

			}
		} else System.out.println("Non ci sono ordini nel tuo storico");
		
		System.out.println();
		menu();
	}

	private static void menu() {

		System.out.println("0 per visualizzare elenco prodotti");
		System.out.println("1 per visualizzare i tuoi ordini");
		System.out.println("-1 per logout");

		int scelta = scanner.nextInt();

		switch(scelta) {
		case 0:
			visualizza();
			break;
		case 1: 
			ordini();
			break;
		case -1:
			log = null;
			System.out.println("LOGOUT EFFETTUATO");
			start();
			break;
		default:
			System.out.println("NON HO CAPITO");
			menu();
			break;
		}

	}

	public static void main(String[] args) {


		try {
			start();
		} catch (InputMismatchException e) {
			System.out.println("NON HO CAPITO");
			e=null;
		}


	}

}
