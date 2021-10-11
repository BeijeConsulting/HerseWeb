package it.beije.herse.web.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Shop {

	public static void main(String[] args) {
		//menuShop();
	}

	public static void menuShop() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Buongiorno utente. Inserisci il tuo id per effettuare l'ordine");
		boolean idValido = false;
		int idUser = 0;
		
		while(!idValido) {
			try {
				idUser = scanner.nextInt();
				idValido = true;
			} catch(InputMismatchException inputE) {
				System.out.println("Input sbagliato riprova");
				
			}
		}

		User user = entityManager.find(User.class, idUser);
		scanner.nextLine();

		int opzione = 1;
		while (opzione != 0) {
			if (user != null) {
				System.out.println("1: visualizza la lista di ordini relativi al tuo profilo");
				System.out.println("2: effettua un nuovo ordine");
				System.out.println("3: inserisci un prodotto al tuo ordine");
				System.out.println("4: ottieni informazioni riguardo un ordine");
				System.out.println("0: esci");

				opzione = scanner.nextInt();
				switch (opzione) {
				case 1:
					ordersOfUser(idUser);
					break;
				case 2:
					insertOrder(idUser);
					break;
				case 3:
					System.out.println("inserisci l'id dell'ordine");
					int idOrdine2 = scanner.nextInt();
					scanner.nextLine();

					System.out.println("Inserisci l'id del prodotto che vuoi inserire");
					int idProdotto2 = scanner.nextInt();
					scanner.nextLine();

					System.out.println("Inserisci la quantità del prodotto");
					int quantita2 = scanner.nextInt();
					scanner.nextLine();

					insertOrderItem(idOrdine2, idProdotto2, quantita2);

					System.out.println("prodotto inserito");
					break;
				case 4:
					ordersOfUser(idUser);
					System.out.println("Vuoi ottenere informazioni riguardo un ordine? Rispondi sì o no");
					String risposta = scanner.next();
					if (risposta.equalsIgnoreCase("si") || risposta.equalsIgnoreCase("sì")) {
						System.out.println("Inserisci l'id dell'ordine che vuoi visualizzare");
						int idOrdine3 = scanner.nextInt();
						getOrderInfo(idOrdine3);
					}
					break;
				default:
					System.out.println("Numero inserito errato. Inserire nuovamente un numero");
					break;
				}
			}

			else {
				System.out.println("Id non presente. Inserisci nuovamente un id valido");
			}
			
		}
		scanner.close();
	}

	public static Order insertOrder(int idUser) {
		// map products with key of id products and values of products quantity
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Order order = new Order();
		order.setDateTime(LocalDateTime.now());
		order.setUserId(idUser);
		order.setAmount(new Double(0));
//		for (Map.Entry<Integer, Integer> product : products.entrySet()) {
//			System.out.println(product);
//	        insertOrderItem(order.getId(), product.getKey(), product.getValue());
//	    }

		entityManager.persist(order);
		transaction.commit();
		entityManager.close();
		return order;
	}

	public static void deleteOrderItemJpa(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		OrderItem orderItem = entityManager.find(OrderItem.class, id);
		if (orderItem != null) {
			entityManager.remove(orderItem);
		}

		transaction.commit();
		System.out.println("Element is removed");
		entityManager.close();
	}

	public static OrderItem insertOrderItem(int idOrder, int idProduct, int quantity) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Product product = entityManager.find(Product.class, idProduct);
		//prova(entityManager, transaction, product);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(idOrder);
		orderItem.setProductId(idProduct);
		orderItem.setSellPrice(product.getPrice());

		// verifica sulla quantita
		if (product.getQuantity() >= quantity) {
			orderItem.setQuantity(quantity);
			product.setQuantity(product.getQuantity() - quantity);
			System.out.println(product);
			entityManager.persist(orderItem);
			entityManager.persist(product);
			transaction.commit();

			fixPriceOrder(idOrder);

		} else if (product.getQuantity() < quantity) {
			System.out.println("Quantità di prodotti limitata");
			transaction.rollback();
		}

		entityManager.close();
		return orderItem;
	}
	
//	public static void prova(EntityManager m, EntityTransaction t, Product p) {
//		
//		
//		p.setName("crop top");
//		
//		m.persist(p);
//		t.commit();
//		m.close();
//	}

	public static void getOrderInfo(Integer idOrder) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Order order = entityManager.find(Order.class, idOrder);
		if (order != null) {
			System.out.println("Informazioni riguardanti l'ordine");
			System.out.println(order);
		} else {
			System.out.println("Ordine non trovato. Provare con un altro id");
		}

		String jpqlOrderItem = "SELECT o FROM OrderItem as o WHERE o.orderId = " + idOrder;
		Query query = entityManager.createQuery(jpqlOrderItem);
		List<OrderItem> orderItems = query.getResultList();

		if (orderItems.size() >= 1) {
			System.out.println("Informazioni riguardanti ogni elemento dell'ordine");
			for (OrderItem ordI : orderItems) {
				System.out.println("Order Item: ");
				System.out.println(ordI);

				Integer idProduct = ordI.getProductId();
				Product product = entityManager.find(Product.class, idProduct);

				System.out.println("Product related to the order:");
				System.out.println(product);
			}
		} else {
			System.out.println("L'ordine è vuoto");
		}

		entityManager.close();
	}

	public static List<Order> ordersOfUser(Integer idUser) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Order> orders = new ArrayList<Order>();

		User user = entityManager.find(User.class, idUser);
		if (user != null) {
			String orderSelect = "SELECT o FROM Order AS o WHERE o.userId =" + idUser;
			Query query = entityManager.createQuery(orderSelect);
			orders = query.getResultList();

			if (orders.size() >= 1) {
				System.out.println("List of all orders");
				for (Order o : orders) {
					System.out.println(o);
				}
			} else
				System.out.println("L'utente non ha ordini");

		} else {
			System.out.println("Utente non trovato. Riprovare con un altro id");
		}

		entityManager.close();
		return orders;
	}

	private static void fixPriceOrder(int idOrder) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpqlSelect = "SELECT o FROM OrderItem as o WHERE o.orderId =" + idOrder;
		Query query = entityManager.createQuery(jpqlSelect);
		List<OrderItem> orderItems = query.getResultList();
		System.out.println(orderItems);
		double total = 0;
		Order order = entityManager.find(Order.class, idOrder);

		if (orderItems != null && orderItems.size() >= 1) {
			for (OrderItem ord : orderItems) {

				if (ord.getQuantity() != 0) {
					total += ord.getSellPrice() * ord.getQuantity();
				}
			}
			order.setAmount(total);
			entityManager.persist(order);
			transaction.commit();
		} else
			System.out.println("ordine non presente");

		entityManager.close();

	}
}
