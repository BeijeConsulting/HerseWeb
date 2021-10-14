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
	public static Order newOrder(Integer userId) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		Order order=new Order();
		manager.getTransaction().begin();
		order.setAmount(0.0);
		order.setDataTime(LocalDateTime.now());
		order.setUserId(userId);
		manager.persist(order);
		manager.getTransaction().commit();
		return order;
	}

	public static ArrayList<Carrello> carrello(Integer id_product,Integer qty,ArrayList<Carrello>carrello,Order order) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		for(Carrello c:carrello) {
			if(id_product==c.getId_product()) {
				manager.getTransaction().begin();
				//manager.merge(order);
				order.setAmount(order.getAmount()+qty*(manager.find(Product.class, id_product).getPrice()));
				//manager.persist(order);
				manager.getTransaction().commit();
				c.setQty(c.getQty()+qty);
				return carrello;
			}}
		Carrello carr=new Carrello();
		carr.setId_product(id_product);
		carr.setQty(qty);
		carrello.add(carr);
		manager.getTransaction().begin();
		//manager.merge(order);
		order.setAmount(order.getAmount()+qty*manager.find(Product.class, id_product).getPrice());
		manager.getTransaction().commit();
		return carrello;
	}
	public static Product getProdotto(Integer id_product){
		EntityManager manager=ShopEntityManager.newEntityManager();
		Product p=manager.find(Product.class, id_product);
		return p;
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
	
	public static void terminaOrdine(ArrayList<Carrello> carrello,Order order) {
		for(Carrello c:carrello)
			commit(c,order.getId());
	}
	
		public static void commit(Carrello c,int id_order) {
			EntityManager manager=ShopEntityManager.newEntityManager();
			Product p=manager.find(Product.class, c.getId_product());
			OrderItem orderItem=new OrderItem();
			manager.getTransaction().begin();
			orderItem.setOrderId(id_order);
			orderItem.setProductId(c.getId_product());
			orderItem.setSellPrice(p.getPrice());
			orderItem.setQuantity(c.getQty());
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
