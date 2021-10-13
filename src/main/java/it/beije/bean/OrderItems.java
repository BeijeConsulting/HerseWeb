package it.beije.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "product_id")
	private Integer product_id;

	@Column(name = "sell_price")
	private Integer sellPrice;
	
	@Column(name = "quantity")
	private Integer quantity;

	// Getter & Setters
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return product_id;
	}

	public void setProductId(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", orderId=" + orderId + ", product_id=" + product_id + ", sellPrice="
				+ sellPrice + ", quantity=" + quantity + "]";
	}

	// ToString using Fields
	

}
