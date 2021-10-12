package Shop;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
public class Funzioni {
	static EntityManager manager=ShopEntityManager.newEntityManager();
	static CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
	static CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();
	static List<Integer> q=new ArrayList();
	static List<Product> carrello = new ArrayList<Product>();
	static Double tot = 0.0;
	static Integer user=null;
	static Integer orderId=null;

	public static boolean login(Integer id) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<User> users=manager.createQuery("select u from User as u").getResultList();
		for(User u:users)
			if(u.getId().equals(id)) {
				user=id;
				return true;
			}
		return false;
	}

	public static void carrello(Integer id,Integer qty ) {
			carrello.add(manager.find(Product.class, id));
			tot+=qty*carrello.get(carrello.size()-1).getPrice();
	}

	public static boolean controlloQuantità(int id,int qty) {
		Product p=manager.find(Product.class, id);
		if(p.getQty()>=qty) {
			manager.getTransaction().begin();
			p.setQty(p.getQty()-qty);
			manager.getTransaction().commit();
			q.add(qty);
			return true;
		}
		return false;
	}

	public static boolean controlloIdProdotto(Integer id) {
		if(manager.find(Product.class, id)!=null)
			return true;
		return false;
	}

	public static Integer inserisciOrdine() {
		manager.getTransaction().begin();
		Order order=new Order();
		order.setAmount(tot);
		order.setDataTime(LocalDateTime.now());
		order.setUserId(user);
		manager.persist(order);
		manager.getTransaction().commit();
		return order.getId();
	}
	public static void inserisciOrdineItem() {
		orderId=inserisciOrdine();
		int i=0;
		for(Product c:carrello) {
			commit(c,q.get(i),orderId);
			i++;
		}
		
		q=new ArrayList();
		carrello =new ArrayList<Product>();
		tot =0.0;
		user=null;
		orderId=null;
	}

	public static void commit(Product c,Integer i,Integer id) {
		OrderItem orderItem=new OrderItem();
		manager.getTransaction().begin();
		orderItem.setOrderId(id);
		orderItem.setProductId(c.getId());
		orderItem.setSellPrice(c.getPrice());
		orderItem.setQuantity(i);
		manager.persist(orderItem);
		manager.getTransaction().commit();
	}
	public static String getCatalogo() {
		List<Product> prodotti=manager.createQuery("SELECT p FROM Product as p").getResultList();
		StringBuilder catalogo=new StringBuilder();
		for(Product p:prodotti) 
			catalogo.append("<tr><td>"+p.getId()+"</td><td>"+p.getName()+"</td><td>"+p.getDesc()+"</td><td>"+p.getPrice()+"</td><td>"+p.getQty()+"</td></tr>");
		return catalogo.toString();
			
	}
}

