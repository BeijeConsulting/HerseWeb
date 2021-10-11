package Shop;
import javax.persistence.*;
@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String desc;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="quantity")
	private Integer qty;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{id: ").append(id)
				.append(", name: ").append(name)
				.append(", desc: ").append(desc)
				.append(", price: ").append(price)
				.append(", qty: ").append(qty)
				.append("}");
		
		return builder.toString();
	}

}
