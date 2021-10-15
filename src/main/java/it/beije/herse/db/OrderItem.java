package it.beije.herse.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

@Entity 
@Table(name = "`order_item`")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="product_id")
	private int productId;
	
	@Column(name="sell_price")
	private Double sellPrice;
	
	@Column(name="quantity")
	private Integer quantity;


	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity=quantity;
	}

	public OrderItem() {}
	public OrderItem(int orderId, int productId, Double sellPrice, Integer quantity) {
		
		this.orderId=orderId;
		this.productId=productId;
		this.sellPrice=sellPrice;
		this.quantity=quantity;
	}
	
	
	
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", sellPrice=" + sellPrice
				+ ", quantity=" + quantity + "]";
	}
	
	
	public static void main(String[] args) {
		
		User user = new User("samu@fraio.it", "Samu","Fraio","pass");
		Product product = new Product("chitarra classica", "suona", new Double(30.0), new Integer(1), "default.jpg");	
	    Order order = new Order(user.getId(),product.getPrice());
		OrderItem orderItem = new OrderItem(order.getId(),product.getId(),product.getPrice(),new Integer(2));
		
		EntityManager entityManager = Singleton.createEntity("herse-shop");
		Query n = entityManager.createQuery("SELECT product FROM Product as product");
		List<Product> lista = n.getResultList();
		entityManager.close();
		
		for(Product product2 : lista) {
			System.out.println(product2);
		}

	}

}
