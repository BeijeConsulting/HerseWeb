package Shop;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
public class Funzioni {

	public static User login(String username, String password) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<User> users=manager.createQuery("select u from User as u").getResultList();
		for(User u:users)
			if(u.getEmail().equalsIgnoreCase(username)&&u.getPassword().equals(password)) 
				return u;
		return null;
	}
	public static int ControlloRegistrazione(String email, String name,String surname,String password,String conf_email,String conf_password) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<User> users=manager.createQuery("select u from User as u").getResultList();
		if(!email.contains("@"))
			return -1;
		if(!email.equals(conf_email))
			return -3;
		if(!password.equals(conf_password))
			return -4;
		for(User u:users)
			if(u.getEmail().equalsIgnoreCase(email)) 
				return -2;
		return 0;
	}
	public static User Registrazione(String email,String name,String surname,String password) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		User user=new User();
		manager.getTransaction().begin();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setSurname(surname);
		manager.persist(user);
		manager.getTransaction().commit();
		return user;
	}

	public static ArrayList<Product> carrello(Integer id,ArrayList<Product>carrello) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		carrello.add(manager.find(Product.class, id));
		return carrello;
	}
	public static ArrayList<Integer> qta(Integer quta,ArrayList<Integer>qta) {
		qta.add(quta);
		return qta;
	}

	public static boolean controlloQuantità(int id,int qty) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		Product p=manager.find(Product.class, id);
		if(p.getQty()>=qty) {
			manager.getTransaction().begin();
			p.setQty(p.getQty()-qty);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}
	//
	public static boolean controlloIdProdotto(Integer id) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		if(manager.find(Product.class, id)!=null)
			return true;
		return false;
	}

	public static int inserisciOrdine(ArrayList<Product>carrello,Double tot,User user) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		manager.getTransaction().begin();
		Order order=new Order();
		order.setAmount(tot);
		order.setDataTime(LocalDateTime.now());
		order.setUserId(user.getId());
		manager.persist(order);
		manager.getTransaction().commit();
		return order.getId();
	}
		public static void inserisciOrdineItem(ArrayList<Product>carrello,Double tot,User user,ArrayList<Integer>qta) {
			
			int orderId=inserisciOrdine(carrello,tot,user);
			int i=0;
			for(Product c:carrello) {
				commit(c,qta.get(i),orderId);
				i++;
			}
		}
	
		public static void commit(Product c,Integer i,Integer orderId) {
			EntityManager manager=ShopEntityManager.newEntityManager();
			OrderItem orderItem=new OrderItem();
			manager.getTransaction().begin();
			orderItem.setOrderId(orderId);
			orderItem.setProductId(c.getId());
			orderItem.setSellPrice(c.getPrice());
			orderItem.setQuantity(i);
			manager.persist(orderItem);
			manager.getTransaction().commit();
		}
	public static List<Product> getCatalogo() {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<Product> prodotti=manager.createQuery("SELECT p FROM Product as p").getResultList();
		return prodotti;
	}
}
//
